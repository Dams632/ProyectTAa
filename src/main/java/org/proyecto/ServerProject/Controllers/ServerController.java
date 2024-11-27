package org.proyecto.ServerProject.Controllers;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;
import org.proyecto.ServerProject.GUI.ServerGUI;
import org.proyecto.ServerProject.PantallaCliente.PantallaCliente;
import org.proyecto.ServerProject.Server.Server;

public class ServerController  {
    private ServerGUI serverGUI;
    private final LeerConfig leerConfig = new LeerConfig("./src/main/resources/config/config.propierties");
    private final ConcreteSocketPool concreteSocketPool;
    private Server server;
    private Map<String, Socket> clientSockets = new HashMap<>();
    private Map<String, PantallaCliente> pantallaClientes = new HashMap<>();
    private Map<String, Boolean> clientScreenInUse = new HashMap<>();


    public ServerController(ServerGUI serverGUI,Server server) {
        this.concreteSocketPool=ConcreteSocketPool.getConcretePool(leerConfig);
        this.serverGUI=serverGUI;
        this.server=server;

        serverGUI.getStartServer().addActionListener(e -> {
            try {
                startServer();
            }catch (Exception es){

            }
        });
        serverGUI.getStopServer().addActionListener(e -> {
            try {
                stopServer();
            }catch (Exception es){

            }
        });
        serverGUI.getViewClientScreen().addActionListener(e -> {
            String selectedClient = serverGUI.getClientList().getSelectedValue();
            if (selectedClient != null) {
                if (clientScreenInUse.getOrDefault(selectedClient, false)) {
                    JOptionPane.showMessageDialog(serverGUI, "This client's screen is already in use.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    PantallaCliente pantallaCliente = pantallaClientes.get(selectedClient);
                    if (pantallaCliente != null) {
                        JFrame frame = new JFrame("Pantalla del Cliente: " + selectedClient);
                        JLabel label = new JLabel();
                        frame.add(label);
                        frame.setSize(800, 600);
                        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        frame.setVisible(true);
                        pantallaCliente.startScreenSharing(label);
                        clientScreenInUse.put(selectedClient, true);
                        frame.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                                clientScreenInUse.put(selectedClient, false);
                            }
                        });
                    }
                }
            }
        });
    }
    private void startServer() throws Exception{
        System.out.println("HOLAAAAAA");
        serverGUI.getStopServer().setEnabled(false);
        server.iniciarServer();
        server.setClientConnectedListener(this::onClientConnected);
        serverGUI.getStopServer().setEnabled(true);
        serverGUI.getStartServer().setEnabled(false);
        System.out.println("SERVER INICIADO DESDE LA YGUIS");

    }
    private void stopServer() throws Exception{
        System.out.println("ADIOSSSS");
        server.stop();
        //server=null;
        serverGUI.getStopServer().setEnabled(false);
        serverGUI.getStartServer().setEnabled(true);
    }
    private void onClientConnected(String clientID, Socket clientSocket) {
        clientSockets.put(clientID, clientSocket);
        pantallaClientes.put(clientID, new PantallaCliente(clientSocket));
        updateClientList();
        clientScreenInUse.put(clientID, false);
    }

    private void updateClientList() {
        String[] clients = clientSockets.keySet().toArray(new String[0]);
        serverGUI.updateClientList(clients);
    }

    private Socket getClientSocket(String clientIdentifier) {
        return clientSockets.get(clientIdentifier);
    }
}

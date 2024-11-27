package org.proyecto.ServerProject.GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ServerGUI extends JFrame {
    private JButton startServer;
    private JButton stopServer;
    private JButton transferFile;
    private JPanel clientPanel;
    private Map<String,JPanel> clientViews;
    private JList<String> clientList;
    private JButton viewClientScreen;

    public ServerGUI(){
        setTitle("SERVIDOR REMOTO");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //Panel superior de botones
        startServer = new JButton("Iniciar Servidor");
        stopServer = new JButton("Detener Servidor");
        transferFile = new JButton("TRANSFERIR ARCHIVOS");
        stopServer.setEnabled(false);

        JPanel topPanel = new JPanel();
        topPanel.add(transferFile);
        topPanel.add(startServer);
        topPanel.add(stopServer);

        clientPanel = new JPanel();
        clientPanel.setLayout(new GridLayout(0,3,10,10));
        clientPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));

        JScrollPane scrollPane = new JScrollPane(clientPanel);
        add(topPanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);

        clientList = new JList<>();
        viewClientScreen = new JButton("Ver Pantalla del Cliente");

        JPanel clientListPanel = new JPanel(new BorderLayout());
        clientListPanel.add(new JScrollPane(clientList), BorderLayout.CENTER);
        clientListPanel.add(viewClientScreen, BorderLayout.SOUTH);

        add(clientListPanel, BorderLayout.EAST);
    }

    public JPanel getClientPanel() {
        return clientPanel;
    }

    public JButton getStartServer() {
        return startServer;
    }

    public JButton getStopServer() {
        return stopServer;
    }

    public JButton getTransferFile() {
        return transferFile;
    }

    public JList<String> getClientList() {
        return clientList;
    }

    public JButton getViewClientScreen() {
        return viewClientScreen;
    }

    public void updateClientList(String[] clients) {
        clientList.setListData(clients);
    }
}

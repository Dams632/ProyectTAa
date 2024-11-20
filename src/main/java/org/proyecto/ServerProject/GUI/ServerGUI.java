package org.proyecto.ServerProject.GUI;
import org.proyecto.ServerProject.Controllers.ServerController;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ServerGUI extends JFrame {
    private JButton startServer;
    private JButton stopServer;
    private JButton transferFile;
    private JPanel clientPanel;


    public ServerGUI(){
        setTitle("SERVIDOR REMOTO");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        startServer = new JButton("Iniciar Servidor");
        stopServer = new JButton("Detener Servidor");
        transferFile = new JButton("TRANSFERIR ARCHIVOS");
        stopServer.setEnabled(false);

        JPanel topPanel = new JPanel();
        topPanel.add(transferFile);
        topPanel.add(startServer);
        topPanel.add(stopServer);

        clientPanel = new JPanel();
        clientPanel.setLayout(new GridLayout(5,5));
        add(topPanel,BorderLayout.NORTH);
        add(clientPanel,BorderLayout.CENTER);
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
}

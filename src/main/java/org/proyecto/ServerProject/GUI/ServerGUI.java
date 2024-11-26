package org.proyecto.ServerProject.GUI;
import org.proyecto.ServerProject.Controllers.ServerController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;


public class ServerGUI extends JFrame {
    private JButton startServer;
    private JButton stopServer;
    private JButton transferFile;
    private JPanel clientPanel;
    private Map<String,JPanel> clientViews;

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

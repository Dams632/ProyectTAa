package org.proyecto.ServerProject.GUI;
import org.proyecto.ServerProject.Controllers.ServerController;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ServerGUI {
    private JFrame frame;
    private JPanel clientPanel;
    private JButton startServerButton;
    private JButton stopServerButton;
    private ServerController serverController;

    private Map<String, JPanel> clientTiles;

    public ServerGUI() {
        this.serverController = new ServerController(this);
        this.clientTiles = new HashMap<>();
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("Servidor Remoto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        startServerButton = new JButton("Iniciar Servidor");
        startServerButton.addActionListener(e -> onStartServer());

        stopServerButton = new JButton("Apagar Servidor");
//        stopServerButton.addActionListener(e -> onStopServer());
        stopServerButton.setVisible(false); // Inicialmente invisible.

        clientPanel = new JPanel(new GridLayout(0, 3, 10, 10));
    }

    public void showPreGUI() {
        frame.add(startServerButton, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void onStartServer() {
        if (serverController.startServer()) {
            frame.remove(startServerButton);
            frame.add(stopServerButton, BorderLayout.SOUTH);
            frame.add(new JScrollPane(clientPanel), BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Error al iniciar el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//    private void onStopServer() {
//        serverController.stopServer();
//        JOptionPane.showMessageDialog(frame, "Servidor apagado.", "Información", JOptionPane.INFORMATION_MESSAGE);
//        System.exit(0);
//    }

    public void addClient(String clientName) {
        JPanel clientTile = new JPanel();
        clientTile.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon("src/resources/client_icon.png")); // Reemplaza con tu imagen.
        JLabel nameLabel = new JLabel(clientName, SwingConstants.CENTER);

        clientTile.add(imageLabel, BorderLayout.CENTER);
        clientTile.add(nameLabel, BorderLayout.SOUTH);

        clientTiles.put(clientName, clientTile);
        clientPanel.add(clientTile);
        frame.revalidate();
        frame.repaint();
    }

    public void removeClient(String clientName) {
        JPanel clientTile = clientTiles.remove(clientName);
        if (clientTile != null) {
            clientPanel.remove(clientTile);
            frame.revalidate();
            frame.repaint();
        }
    }
}

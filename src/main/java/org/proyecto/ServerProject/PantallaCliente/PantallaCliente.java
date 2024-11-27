package org.proyecto.ServerProject.PantallaCliente;

import java.awt.Image;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PantallaCliente extends JFrame implements Runnable {
    private Socket socketCliente;

    public PantallaCliente(Socket socket) {
        socketCliente = socket;
    }

    public void startScreenSharing(JLabel label) {
        Thread clientScreenThread = new Thread(() -> run(label));
        clientScreenThread.start();
    }

    @Override
    public void run() {
        // This method is no longer used
    }

    public void run(JLabel label) {
        try (ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream())) {
            while (true) {
                String header = ois.readUTF();

                if ("SCREEN".equals(header)) {
                    // Recibir la imagen encapsulada en ImageIcon
                    ImageIcon imageIcon = (ImageIcon) ois.readObject();
                    Image image = imageIcon.getImage();
                    image = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);

                    // Mostrar la imagen en el JLabel
                    label.setIcon(imageIcon);

                    // Actualizar la ventana
                    label.repaint();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

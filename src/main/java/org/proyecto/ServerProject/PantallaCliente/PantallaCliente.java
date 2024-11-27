package org.proyecto.ServerProject.PantallaCliente;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PantallaCliente extends JFrame implements Runnable {
    private Socket socketCliente;
    private GrabarPantalla grabarPantalla;

    public PantallaCliente(Socket socket) {
        socketCliente = socket;
        grabarPantalla=new GrabarPantalla("src/main/resources/grabaciones/"+socket.getInetAddress()+".mp4");
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
        Thread grabarThread = new Thread(grabarPantalla);
        grabarThread.start();
        EnviarComandosCliente enviarComandosCliente = new EnviarComandosCliente(socketCliente,label);
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
                    // Convierte ImageIcon a BufferedImage
                    BufferedImage bufferedImage = new BufferedImage(
                            imageIcon.getIconWidth(),
                            imageIcon.getIconHeight(),
                            BufferedImage.TYPE_INT_RGB
                    );
                    Graphics2D g2d = bufferedImage.createGraphics();
                    imageIcon.paintIcon(null, g2d, 0, 0);
                    g2d.dispose();

                    // Agregar el frame al grabador
                    grabarPantalla.agregarFrame(bufferedImage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            grabarPantalla.detenerGrabacion();
        }
    }
}

package org.proyecto.ServerProject.PantallaCliente;

import org.proyecto.Command.ClicIzquierdoMouse;
import org.proyecto.Command.MoverMouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;

public class PantallaCliente  extends JFrame implements Runnable{
    private  Socket socketCliente;

    public PantallaCliente(Socket socket) {
        socketCliente=socket;
    }

    @Override
    public void run() {
        Rectangle rectangle = null;
        JFrame frame = new JFrame("Pantalla del Cliente");
        JLabel label = new JLabel();
        frame.add(label);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        EnviarComandosCliente enviarComandosCliente = new EnviarComandosCliente(socketCliente,label);




        try (ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream())) {
            while (true) {
                String header = ois.readUTF();

                if ("SCREEN".equals(header)) {
                    // Recibir la imagen encapsulada en ImageIcon
                    ImageIcon imageIcon = (ImageIcon) ois.readObject();
                    Image image = imageIcon.getImage();
                    image= image.getScaledInstance(label.getWidth(),label.getHeight(),Image.SCALE_SMOOTH);

                    // Mostrar la imagen en el JLabel
                    label.setIcon(imageIcon);

                    // Actualizar la ventana
                    frame.repaint();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

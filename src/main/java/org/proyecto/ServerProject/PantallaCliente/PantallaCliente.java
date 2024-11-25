package org.proyecto.ServerProject.PantallaCliente;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;

public class PantallaCliente  extends JFrame implements Runnable{
    private  Socket socketCliente;
    private EnviarComandosCliente enviarComandosCliente;

    public PantallaCliente(Socket socket) {
        socketCliente=socket;
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Pantalla del Cliente");
        JLabel label = new JLabel();
        frame.add(label);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                enviarComandosCliente.enviarMoverMouse(e.getX(),e.getY());
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enviarComandosCliente.enviarClicIzquierdoMouse(e.getX(),e.getY());
            }
        });

        try (ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream())) {
            while (true) {
                // Recibir la imagen encapsulada en ImageIcon
                ImageIcon imageIcon = (ImageIcon) ois.readObject();

                // Mostrar la imagen en el JLabel
                label.setIcon(imageIcon);

                // Actualizar la ventana
                frame.repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

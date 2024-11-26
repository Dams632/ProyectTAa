package org.proyecto.ServerProject.PantallaCliente;

import org.proyecto.Command.ClicIzquierdoMouse;
import org.proyecto.Command.MoverMouse;

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
        this.enviarComandosCliente = new EnviarComandosCliente(socket);
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Pantalla del Cliente");
        JLabel label = new JLabel();
        frame.add(label);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                enviarComandosCliente.enviarComando(new MoverMouse(e.getX(),e.getY()));
            }
        });
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("CLIC CLIC");
                if (e.getButton() == MouseEvent.BUTTON1) {
                    enviarComandosCliente.enviarComando(new ClicIzquierdoMouse());
                }
            }
        });

        label.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                enviarComandosCliente.enviarComando(new MoverMouse(e.getX(),e.getY()));

            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                enviarComandosCliente.enviarClicIzquierdoMouse(e.getX(),e.getY());
                }
            }
        });


        try (ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream())) {
            while (true) {
                String header = ois.readUTF();

                if ("SCREEN".equals(header)) {
                    // Recibir la imagen encapsulada en ImageIcon
                    ImageIcon imageIcon = (ImageIcon) ois.readObject();

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

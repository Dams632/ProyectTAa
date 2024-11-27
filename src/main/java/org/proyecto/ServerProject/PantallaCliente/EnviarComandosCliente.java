package org.proyecto.ServerProject.PantallaCliente;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class EnviarComandosCliente implements KeyListener,MouseMotionListener,MouseListener{
    private Socket clientSocket;
    private JLabel jLabel;
    private PrintWriter writer;

    public EnviarComandosCliente(Socket clientSocket, JLabel jLabel) {
        this.clientSocket = clientSocket;
        this.jLabel = jLabel;

        jLabel.addMouseListener(this);
        jLabel.addMouseMotionListener(this);
        jLabel.addKeyListener(this);

        try{
            writer = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        writer.println(-7); // Indicador para tecla tipeada
        writer.println((int) e.getKeyChar()); // Código del carácter asociado
        writer.flush(); // Enviar datos

    }

    @Override
    public void keyReleased(KeyEvent e) {
        writer.println(-8); // Indicador para tecla liberada
        writer.println(e.getKeyCode()); // Código de la tecla liberada
        writer.flush(); // Enviar datos

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        writer.println(-1);
        int button = e.getButton();
        //first we assume left button is clicked
        int xButton = 16;
        if (button == 3) // if right button is clciked
        {
            xButton = 4;
        }

        // xbutton is value used to tell robot class which mouse button is pressed
        writer.println(xButton);
        writer.flush();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        writer.println(-2);
        int button = e.getButton();
        System.out.println(button);
        int xButton = 16;
        if (button == 3) {
            xButton = 4;
        }
        writer.println(xButton);
        writer.flush();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        writer.println(-6);
        writer.println((int)(e.getX())); // Coordenada X del mouse
        writer.println((int)(e.getY())); // Coordenada Y del mouse
        writer.flush();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        writer.println(-5);
        writer.println((int)(e.getX() ));
        writer.println((int)(e.getY()));
        writer.flush();
    }
}

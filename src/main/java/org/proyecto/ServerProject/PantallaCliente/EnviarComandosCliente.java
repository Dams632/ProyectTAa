package org.proyecto.ServerProject.PantallaCliente;

import org.proyecto.Command.ClicIzquierdoMouse;
import org.proyecto.Command.ICommand;
import org.proyecto.Command.MoverMouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class EnviarComandosCliente implements KeyListener,MouseMotionListener,MouseListener{
    private Socket clientSocket;
    private JLabel jLabel;
    //private Rectangle rectangle;
    private PrintWriter writer;

    public EnviarComandosCliente(Socket clientSocket, JLabel jLabel) {
        this.clientSocket = clientSocket;
        this.jLabel = jLabel;
       // this.rectangle = rectangle;

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

    }

    @Override
    public void keyReleased(KeyEvent e) {

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

    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        double xScale = jLabel.getWidth()/jLabel.getWidth();
//        double yScale = jLabel.getHeight()/jLabel.getHeight();
        writer.println(-5);
        writer.println((int)(e.getX() ));
        writer.println((int)(e.getY()));
        writer.flush();
    }
}

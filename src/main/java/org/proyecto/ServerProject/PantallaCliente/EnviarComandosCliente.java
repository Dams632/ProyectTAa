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

public class EnviarComandosCliente extends Thread {
    private final Socket clientSocket;
    //private ObjectOutputStream out;

    public EnviarComandosCliente(Socket clientSocket) {
        this.clientSocket = clientSocket;
//        try{
//        out=new ObjectOutputStream(clientSocket.getOutputStream());
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }

    public synchronized void enviarMoverMouse(int coordenadaX, int coordenadaY){
        enviarComando(new MoverMouse(coordenadaX,coordenadaY));
    }
    public synchronized void enviarClicIzquierdoMouse(int coordenadaX, int coordenadaY){
        enviarComando((new ClicIzquierdoMouse()));
    }

    @Override
    public void run() {


    }

    public void enviarComando(ICommand command){
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {
            out.writeUTF("COMMAND");
            out.flush();
            out.writeObject(command); // Enviar el comando
            out.flush(); // Asegúrate de que los datos se envíen correctamente
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

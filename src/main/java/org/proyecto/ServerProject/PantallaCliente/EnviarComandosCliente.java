package org.proyecto.ServerProject.PantallaCliente;

import org.proyecto.Command.ClicIzquierdoMouse;
import org.proyecto.Command.ICommand;
import org.proyecto.Command.MoverMouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class EnviarComandosCliente {
    private final Socket clientSocket;

    public EnviarComandosCliente(Socket clientSocket) {
        this.clientSocket = clientSocket;    }

    public void enviarMoverMouse(int coordenadaX, int coordenadaY){
        enviarComando(new MoverMouse(coordenadaX,coordenadaY));
    }
    public void enviarClicIzquierdoMouse(int coordenadaX, int coordenadaY){
        enviarComando((new ClicIzquierdoMouse(coordenadaX, coordenadaY)));
    }
    private void enviarComando(ICommand command){
        try(ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())){
            out.writeObject(command);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package org.proyecto.ClientProject.CapturarPantalla;

import org.proyecto.Command.ICommand;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class RecibirOrdenesRemotas implements Runnable {
    private final Socket socket;

    public RecibirOrdenesRemotas(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
            while(true){
                ICommand comando = (ICommand) in.readObject();
                comando.ejecutar();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

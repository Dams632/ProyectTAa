package org.proyecto.ClientProject.CapturarPantalla;

import org.proyecto.Command.ICommand;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.DataInputStream;
import java.io.EOFException;
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
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            while (true) {
                String headr = in.readUTF();
                try {
                    if("COMMAND".equals(headr)) {
                        ICommand comando = (ICommand) in.readObject();
                        comando.ejecutar();
                    }
                } catch (EOFException e) {
                    System.out.println("Se alcanzó el final del flujo de datos.");
                    break; // Si es normal que se cierre la conexión, salimos del bucle
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){

        }
    }
}

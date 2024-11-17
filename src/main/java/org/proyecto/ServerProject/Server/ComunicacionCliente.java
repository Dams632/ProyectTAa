package org.proyecto.ServerProject.Server;

import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ComunicacionCliente implements Runnable{
    private final Socket cliente;
    private final ConcreteSocketPool concreteSocketPool;

    public ComunicacionCliente(Socket cliente, ConcreteSocketPool concreteSocketPool) {
        this.cliente = cliente;
        this.concreteSocketPool = concreteSocketPool;
        asignarSocket(cliente);

    }
    private void asignarSocket(Socket cliente){
        try {
            cliente = concreteSocketPool.getSocket();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // Implementa la lógica de comunicación con el cliente.
            BufferedReader input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter output = new PrintWriter(cliente.getOutputStream(), true);

            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("Mensaje recibido: " + message);
                output.println("Eco: " + message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cliente.close(); // Cierra el socket.
                concreteSocketPool.liberarSocket(cliente); // Devuelve el socket al pool.
                System.out.println("Socket liberado y devuelto al pool.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

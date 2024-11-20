package org.proyecto.ServerProject.Server;

import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ComunicacionCliente implements Runnable{
    private final Socket cliente;
    private final ConcreteSocketPool concreteSocketPool;


    public ComunicacionCliente(Socket cliente,ConcreteSocketPool concreteSocketPool) {
        this.cliente = cliente;
        this.concreteSocketPool = concreteSocketPool;
    }


    @Override
    public void run() {
        try {
            Socket temp = concreteSocketPool.getSocket();
            System.out.println("Ya me puedo comunicar con el cliente");
            System.out.println("El socket del cliente aqui es: " + cliente);
            BufferedReader input = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter output = new PrintWriter(cliente.getOutputStream(), true);
            System.out.println("Después de buffer y el print");
            String mensaje;
            while ((mensaje = input.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensaje);
                output.println("Eco: " + mensaje);
            }

        } catch (Exception e) {

        } finally {
            try {
                //cliente.close(); // Cierra el socket.
                concreteSocketPool.liberarSocket(cliente); // Devuelve el socket al pool.
                System.out.println("Socket liberado y devuelto al pool.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

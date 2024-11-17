package org.proyecto.ClientProject;

import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente{
    private String ip;
    private int port;
    private  final ConcreteSocketPool concreteSocketPool;

    public Cliente(ConcreteSocketPool concreteSocketPool) {
        this.concreteSocketPool=concreteSocketPool;
    }
    public void conectar() {
        try (Socket socket = concreteSocketPool.getSocket();
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Enviar comandos al servidor
            System.out.println("comando para el servidor");

            // Leer la respuesta del servidor
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Respuesta del servidor: " + response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void desconectar(){
        //aplicar logica
    }
}

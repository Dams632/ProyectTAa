package org.proyecto.ClientProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente{
    private String ip;
    private int port;

    public Cliente(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    public void conectar() {
        try (Socket socket = new Socket(ip, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Enviar comandos al servidor
            out.println("comando para el servidor");

            // Leer la respuesta del servidor
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println("Respuesta del servidor: " + response);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void desconectar(){
        //aplicar logica
    }
}

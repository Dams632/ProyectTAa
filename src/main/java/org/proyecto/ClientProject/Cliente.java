package org.proyecto.ClientProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;

public class Cliente {
    private String ip;
    private int port;
    private final LeerConfig config = new LeerConfig("./src/main/resources/config/config.propierties");
    private Socket socket;


    public Cliente(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void conectar() throws  IOException {
        socket = new Socket(config.getIp(), config.getPort());
        iniciarComunicacion();
    }

    private void iniciarComunicacion() {
        Scanner scanner = new Scanner(System.in);

        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println("Conexión establecida con el servidor. Puedes empezar a enviar comandos.");

            while (true) {
                System.out.print("Comando para el servidor: ");
                String comando = scanner.nextLine();
                out.println(comando); // Enviar comando al servidor

                // Leer la respuesta del servidor
                String response = in.readLine();
                if (response == null) {
                    System.out.println("Servidor desconectado.");
                    break;
                }
                System.out.println("Respuesta del servidor: " + response);
            }
        } catch (IOException e) {
            System.err.println("Error durante la comunicación con el servidor: " + e.getMessage());
        } finally {
            desconectar(); // Liberar el socket en caso de error o desconexión
        }
    }
    public void desconectar () {
        //aplicar logica
    }
}



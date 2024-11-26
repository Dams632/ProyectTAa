package org.proyecto.ClientProject;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

import org.proyecto.ClientProject.CapturarPantalla.EnviarCapturaPantalla;
import org.proyecto.ClientProject.CapturarPantalla.RecibirOrdenesRemotas;
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
        try{
            EnviarCapturaPantalla enviarCapturaPantalla = new EnviarCapturaPantalla(socket);
            RecibirOrdenesRemotas recibirOrdenesRemotas = new RecibirOrdenesRemotas(socket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void desconectar () {
        //aplicar logica
    }
}



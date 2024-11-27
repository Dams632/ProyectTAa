package org.proyecto.ClientProject;

import java.io.IOException;
import java.net.Socket;

import org.proyecto.ClientProject.CapturarPantalla.EnviarCapturaPantalla;
import org.proyecto.ClientProject.CapturarPantalla.RecibirOrdenesRemotas;
import org.proyecto.Config.LeerConfig;

public class Cliente {
    private String ip;
    private int port;
    private final LeerConfig config = new LeerConfig("./src/main/resources/config/config.propierties");
    private Socket socket;


    public Cliente(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void conectar(String ip) throws  IOException {

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



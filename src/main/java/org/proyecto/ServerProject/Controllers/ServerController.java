package org.proyecto.ServerProject.Controllers;
import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;
import org.proyecto.ServerProject.GUI.ServerGUI;
import org.proyecto.ServerProject.Server.ComunicacionCliente;
import org.proyecto.ServerProject.Server.Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerController  {
    private ServerGUI serverGUI;
    private final LeerConfig leerConfig = new LeerConfig("./src/main/resources/config/config.propierties");
    private final ConcreteSocketPool concreteSocketPool;
    private Server server;


    public ServerController(ServerGUI serverGUI,Server server) throws Exception {
        this.concreteSocketPool=ConcreteSocketPool.getConcretePool(leerConfig);
        this.serverGUI=serverGUI;
        this.server=server;

        serverGUI.getStartServer().addActionListener(e -> {
            try {
                startServer();
            }catch (Exception es){

            }
        });
        serverGUI.getStopServer().addActionListener(e -> {
            try {
                stopServer();
            }catch (Exception es){

            }
        });
    }
    private void startServer() throws Exception{
        System.out.println("HOLAAAAAA");
        serverGUI.getStopServer().setEnabled(false);
        server.iniciarServer();
        serverGUI.getStopServer().setEnabled(true);
        serverGUI.getStartServer().setEnabled(false);
        System.out.println("SERVER INICIADO DESDE LA YGUIS");

    }
    private void stopServer() throws Exception{
        System.out.println("ADIOSSSS");
        server.stop();
        //server=null;
        serverGUI.getStopServer().setEnabled(false);
        serverGUI.getStartServer().setEnabled(true);
        System.out.println("SERVER INICIADO DESDE LA YGUIS");

    }
}

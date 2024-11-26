package org.proyecto.ServerProject.Controllers;
import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;
import org.proyecto.ServerProject.GUI.ServerGUI;
import org.proyecto.ServerProject.Server.Server;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServerController  {
    private ServerGUI serverGUI;
    private final LeerConfig leerConfig = new LeerConfig("./src/main/resources/config/config.propierties");
    private final ConcreteSocketPool concreteSocketPool;
    private Server server;


    public ServerController(ServerGUI serverGUI,Server server) {
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
        serverGUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
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
    }
}

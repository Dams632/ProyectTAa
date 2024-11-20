package org.proyecto.ServerProject;

import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;
import org.proyecto.ServerProject.GUI.ServerGUI;
import org.proyecto.ServerProject.Server.Server;

import javax.swing.*;
import java.io.IOException;

public class MainServer {
        public static void main(String[] args) throws IOException, InterruptedException,Exception {
            LeerConfig config = new LeerConfig("./src/main/resources/config/config.propierties");

            ConcreteSocketPool pool = ConcreteSocketPool.getConcretePool(config);
            Server server= Server.getServer(config.getPort(), pool);
            server.start();
            System.out.println("Hello worldaa!");
//            SwingUtilities.invokeLater(() -> {
//                ServerGUI serverGUI = new ServerGUI();
//                serverGUI.showPreGUI();
//            });
        }

}

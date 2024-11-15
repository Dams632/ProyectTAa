package org.proyecto.ServerProject;

import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;
import org.proyecto.ServerProject.Server.Server;

import java.io.IOException;

public class MainServer {
        public static void main(String[] args) throws IOException, InterruptedException {
            LeerConfig config = new LeerConfig("./src/main/resources/config/config.propierties");

            ConcreteSocketPool pool = new ConcreteSocketPool(config);
            Server server= Server.getServer(config.getPort(), pool);
            server.start();
            pool.iniciarPool(config.getMinConexiones(), config.getMaxConexiones());




            System.out.println("Hello worldaa!");
        }

}

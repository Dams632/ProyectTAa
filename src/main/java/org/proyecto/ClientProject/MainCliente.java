package org.proyecto.ClientProject;

import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;

public class MainCliente {
        public static void main(String[] args) {
            LeerConfig config = new LeerConfig("./src/main/resources/config/config.propierties");
            ConcreteSocketPool concreteSocketPool = new ConcreteSocketPool(config);

            Cliente cliente = new Cliente(concreteSocketPool);

            cliente.conectar();
            System.out.println("Hello worldaa!");
        }

}

package org.proyecto.ClientProject;

import org.proyecto.Config.LeerConfig;

public class MainCliente {
        public static void main(String[] args) {
            LeerConfig config = new LeerConfig("./src/main/resources/config/config.propierties");
            Cliente cliente = new Cliente("192.168.1.8",1331);

            cliente.conectar();
            System.out.println("Hello worldaa!");
        }

}

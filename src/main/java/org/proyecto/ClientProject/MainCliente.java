package org.proyecto.ClientProject;

import org.proyecto.Config.LeerConfig;

public class MainCliente {
        public static void main(String[] args) throws Exception {
            LeerConfig config = new LeerConfig("./src/main/resources/config/config.propierties");
            Cliente cliente = new Cliente(config.getIp(), config.getPort());
            try {
                cliente.conectar();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Hello worlda");
        }

}

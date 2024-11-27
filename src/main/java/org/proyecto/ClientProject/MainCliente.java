package org.proyecto.ClientProject;

import org.proyecto.Config.LeerConfig;

import javax.swing.*;

public class MainCliente {
        public static void main(String[] args) throws Exception {
            LeerConfig config = new LeerConfig("./src/main/resources/config/config.propierties");
            String ip = JOptionPane.showInputDialog("Please enter server IP");
            Cliente cliente = new Cliente(ip, config.getPort());
            try {
                cliente.conectar(ip);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Hello worlda");
        }

}

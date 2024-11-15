package org.proyecto.ServerProject.Server;

import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

public class Server {
    private  LeerConfig leerConfig = new LeerConfig("./src/main/resources/config/config.propierties");
    private static Server server;
    private ServerSocket serverSocket;
    private  ConcreteSocketPool concreteSocketPool;


    private Server(int port,ConcreteSocketPool concreteSocketPool) throws IOException {
        serverSocket= new ServerSocket(port);
        this.concreteSocketPool = concreteSocketPool;
    }
    public static Server getServer(int port,ConcreteSocketPool concreteSocketPool) throws IOException{
        if(server ==null){
            return new Server(port,concreteSocketPool);
        }
        return server;
    }


    public void start() throws IOException,InterruptedException {
        System.out.println("Servidor iniciado en el puerto ");
        concreteSocketPool.iniciarPool(leerConfig.getMinConexiones(), leerConfig.getMaxConexiones());
        while(true){
            try{
                Socket cliente = serverSocket.accept();
                System.out.println("Cloiente conectaado desde: "+ cliente.getRemoteSocketAddress());


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

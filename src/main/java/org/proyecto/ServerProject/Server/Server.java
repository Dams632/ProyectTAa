package org.proyecto.ServerProject.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;

public class Server {
    private final LeerConfig leerConfig = new LeerConfig("./src/main/resources/config/config.propierties");
    private static Server server;
    private final ServerSocket serverSocket;
    private final ConcreteSocketPool concreteSocketPool;



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
        Socket cliente;
        while((cliente = serverSocket.accept())!=null){

            try{                
                Socket poolSocket = concreteSocketPool.getSocket();
                System.out.println("Cliente:"+cliente.getRemoteSocketAddress());
                new Thread(new ComunicacionCliente(poolSocket,concreteSocketPool)).start();
                //concreteSocketPool.liberarSocket(cliente);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

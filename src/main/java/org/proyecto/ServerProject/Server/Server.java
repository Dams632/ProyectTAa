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



    private Server(int port,ConcreteSocketPool concreteSocketPool) throws Exception {
        serverSocket= new ServerSocket(port);
        this.concreteSocketPool = concreteSocketPool;
    }
    public static Server getServer(int port,ConcreteSocketPool concreteSocketPool) throws Exception{
        if(server ==null){
            return new Server(port,concreteSocketPool);
        }
        return server;
    }
    public void iniciarServer(){
        new Thread(()-> {
            try {

                start();
                serverSocket.setReuseAddress(true);
            } catch (Exception e) {
                //throw new RuntimeException(e);
            }
        }).start();
    }
    public void stop(){
        try {
            serverSocket.close();
        }catch (IOException e){

        }
    }



    private void start() throws IOException,Exception {
        System.out.println("Servidor iniciado en el puerto "+ leerConfig.getPort());
        concreteSocketPool.iniciarPool(leerConfig.getMaxConexiones());

        while (true) {
            System.out.println("Esperando conexión de cliente...");
            System.out.println("Socket pool :" + concreteSocketPool);

            Socket cliente = serverSocket.accept(); // Acepta una nueva conexión

                // Inicia el manejador para el cliente
                new Thread(new ComunicacionCliente(cliente, concreteSocketPool)).start();

        }

    }

}

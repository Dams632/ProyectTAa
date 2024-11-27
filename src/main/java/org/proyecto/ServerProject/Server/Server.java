package org.proyecto.ServerProject.Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;

public class Server {
    private final LeerConfig leerConfig = new LeerConfig("./src/main/resources/config/config.propierties");
    private static Server server;
    private final ServerSocket serverSocket;
    private final ConcreteSocketPool concreteSocketPool;
    private Thread serverThread;
    private ClientConnectedListener clientConnectedListener;



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
    public void setClientConnectedListener(ClientConnectedListener listener) {
        this.clientConnectedListener = listener;
    }
    public void iniciarServer(){
         serverThread = new Thread(()-> {
            try {
                start();
                serverSocket.setReuseAddress(true);
            } catch (Exception e) {
            }
        });
        serverThread.start();
    }
    public void stop(){
        try {
            serverSocket.close();
            serverThread.join();
        }catch (Exception e){

        }
    }



    private void start() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(leerConfig.getMaxConexiones());
        System.out.println("Servidor iniciado en el puerto "+ leerConfig.getPort());
        concreteSocketPool.iniciarPool(leerConfig.getMaxConexiones());

        while (true) {
            System.out.println("Esperando conexión de cliente...");
            System.out.println("Socket pool :" + concreteSocketPool);

            Socket cliente = serverSocket.accept(); // Acepta una nueva conexión
            String clientID = cliente.getInetAddress().getHostAddress();

            if (clientConnectedListener != null) {
                clientConnectedListener.onClientConnected(clientID, cliente);
            }

            // Remove automatic window opening
            // PantallaCliente pantallaCliente = new PantallaCliente(cliente);
            // executor.execute(pantallaCliente);
        }

    }

    public interface ClientConnectedListener {
        void onClientConnected(String clientID, Socket clientSocket);
    }

}

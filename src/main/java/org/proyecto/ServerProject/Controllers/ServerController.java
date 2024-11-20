package org.proyecto.ServerProject.Controllers;
import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.PoolSockets.ConcreteSocketPool;
import org.proyecto.ServerProject.GUI.ServerGUI;
import org.proyecto.ServerProject.Server.ComunicacionCliente;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerController {
    private final ServerGUI serverGUI;
    private final ConcreteSocketPool socketPool;
    private final ExecutorService threadPool;
    private boolean serverRunning;
    LeerConfig config = new LeerConfig("./src/main/resources/config/config.propierties");


    public ServerController(ServerGUI serverGUI) {
        this.serverGUI = serverGUI;
        this.socketPool = ConcreteSocketPool.getConcretePool(config);
        this.threadPool = Executors.newFixedThreadPool(config.getMaxConexiones());
        this.serverRunning = false;
    }

    public boolean startServer() {
        try {
            socketPool.iniciarPool(config.getMaxConexiones());
            serverRunning = true;

            // Simula la lógica de aceptar clientes
            new Thread(() -> {
                while (serverRunning) {
                    try {
                        Socket clientSocket = socketPool.getSocket();
                        serverGUI.addClient(clientSocket.getRemoteSocketAddress().toString());
                        threadPool.submit(new ComunicacionCliente(clientSocket, socketPool));
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public void stopServer() {
//        serverRunning = false;
//        threadPool.shutdown();
//        socketPool.closeAllSockets();
//    }
}

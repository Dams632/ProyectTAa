package org.proyecto.FactoryPool.PoolSockets;

import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.FactorySockets.ISocketFactory;
import org.proyecto.FactoryPool.FactorySockets.SocketFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class ConcreteSocketPool extends AbstractSocketPool{
    ISocketFactory fabricaSocket;
    private static ConcreteSocketPool instance;

    private ConcreteSocketPool(LeerConfig valor) {
        super(valor);
        fabricaSocket=SocketFactory.getFactory();
        socketDisponibles= new LinkedBlockingQueue<>(valor.getMaxConexiones());
    }
    public static ConcreteSocketPool getConcretePool(LeerConfig config){
        if(instance==null){
            instance =new ConcreteSocketPool(config);
        }
        return instance;
    }

    @Override
    public void iniciarPool( int max) throws Exception {
        for (int i = 0; i < max; i++) {
            // Agrega sockets vacíos a la pool, sin inicializarlos
            socketDisponibles.add(fabricaSocket.crearNuevo());
            System.out.println("Socket # " + i + " creado y listo en la pool."+socketDisponibles);
        }
    }
    public int getSocketsLibres(){
        return socketDisponibles.size();
    }

    @Override
    public synchronized Socket getSocket() throws InterruptedException,IOException {
        return socketDisponibles.take();
    }

    @Override
    public void liberarSocket(Socket socket) {
        socketDisponibles.add(socket);
    }
}

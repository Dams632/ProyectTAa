package org.proyecto.FactoryPool.PoolSockets;

import org.proyecto.Config.LeerConfig;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public abstract class AbstractSocketPool implements ISocketPool {
    protected final LeerConfig valor;
    protected final int max;
    protected final int min;
    protected final int timeout;
    protected BlockingQueue<Socket> socketDisponibles;

    public AbstractSocketPool(LeerConfig valor) {
        this.valor = valor;
        this.max = valor.getMaxConexiones();
        this.min = valor.getMinConexiones();
        this.timeout = valor.getTimeOut();
    }

    public abstract void iniciarPool(int min,int max);


    @Override
    public Socket getSocket() throws InterruptedException {
        if(socketDisponibles!=null){
            return socketDisponibles.take();
        }else{
            return null;
        }
    }

    @Override
    public void liberarSocket(Socket socket) {
        boolean insertar = socketDisponibles.offer(socket);
        if(!insertar){
            System.out.println("Cola llena, no se puede liberar objetos");
        }else{
            System.out.println("Socket" +System.identityHashCode(socket)+ "liberado ");
        }
    }
}

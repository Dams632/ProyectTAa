package org.proyecto.FactoryPool.PoolSockets;

import org.proyecto.Config.LeerConfig;
import org.proyecto.FactoryPool.FactorySockets.ISocketFactory;
import org.proyecto.FactoryPool.FactorySockets.SocketFactory;

import java.util.concurrent.LinkedBlockingQueue;

public class ConcreteSocketPool extends AbstractSocketPool{
    ISocketFactory fabricaSocket;


    public ConcreteSocketPool(LeerConfig valor) {
        super(valor);
        fabricaSocket=SocketFactory.getFactory("192.168.1.8",valor.getPort());
        socketDisponibles= new LinkedBlockingQueue<>(valor.getMaxConexiones());
    }

    @Override
    public void iniciarPool( int min,  int max) {
        for(int i=0; i<max;i++){
            socketDisponibles.add(fabricaSocket.crearNuevo());
            System.out.println("Socket #"+i+"Creado");
        }
    }
}

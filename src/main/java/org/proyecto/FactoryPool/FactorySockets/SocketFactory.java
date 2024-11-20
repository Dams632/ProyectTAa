package org.proyecto.FactoryPool.FactorySockets;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketFactory implements ISocketFactory{
    private static SocketFactory socketFactory;

    private SocketFactory() {

    }
    public static synchronized SocketFactory getFactory(){
        if(socketFactory ==null){
            return new SocketFactory();
        }else{
            return socketFactory;
        }
    }

    @Override
    public Socket crearNuevo() throws IOException{
        return new Socket();
    }
}

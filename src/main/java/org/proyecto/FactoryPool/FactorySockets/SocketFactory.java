package org.proyecto.FactoryPool.FactorySockets;

import java.io.IOException;
import java.net.Socket;

public class SocketFactory implements ISocketFactory{
    private static String ip;
    private static int port;
    private static SocketFactory socketFactory;

    private SocketFactory(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    public static SocketFactory getFactory(String ip, int port){
        if(socketFactory ==null){
            return new SocketFactory(ip, port);
        }else{
            return socketFactory;
        }
    }

    @Override
    public Socket crearNuevo() {
        try {
            return new Socket(ip, port);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}

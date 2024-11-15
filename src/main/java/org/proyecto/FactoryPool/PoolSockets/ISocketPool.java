package org.proyecto.FactoryPool.PoolSockets;


import org.proyecto.ServerProject.Socket.SocketP;

import java.net.Socket;

public interface ISocketPool {
    Socket getSocket() throws InterruptedException;
    void liberarSocket(Socket socket);
}

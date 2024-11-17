package org.proyecto.FactoryPool.PoolSockets;

import java.net.Socket;

public interface ISocketPool {
    Socket getSocket() throws InterruptedException;
    void liberarSocket(Socket socket);
}

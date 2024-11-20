package org.proyecto.FactoryPool.PoolSockets;

import java.io.IOException;
import java.net.Socket;

public interface ISocketPool {
     Socket  getSocket() throws InterruptedException,IOException;

     void liberarSocket(Socket socket);
}

package org.proyecto.FactoryPool.FactorySockets;

import org.proyecto.ServerProject.Socket.ISocket;

import java.net.Socket;

public interface ISocketFactory {
    Socket crearNuevo();
}

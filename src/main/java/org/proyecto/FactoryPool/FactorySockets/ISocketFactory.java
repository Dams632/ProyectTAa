package org.proyecto.FactoryPool.FactorySockets;

import java.io.IOException;
import java.net.Socket;

public interface ISocketFactory {
    Socket crearNuevo() throws IOException;
}

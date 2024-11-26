package org.proyecto.Observer;

import java.awt.image.BufferedImage;

public interface IClientObserver {
    void onScreenUpdate(String clientId, BufferedImage captura);
    void onClientConnected(String clientId);
    void onClientDisconnected(String clientId);
}

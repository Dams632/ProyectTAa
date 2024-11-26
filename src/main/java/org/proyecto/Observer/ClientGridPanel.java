package org.proyecto.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientGridPanel extends JPanel implements IClientObserver {
    private final Map<String, ClientThumbnail> clientPanels;
    private final GridLayout layout;
    public ClientGridPanel(){
        clientPanels = new ConcurrentHashMap<>();
        layout = new GridLayout(0,3,10,10);
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    @Override
    public void onClientConnected(String clientId) {
        SwingUtilities.invokeLater(() -> {
            ClientThumbnail thumbnail = new ClientThumbnail(clientId);
            clientPanels.put(clientId, thumbnail);
            add(thumbnail);
            revalidate();
            repaint();
        });

    }

    @Override
    public void onScreenUpdate(String clientId, BufferedImage captura) {
        SwingUtilities.invokeLater(()->{
            ClientThumbnail thumbnail = clientPanels.get(clientId);
            if(thumbnail!=null){
                thumbnail.updateScreenShot(captura);
            }
        });

    }

    @Override
    public void onClientDisconnected(String clientId) {
        SwingUtilities.invokeLater(() -> {
            ClientThumbnail thumbnail = clientPanels.remove(clientId);
            if (thumbnail != null) {
                remove(thumbnail);
                revalidate();
                repaint();
            }
        });

    }
}

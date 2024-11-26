package org.proyecto.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ClientThumbnail extends JPanel {
    private static final int THUMB_WIDTH = 320;
    private static final int THUMB_HEIGHT = 180;
    private final String clientId;
    private BufferedImage screenshot;
    private final JLabel infoLabel;
    private final Object imageLock = new Object();

    public ClientThumbnail(String clientId) {
        this.clientId = clientId;
        setPreferredSize(new Dimension(THUMB_WIDTH, THUMB_HEIGHT + 30));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        infoLabel = new JLabel("Cliente: " + clientId);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(infoLabel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    openDetailView();
                }
            }
        });
    }


    public void updateScreenshot(BufferedImage newScreenshot) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized (imageLock) {
            if (screenshot != null) {
                g.drawImage(screenshot, 0, 0, null);
            } else {
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, THUMB_WIDTH, THUMB_HEIGHT);
                g.setColor(Color.GRAY);
                g.drawString("Esperando captura...", 10, THUMB_HEIGHT / 2);
            }
        }
    }

    private void openDetailView() {
        // Implementar vista detallada si se necesita
    }

    public void updateScreenShot(BufferedImage captura) {
    }
}

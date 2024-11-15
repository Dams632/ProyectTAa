package org.proyecto.ServerProject.PantallaCliente;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class PantallaCliente implements Runnable{
    private final Socket socketCliente;
    private final JLabel screenLabel;

    public PantallaCliente(Socket socketCliente, JLabel screenLabel) {
        this.socketCliente = socketCliente;
        this.screenLabel = screenLabel;
    }

    @Override
    public void run() {
        try(InputStream in = socketCliente.getInputStream()){
            while(true){
                BufferedImage imagen = ImageIO.read(in);
                if(imagen!=null){
                    ImageIcon icon = new ImageIcon(imagen);
                    screenLabel.setIcon(icon);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

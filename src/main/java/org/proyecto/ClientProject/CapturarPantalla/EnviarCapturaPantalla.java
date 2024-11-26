package org.proyecto.ClientProject.CapturarPantalla;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class EnviarCapturaPantalla extends Thread{
    private final Socket socket;
    private Robot robot;
    private Rectangle rectangulo;

    public EnviarCapturaPantalla(Socket socket) throws Exception{
        this.socket = socket;
        robot = new Robot();
        rectangulo = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        start();
    }

    @Override
    public void run() {
        try(ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){
           while(true){
               BufferedImage imagen = robot.createScreenCapture(rectangulo);
               out.writeUTF("SCREEN");
               out.flush();
               ImageIcon imageIcon = new ImageIcon(imagen);
               out.writeObject(imageIcon);
               out.flush();
               Thread.sleep(100);
           }

        }catch (IOException | InterruptedException e ){
            e.printStackTrace();
        }

    }
}

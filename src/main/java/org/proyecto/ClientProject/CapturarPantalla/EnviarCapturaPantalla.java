package org.proyecto.ClientProject.CapturarPantalla;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class EnviarCapturaPantalla {
    private final Socket socket;
    private  final Robot robot;

    public EnviarCapturaPantalla(Socket socket) throws AWTException{
        this.socket = socket;
        this.robot = new Robot();
    }
    public void enviarScreenshots(){
        new Thread(()->{
            try(OutputStream out= socket.getOutputStream()){
                while(true){
                    BufferedImage screenshot  =robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                    ImageIO.write(screenshot,"jpg",out);
                    out.flush();
                    Thread.sleep(500);
                }
            }catch (IOException | InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}

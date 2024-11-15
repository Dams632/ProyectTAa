package org.proyecto.ClientProject.CapturarPantalla;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class RecibirOrdenesRemotas {
    private final Socket socket;
    private final Robot robot;

    public RecibirOrdenesRemotas(Socket socket) throws Exception {
        this.socket = socket;
        this.robot=new Robot();
    }
    public void escucharComandos(){
        new Thread(()->{
            try(DataInputStream in = new DataInputStream(socket.getInputStream())){
                while(true){
                    String comando = in.readUTF();
                    //IMPLEMENTAR COMANDOS
                    ejecutarComando(comando);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }).start();
    }

    public void ejecutarComando(String comando){
        try {
            String[] parts = comando.split(" ");
            switch (parts[0]) {
                case "MOV_MOUSE":
                    int x = Integer.parseInt(parts[1]);
                    int y = Integer.parseInt(parts[2]);
                    robot.mouseMove(x, y);
                    break;

                case "CLICK":
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    break;

                case "KEY_PRESS":
                    int keyCode = Integer.parseInt(parts[1]);
                    robot.keyPress(keyCode);
                    robot.keyRelease(keyCode);
                    break;

                default:
                    System.out.println("Comando no reconocido: " + comando);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

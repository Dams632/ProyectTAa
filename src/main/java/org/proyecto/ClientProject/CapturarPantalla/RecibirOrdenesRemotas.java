package org.proyecto.ClientProject.CapturarPantalla;
import java.awt.*;
import java.net.Socket;
import java.util.Scanner;

public class RecibirOrdenesRemotas extends Thread {
    private final Socket socket;
    private Robot robot;

    public RecibirOrdenesRemotas(Socket socket) {
        this.socket = socket;
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        Scanner scanner=null;
        try{
            scanner=new Scanner(socket.getInputStream());
            while(true){
                int command = scanner.nextInt();
                switch (command){
                    case -1:
                        robot.mousePress(scanner.nextInt());
                        break;
                    case -2:
                        robot.mouseRelease(scanner.nextInt());
                        break;
                    case -3:
                        robot.keyPress(scanner.nextInt());
                        break;
                    case -4:
                        robot.keyRelease(scanner.nextInt());
                        break;
                    case -5:
                        robot.mouseMove(scanner.nextInt(), scanner.nextInt());
                        break;
                    case -6:
                        robot.mousePress(scanner.nextInt());
                        robot.mouseMove(scanner.nextInt(),scanner.nextInt());
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

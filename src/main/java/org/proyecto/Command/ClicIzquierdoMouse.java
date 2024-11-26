package org.proyecto.Command;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ClicIzquierdoMouse implements ICommand{
    private int coordenadaX;
    private int coordenadaY;

    public ClicIzquierdoMouse() {
    }

    @Override
    public void ejecutar() {
        try{
            Robot robot = new Robot();
            robot.mousePress(MouseEvent.BUTTON1);
            robot.mouseRelease(MouseEvent.BUTTON1);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}

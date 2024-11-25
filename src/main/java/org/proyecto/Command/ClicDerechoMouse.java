package org.proyecto.Command;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ClicDerechoMouse implements ICommand{
    private int coordenadaX;
    private int coordenadaY;

    public ClicDerechoMouse(int coordenadaX, int coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    @Override
    public void ejecutar() {
        try{
            Robot robot = new Robot();
            robot.mouseMove(coordenadaX,coordenadaY);
            robot.mousePress(MouseEvent.BUTTON3);
            robot.mouseRelease(MouseEvent.BUTTON3);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }
}

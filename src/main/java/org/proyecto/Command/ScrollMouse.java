package org.proyecto.Command;

import java.awt.*;

public class ScrollMouse implements ICommand{
    private int coordenadaY;

    public ScrollMouse(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    @Override
    public void ejecutar() {
        try{
            Robot robot = new Robot();
            robot.mouseWheel(coordenadaY);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}

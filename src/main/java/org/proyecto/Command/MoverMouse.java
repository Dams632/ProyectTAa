package org.proyecto.Command;

import java.awt.*;

public class MoverMouse implements ICommand{
    private int coordenadaX;
    private int coordenadaY;

    public MoverMouse(int coordenadaX, int coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    @Override
    public void ejecutar() {
        try{
            Robot robot = new Robot();
            robot.mouseMove(coordenadaX,coordenadaY);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }
}

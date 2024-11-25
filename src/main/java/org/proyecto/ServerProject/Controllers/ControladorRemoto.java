package org.proyecto.ServerProject.Controllers;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ControladorRemoto {
    private Socket socketCliente;

    public ControladorRemoto(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }
    public void enviarMovimientoMouse(int coordenadaX, int coordenadaY){
        sendCommand("MOUSE_MOVE "+coordenadaX+""+coordenadaY);
    }
    public void enviarClickMouse(int boton){
        sendCommand("MOUSE_CLICK "+boton);
    }
    public void enviarTeclaPresionada(int keyCode){
        sendCommand("KEY_PRESS "+keyCode);
    }
    private void sendCommand(String command){
        try(OutputStream outputStream = socketCliente.getOutputStream()){
            outputStream.write((command+"\n").getBytes());
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

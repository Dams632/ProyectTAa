package org.proyecto.ServerProject.Socket;

import java.net.Socket;

public class SocketP {
    private Socket socketP;

    public SocketP(Socket socketP) {
        this.socketP = socketP;
    }
    public Socket getSocket(){
        return socketP;
    }
    public void close(){
        try{
            socketP.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

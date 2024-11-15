package org.proyecto.ServerProject.PantallaCliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class EnviarComandosCliente {
    private final Socket clientSocket;

    public EnviarComandosCliente(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void enviarComando(String comando) {
        try (DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {
            out.writeUTF(comando);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

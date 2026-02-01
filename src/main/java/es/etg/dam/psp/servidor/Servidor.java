package es.etg.dam.psp.servidor;

import java.net.ServerSocket;
import java.net.Socket;

import es.etg.dam.psp.ClienteHandler;

public class Servidor {

    public static final int PUERTO = 5000;
    public static final String MSG_ESCUCHA = "Servidor iniciado. Esperando clientes...";

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {

            System.out.println(MSG_ESCUCHA);

            while (true) {
                Socket socket = serverSocket.accept();
                Thread hilo = new Thread(new ClienteHandler(socket));
                hilo.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

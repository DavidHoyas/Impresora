package es.etg.dam.psp.servidor;

import java.net.ServerSocket;
import java.net.Socket;

import es.etg.dam.psp.ClienteHandler;
import es.etg.dam.psp.Impresora;
import es.etg.dam.psp.Tinta;

public class Servidor {

    public static final int PUERTO = 5000;
    public static final String MSG_ESCUCHA = "Servidor iniciado. Esperando clientes...";

    public static void main(String[] args) {

        Tinta tinta = new Tinta();
        Impresora impresora = new Impresora(tinta);

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {

            System.out.println(MSG_ESCUCHA);

            while (true) {
                Socket socket = serverSocket.accept();
                Thread hilo = new Thread(new ClienteHandler(socket, impresora));
                hilo.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

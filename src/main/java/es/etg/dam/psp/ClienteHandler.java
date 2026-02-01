package es.etg.dam.psp;

import java.io.IOException;
import java.net.Socket;

public class ClienteHandler implements Runnable {

    private Socket socket;

    public static final String MSG_KO = "KO";
    public static final int PARTES_ESPERADAS = 2;
    public static final String SPLITTER = " ";

    public static final int INDEX_TIPO = 0;
    public static final int INDEX_HOJAS = 1;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            Impresora impresora = new Impresora();
            
            String peticion = Conexion.recibir(socket);
            String[] partes = peticion.split(SPLITTER);

            if (partes.length != PARTES_ESPERADAS) {
                Conexion.enviar(MSG_KO, socket);
                return;
            }

            String tipo = partes[INDEX_TIPO];
            int hojas = Integer.parseInt(partes[INDEX_HOJAS]);

            String respuesta = impresora.imprimir(tipo, hojas);

            Conexion.enviar(respuesta, socket);

        } catch (IOException | NumberFormatException e) {
            try {
                Conexion.enviar(MSG_KO, socket);
            } catch (IOException ignored) {}
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }
}


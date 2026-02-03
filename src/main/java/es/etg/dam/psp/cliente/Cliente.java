package es.etg.dam.psp.cliente;

import java.net.Socket;

import es.etg.dam.psp.Conexion;

public class Cliente {

    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;
    private static final String MSG_USO = "Uso: java Cliente \"BN 5\" | \"COLOR 3\"";
    private static final String MSG_RESPUESTA = "Servidor responde: %s";

    public static final int INDEX_PETICION = 0;

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println(MSG_USO);
        }

        String peticion = args[INDEX_PETICION].trim();

        try (Socket socket = new Socket(HOST, PUERTO)) {

            Conexion.enviar(peticion, socket);

            String respuesta = Conexion.recibir(socket);

            System.out.println(String.format(MSG_RESPUESTA, respuesta));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



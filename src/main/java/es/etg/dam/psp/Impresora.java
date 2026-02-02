package es.etg.dam.psp;

public class Impresora {

    public static final int PRECIO_BN = 1;
    public static final int PRECIO_COLOR = 2;

    public static final String TIPO_BN = "BN";
    public static final String TIPO_COLOR = "COLOR";

    public static final String MSG_OK = "OK %d euros | Hojas restantes: BN=%d, COLOR=%d";
    public static final String MSG_KO = "KO | Hojas restantes: BN=%d, COLOR=%d";

    private final Tinta tinta;

    public Impresora(Tinta tinta) {
        this.tinta = tinta;
    }

    public String imprimir(String tipo, int hojas) {

        boolean exito;

        if (tipo == TIPO_BN) {
            exito = imprimirBN(hojas);
        } else if (tipo == TIPO_COLOR) {
            exito = imprimirColor(hojas);
        } else {
            exito = false;
        }

        String msg = exito
                ? String.format(MSG_OK, tinta.getHojasBN(), tinta.getHojasColor())
                : String.format(MSG_KO, tinta.getHojasBN(), tinta.getHojasColor());

        return msg;
    }

    public Boolean imprimirBN(int hojas) {

        return tinta.consumirBN(hojas);
    }

    public Boolean imprimirColor(int hojas) {

        return tinta.consumirColor(hojas);
    }

    public int calcularPrecio(int precioHoja, int hojas) {
        return precioHoja * hojas;
    }
}

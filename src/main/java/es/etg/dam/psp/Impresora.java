package es.etg.dam.psp;

public class Impresora {

    public static final double PRECIO_BN = 0.5;
    public static final double PRECIO_COLOR = 1.0;

    public static final String TIPO_BN = "BN";
    public static final String TIPO_COLOR = "COLOR";

    public static final String MSG_OK =
            "OK %.2f euros | Hojas restantes: BN=%d, COLOR=%d";
    public static final String MSG_KO =
            "KO | Hojas restantes: BN=%d, COLOR=%d";

    private final Tinta tinta;

    public Impresora(Tinta tinta) {
        this.tinta = tinta;
    }

    public String imprimir(String tipo, int hojas) {

        boolean exito = false;
        double precio = 0.0;

        if (TIPO_BN.equals(tipo)) {
            exito = imprimirBN(hojas);
            precio = calcularPrecio(PRECIO_BN, hojas);

        } else if (TIPO_COLOR.equals(tipo)) {
            exito = imprimirColor(hojas);
            precio = calcularPrecio(PRECIO_COLOR, hojas);
        }

        return exito
                ? String.format(MSG_OK, precio, tinta.getHojasBN(), tinta.getHojasColor())
                : String.format(MSG_KO, tinta.getHojasBN(), tinta.getHojasColor());
    }

    private boolean imprimirBN(int hojas) {
        return tinta.consumirBN(hojas);
    }

    private boolean imprimirColor(int hojas) {
        return tinta.consumirColor(hojas);
    }

    private double calcularPrecio(double precioHoja, int hojas) {
        return precioHoja * hojas;
    }
}

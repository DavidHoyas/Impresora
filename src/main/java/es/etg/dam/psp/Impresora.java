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

        return switch (tipo.toUpperCase()) {

            case TIPO_BN -> imprimirBN(hojas);
            case TIPO_COLOR -> imprimirColor(hojas);
            default -> String.format(MSG_KO, tinta.getHojasBN(), tinta.getHojasColor());
        };
    }

    private String imprimirBN(int hojas) {

        boolean exito = tinta.consumirBN(hojas);

        if (!exito) {
            return String.format(MSG_KO, tinta.getHojasBN(), tinta.getHojasColor());
        }

        int precio = calcularPrecio(PRECIO_BN, hojas);
        return String.format(MSG_OK, precio, tinta.getHojasBN(), tinta.getHojasColor());
    }

    private String imprimirColor(int hojas) {

        boolean exito = tinta.consumirColor(hojas);

        if (!exito) {
            return String.format(MSG_KO, tinta.getHojasBN(), tinta.getHojasColor());
        }

        int precio = calcularPrecio(PRECIO_COLOR, hojas);
        return String.format(MSG_OK, precio, tinta.getHojasBN(), tinta.getHojasColor());
    }

    private static int calcularPrecio(int precioHoja, int hojas) {
        return precioHoja * hojas;
    }
}





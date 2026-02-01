package es.etg.dam.psp;

public class Impresora {

    private static final int HOJAS_BN_INICIAL = 50;
    private static final int HOJAS_COLOR_INICIAL = 20;

    private static final int PRECIO_BN = 1;
    private static final int PRECIO_COLOR = 2;

    private static final String TIPO_BN = "BN";
    private static final String TIPO_COLOR = "COLOR";

    private static final String MSG_OK = "OK %d euros | Hojas restantes: BN=%d, COLOR=%d";
    private static final String MSG_KO = "KO | Hojas restantes: BN=%d, COLOR=%d";

    private static int hojasBN;
    private static int hojasColor;

    public Impresora() {
        hojasBN = HOJAS_BN_INICIAL;
        hojasColor = HOJAS_COLOR_INICIAL;
    }

    public synchronized String imprimir(String tipo, int hojas) {

        switch (tipo.toUpperCase()) {
            case TIPO_BN:
                return imprimirBlancoNegro(hojas);

            case TIPO_COLOR:
                return imprimirColor(hojas);

            default:
                return String.format(MSG_KO, hojasBN, hojasColor);
        }
    }

    private String imprimirBlancoNegro(int hojas) {
        if (hojasBN >= hojas) {
            hojasBN -= hojas;
            int precio = calcularPrecio(PRECIO_BN, hojas);
            return String.format(MSG_OK, precio, hojasBN, hojasColor);
        } else {
            return String.format(MSG_KO, hojasBN, hojasColor);
        }
    }

    private String imprimirColor(int hojas) {
        if (hojasColor >= hojas) {
            hojasColor -= hojas;
            int precio = calcularPrecio(PRECIO_COLOR, hojas);
            return String.format(MSG_OK, precio, hojasBN, hojasColor);
        } else {
            return String.format(MSG_KO, hojasBN, hojasColor);
        }
    }

    private static int calcularPrecio(int precio, int hojas) {
        return precio * hojas;
    }
}



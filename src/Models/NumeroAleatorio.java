package Models;

import java.util.Random;

public class NumeroAleatorio {
    private static String codigoAleatorio;
    public static String generarCodigoAleatorio() {
        if (codigoAleatorio == null) {
            Random rand = new Random();
            int codigo = rand.nextInt(9000) + 1000;
            codigoAleatorio = String.valueOf(codigo);
        }
        return codigoAleatorio;
    }
}

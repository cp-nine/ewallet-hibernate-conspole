package config;

public class Code {

    public static String makeCode(String x, String text, int panjang){
        String kode = text;
        int panjangX = x.length();
        int panjangText = text.length();
        int sisa = panjangText - panjang;

        if (panjangText > (panjang-panjangX)){
            kode = kode.substring(sisa + panjangX, panjangText);
        }

        return x + kode;
    }

}

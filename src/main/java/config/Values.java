package config;

import java.util.Random;

public class Values {

    // check numeric value
    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

    // check string value
    public static boolean isString(String str) {
        return str.matches("[a-zA-Z]*");
    }

    // check valid length value
    public static boolean validLength(String val, Integer min, Integer max){
        try {
            int lengthVal = val.length();
            if (lengthVal > max){
                return false;
            } else if (lengthVal < min){
                return false;
            } else {
                return true;
            }
        } catch (Exception e){
            return false;
        }
    }

    // get generate
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }

    // rupiah value
    public static String rupiah(double val){
        String curency = String.format("Rp.%,.0f", val).replaceAll(",", ".")+",00";
        return curency;
    }

    // balance value
    public static String balance(String val){
        String t1 = val.substring(0, 2);
        String t2 = val.substring(2, 5);
        String t3 = val.substring(5, 8);
        String t4 = val.substring(8, 10);
        return t1+"-"+t2+"-"+t3+"-"+t4;
    }

    public  static void isSucces(boolean transaction, String message){
        if (!transaction){
            System.out.println(message+ " failled.");
        } else {
            System.out.println(message+ " success.");
        }
    }

    public static void inputNotValid(){
        System.out.println("Input not valid...");
    }
}

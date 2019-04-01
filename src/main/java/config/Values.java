package config;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static String dateFormat(String date){
        Date newDate = null;
        try {
            String[] splitDate = date.split(" ");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-yyyy");
            newDate = simpleDateFormat.parse(splitDate[0]);
        } catch (Exception e){
            e.printStackTrace();
        }
        return newDate.toString();
    }

    public static void banner(){
        int width = 80;
        int height = 30;

        //BufferedImage image = ImageIO.read(new File("/Users/mkyong/Desktop/logo.jpg"));
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 12));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("E - W a l l e t", 3, 10);

        //save this image
        //ImageIO.write(image, "png", new File("/users/mkyong/ascii-art.png"));

        System.out.println("=========================================================================");
        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(image.getRGB(x, y) == -16777216 ? " " : "*");

            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
        }
        System.out.println("=========================================================================");
    }
}

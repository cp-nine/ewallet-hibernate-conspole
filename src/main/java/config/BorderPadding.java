package config;

public class BorderPadding {

    // function to create border horizontal
    public static void border(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" =");
        }
        System.out.println();
    }

    // padding right
    public static String padRight(String text, int length) {
        return String.format("%-" + length + "s", text);
    }


}

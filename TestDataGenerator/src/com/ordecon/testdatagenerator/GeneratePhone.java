package com.ordecon.testdatagenerator;

/**
 * @author Ivan Stojic
 */
public class GeneratePhone {
    private static String[] PREFIX = {
            "1", "21", "23", "91", "95", "98", "99"
    };

    private static int MAXID = 1499;

    public static void main(String[] args) {
        System.out.println("personid\tphone_number");
        for (int position = 1; position < MAXID; position++) {
            generatePhone(position);
        }
    }

    private static void generatePhone(int position) {
        if (Math.random() < 0.5) {
            // 6 digits

            double number;
            while (String.valueOf(number = Math.random()).length() != 6) {
                System.out.println(number);
            }

            System.out.println(position + "\t385" + PREFIX[(position -1) % PREFIX.length] + String.valueOf(number));

        } else {
            // 7 digits

        }
    }
}

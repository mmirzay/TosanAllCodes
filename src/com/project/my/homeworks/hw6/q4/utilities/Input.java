package com.project.my.homeworks.hw6.q4.utilities;

import java.util.Scanner;

public class Input {
    static Scanner input = new Scanner(System.in);

    public static String getOptionalStringInputValue(String msg) {
        System.out.print(msg + " ");
        return input.nextLine().trim();
    }

    public static String getStringInputValue(String msg) {
        String result = null;
        while (true) {
            result = getOptionalStringInputValue(msg);
            if (result.isEmpty())
                Printer.printErrorMessage("Invalid input!");
            else
                return result;
        }
    }

    public static String getValidDigitString(String msg, int minLength, int maxLength) {
        while (true)
            try {
                String result = getStringInputValue(msg);
                if (result.length() < minLength || result.length() > maxLength)
                    throw new Exception();
                Integer.parseInt(result);
                return result;
            } catch (Exception e) {
                Printer.printErrorMessage("Invalid input!");
            }
    }

    public static int getIntInputValue(String msg) {
        while (true)
            try {
                return Integer.parseInt(getStringInputValue(msg));
            } catch (Exception e) {
                Printer.printErrorMessage("Invalid input!");
            }
    }

    public static long getLongInputValue(String msg) {
        while (true)
            try {
                return Long.parseLong(getStringInputValue(msg));
            } catch (Exception e) {
                Printer.printErrorMessage("Invalid input!");
            }
    }

    public static double getDoubleInputValue(String msg) {
        while (true)
            try {
                return Double.parseDouble(getStringInputValue(msg));
            } catch (Exception e) {
                Printer.printErrorMessage("Invalid input!");
            }
    }
}

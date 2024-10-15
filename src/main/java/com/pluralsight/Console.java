package com.pluralsight;

import java.util.Scanner;

public class Console {

    static Scanner scanner = new Scanner(System.in);

    public static String PromptForString(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static String PromptForString(){
        return scanner.nextLine();
    }

    public static boolean PromptForYesNo(String prompt){
        System.out.print(prompt + " ( Y for Yes, N for No ) ?");
        String userInput = scanner.nextLine();

        return
                (
                        userInput.equalsIgnoreCase("Y")
                                ||
                                userInput.equalsIgnoreCase("YES")
                );

    }

    public static short PromptForShort(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        short userInput = Short.parseShort(value);
        return userInput;
    }

    public static int PromptForInt(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        int userInput = Integer.parseInt(value);
        return userInput;
    }

    public static double PromptForDouble(String prompt){
        System.out.print(prompt);
        String userInputs = scanner.nextLine();
        double userInput = Double.parseDouble(userInputs);
        return userInput;
    }

    public static byte PromptForByte(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        byte userInput = Byte.parseByte(value);
        return userInput;
    }

    public static byte PromptForByte(){
        String value = scanner.nextLine();
        byte userInput = Byte.parseByte(value);
        return userInput;
    }

    public static float PromptForFloat(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        float userInput =Float.parseFloat(value);
        return  userInput;
    }

}


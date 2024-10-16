package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
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
        String boolInput = scanner.nextLine();

        return
                (
                        boolInput.equalsIgnoreCase("Y")
                                ||
                                boolInput.equalsIgnoreCase("YES")
                );

    }

    public static short PromptForShort(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        short shortInput = Short.parseShort(value);
        return shortInput;
    }

    public static int PromptForInt(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        int intInput = Integer.parseInt(value);
        return intInput;
    }

    public static int PromptForInt(){
        String value = scanner.nextLine();
        int intInput = Integer.parseInt(value);
        return intInput;
    }

    public static double PromptForDouble(String prompt){
        System.out.print(prompt);
        String userInputs = scanner.nextLine();
        double doubleInput = Double.parseDouble(userInputs);
        return doubleInput;
    }

    public static byte PromptForByte(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        byte byteInput = Byte.parseByte(value);
        return byteInput;
    }

    public static byte PromptForByte(){
        String value = scanner.nextLine();
        byte byteInput = Byte.parseByte(value);
        return byteInput;
    }

    public static float PromptForFloat(String prompt){
        System.out.print(prompt);
        String value = scanner.nextLine();
        float floatInput =Float.parseFloat(value);
        return floatInput;
    }

    public static LocalDate PromptForDate(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine();
        LocalDate dateInput = LocalDate.parse(value);
        return dateInput;
    }

    public static LocalTime PromptForTime(String prompt) {
        System.out.print(prompt);
        String value = scanner.nextLine();
        LocalTime timeInput = LocalTime.parse(value);
        return timeInput;
    }
}


package com.clara;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by we4954cp on 8/31/2016.
 */
public class CreditCard {

    static Scanner stringScanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Ask user for credit card number. store number as a String.
        System.out.println("Please enter the credit card number, digits only:");
        String ccNumber = stringScanner.nextLine();
        boolean isValid = isValidCreditCard(ccNumber);

        if (isValid) {
            System.out.println("This seems to be a valid credit card number");
        } else {
            System.out.println("This is **not** a valid credit card number.");
        }

        stringScanner.close();
    }

    public static boolean isValidCreditCard(String cc) {

        // covert the string input into a char array
        char[] numericChars = cc.toCharArray();

        // define a new integer array with a length based on number of digits in string/char array
        int[] digits = new int[ numericChars.length ];

        // convert the char data to integer and populate the numbers array
        for (int i=0; i < numericChars.length; i++) {
            digits[i] = numericChars[i] - 48;
        }

        // Visa ccCards must start with a 4 and be exactly 16 digits long
        if ( digits[0] != 4 || digits.length != 16 ) {
            return false;
        }

        int sum = 0;
        // scan through each element of the digits array
        // starting with position 0 and proceeding to every other element, double the value of the element
        // sum up the digits, if greater then 10, sum the individual digits - e.g. if value 12, sum 1 + 2
        // starting with position 1 and proceeding to every other element, sum the element value
        for (int i = 0; i < digits.length; i++) {

            // second version of check digit algorithm - shorter and more efficient but less readable (?)
            // some ideas from http://www.freeformatter.com/credit-card-number-generator-validator.html

            if (i % 2 == 0) {  // array element position 0, 2, 4, 6, etc.

                int doubled = digits[i] * 2;

                if (doubled < 10) {
                    sum += doubled;
                }
                if (doubled >= 10) {
                    sum += doubled - 9;  // this is the equivalent of adding the two digits
                }
            } else {  // array element position 1, 3, 5, 7, 9, etc.
                sum += digits[i];
            }

            // first working version of check digit algorithm
            /*
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 8 || i == 10 || i == 12 || i == 14) {

                doubled = numbers[i] * 2;

                if (doubled < 10) {
                    sum = sum + doubled;
                } else if (doubled == 10) {
                    sum = sum + 1; // + 1
                } else if (doubled == 12) {
                    sum = sum + 3; // 1 + 2
                } else if (doubled == 14) {
                    sum = sum + 5; // 1 + 4
                } else if (doubled == 16) {
                    sum = sum + 7; // 1 + 6
                } else if (doubled == 18) {
                    sum = sum + 9; // 1 + 8
                }
            } else {
                sum = sum + numbers[i];
            } */
        }

        // print out the results
        /*
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("array element " + i + " value: " + numbers[i]);
        }
        System.out.println("array printed as a string: " + Arrays.toString(numbers));
        System.out.println("sum = " + sum + " sum % 10 = " + sum % 10);
        */

        return (sum % 10 == 0); // if the sum mod 10 is zero, return true, otherwise return false
    }
}

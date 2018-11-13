package cst154.emcc.utc.edu;

/**********
 * Main.java
 * Mike Preble
 *
 * Convert lottery winnings that result in $0.01 every second
 * to a specific timestamp based on a user inputted value
 *
 */


/*************
 * TODO
 * - output general, user friendly text for dates and times
 *      - break days and hours into 1/3 and output appropriate, early, middle, or end
 *        of the day or month
 * - output "th", "nd", "rd" or "st" after the day number
 *      - use n%10 to isolate the number in the ones place
 * - output "you will have a fun (spring, summer, fall winter) based on the month
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //initialize user input values
        Scanner userInput = new Scanner(System.in);
        int userDollarValue = 0;
        int userCentsValue = 0;

        //calculate seconds for each time frame
        //declare them as final so they cannot be changed after initialization
        final int yearSeconds = 60*60*24*30*12;
        final int monthSeconds = 60*60*24*30;
        final int daySeconds = 60*60*24;
        final int hourSeconds = 60*60;

        //Declare a decimal format to add commas to outputted numbers
        DecimalFormat addCommas = new DecimalFormat("###,###");

        /******
         * Begin initial output
         */
        System.out.print("-------------------------------");
        System.out.print("\n||     Lottery Calculator    ||");
        System.out.print("\n-------------------------------\n");


        //prompt the user for the money they want and turn into cents/seconds
        System.out.println("\nHow much money do you want?");
        userDollarValue = userInput.nextInt();
        userCentsValue = userDollarValue*100;

        /*******
         * End initial output
         */

        /*************
         * Begin whole number/left over seconds pattern
         *
         * pattern:
         * A: whole number: the product of remaining seconds / total seconds for a given time frame
         * B: left over seconds: the remainder of remaining seconds / total seconds for given time frame
         *
         * example
         * A: years = 100000000 / 31104000
         *     3 = 100000000 / 3110400
         * B: yearsRemainder = 100000000 % 31104000
         *     6688000 = 100000000 % 31104000
         * ----
         * A: months = 6688000 / 2592000
         *     2 = 6688000 / 2592000
         * B: monthsRemainder = 6688000 % 2592000
         *    1504000 = 6688000 % 2592000
         *
         *
         */
        int years = 2019 + userCentsValue/yearSeconds;
        int yearRemainder = userCentsValue % yearSeconds;

        int months = yearRemainder / monthSeconds;
        int monthRemainder = yearRemainder % monthSeconds;

        int days = monthRemainder / daySeconds;
        int dayRemainder = monthRemainder % (daySeconds);

        int hours = dayRemainder / hourSeconds;
        int hourRemainder = dayRemainder % hourSeconds;

        int minutes = hourRemainder / 60;
        int secondsLeft = hourRemainder % 60;

        /**********
         * End whole number/left over seconds pattern
         */

        String textMonth = null;
        //String textMonth;

        if (months == 1){
            textMonth = "January";
        } else if (months == 2){
            textMonth = "February";
        } else if (months == 3){
            textMonth = "March";
        } else if (months == 4){
            textMonth = "April";
        } else if (months == 5) {
            textMonth = "May";
        } else if (months == 6){
            textMonth = "June";
        } else if (months == 7){
            textMonth = "July";
        } else if (months == 8){
            textMonth = "August";
        } else if (months == 9){
            textMonth = "September";
        } else if (months == 10){
            textMonth = "October";
        } else if (months == 11){
            textMonth = "November";
        } else if (months == 12){
            textMonth = "December";
        }

        //output values to the user
        System.out.println("It will take you " +addCommas.format(userCentsValue)+ " seconds to make $" +addCommas.format(userDollarValue)+
                "\nThis is the exact moment in time you'll make it: ";

        System.out.println(textMonth+", "+days+ ", "+years+
                " at "+hours+ ":" +minutes+":"+secondsLeft);
    }
}
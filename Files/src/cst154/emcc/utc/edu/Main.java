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
 *  - add more if/else statements for month
 *  - add more if/else statements for day
 *  - format the time in HH:MM:SS format
 *  - refactor double to int for accuracy
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //initialize user input values
        Scanner userInput = new Scanner(System.in);
        double userDollarValue = 0;
        double userCentsValue = 0;

        //calculate seconds for each time frame
        //declare them as final so they cannot be changed after initialization
        final double yearSeconds = 60*60*24*30*12;
        final double monthSeconds = 60*60*24*30;
        final double daySeconds = 60*60*24;
        final double hourSeconds = 60*60;

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
        userDollarValue = userInput.nextDouble();
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
        double years = 2019 + userCentsValue/yearSeconds;
        double yearRemainder = userCentsValue % yearSeconds;

        double months = yearRemainder / monthSeconds;
        double monthRemainder = yearRemainder % monthSeconds;

        double days = monthRemainder / daySeconds;
        double dayRemainder = monthRemainder % (daySeconds);

        double hours = dayRemainder / hourSeconds;
        double hourRemainder = dayRemainder % hourSeconds;

        double minutes = hourRemainder / 60;
        double secondsLeft = hourRemainder % 60;

        /**********
         * End whole number/left over seconds pattern
         */

        String textMonth = null;
        //String textMonth;

        if ((int)months == 1){
            textMonth = "January";
        } else if ((int)months == 2){
            textMonth = "February";
        }

        //output values to the user
        System.out.println("It will take you " +addCommas.format(userCentsValue)+ " seconds to make $" +addCommas.format(userDollarValue)+
                "\nThis is the exact moment in time after 1/1/2019 at 00:00:00 that you will make $"+addCommas.format(userDollarValue));
        System.out.println("\nyear = "+(int)years+
                "\nmonth = "+textMonth+
                "\nday = "+(int)days+
                "\nhour = "+(int)hours+
                "\nminute = "+(int)minutes+
                "\nsecond = "+(int)secondsLeft);
    }
}
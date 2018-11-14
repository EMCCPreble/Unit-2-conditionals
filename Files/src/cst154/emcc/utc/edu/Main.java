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
 * TODO (Code)
 *  - Refactor final variables to meet proper code naming convention (ALL_CAPS_UNDERSCORES)
 *
 * TODO (WHILE)
 *  - output a yearly, monthly, daily, or hourly breakdown based on what the user enters
 *    - input the dollar amount from the user
 *      - figure out if it will take more than a year to make the money
 *      - figure out if it will take more than a month to make the money
 *      - figure out if it will take more than a day to make the money
 *      - figure out if it will take more than an hour to make the money
 *    - once the largest time frame is determined, output how much will be made for each timeframe
 *      making sure to output the remaining amount in the last year accurately
 *
 * TODO (SWITCH)
 *  - Change ladder if/else statements to Switch
 *      - Months 0 and 1 are done, continue through
 *
 * TODO (Development)
 * incorporate the following switch statement to return the correct number of days in a month
 * (https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html)
 * int numDays = 0;
 * switch(months){
 *     case 0: case 2: case 4: case 6: case 7: case 9: case 11:
 *          numDays = 31;
 *          break;
 *     case 3: case 5: case 8: case 10:
 *          numDays = 30;
 *          break;
 *     case 1:
 *          if (((years % 4 ==0) && !(years % 100 == 0)) || (years % 400 == 0))
 *              numDays = 29;
 *          else
 *              numDays = 28;
 *          break;
 *     default:
 *          numDays = 31;
 *          break;
 *  }
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
        final int YEAR_SECONDS = 60*60*24*30*12;
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
        int years = 2019 + userCentsValue/YEAR_SECONDS;
        int yearRemainder = userCentsValue % YEAR_SECONDS;

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

        //if (months == 0){
        //    textMonth = "January";
        //} else if (months == 1){
           // textMonth = "February";
       // } else
           if (months == 2){
            textMonth = "March";
        } else if (months == 3){
            textMonth = "April";
        } else if (months == 4) {
            textMonth = "May";
        } else if (months == 5){
            textMonth = "June";
        } else if (months == 6){
            textMonth = "July";
        } else if (months == 7){
            textMonth = "August";
        } else if (months == 8){
            textMonth = "September";
        } else if (months == 9){
            textMonth = "October";
        } else if (months == 10){
            textMonth = "November";
        } else if (months == 11){
            textMonth = "December";
        } else {
            textMonth = "Something broke, fix it";
        }

        switch (months){
            case 0:
                textMonth = "January";
                break;
            case 1:
                textMonth = "February";
                break;
        }

        //output values to the user
        System.out.println("It will take you " +addCommas.format(userCentsValue)+ " seconds to make $" +addCommas.format(userDollarValue)+
                "\nThis is the exact moment in time you'll make it: ");

        System.out.println(textMonth+" "+(days+1)+ ", "+years+
                " at "+hours+ ":" +minutes+":"+secondsLeft);

        if (years-2019 > 1)
        {
            int yearDollars = YEAR_SECONDS / 100;
            int count = 1;
            int tempDollarValue = 0;


            while (tempDollarValue < userDollarValue){
                tempDollarValue += yearDollars;

                System.out.println ("Year "+count+" amount: $" + addCommas.format(tempDollarValue));
                count++;
            }
        }
    }
}

//TODO (Bugs)
//TempDollarValue amount outputting higher than the user entry in the while loop
//Days of the week not displayed correctly (0 = Sunday, 1 = Monday ....)

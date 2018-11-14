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
 * TODO (Structure)
 * - Lottery App 1.1
 *      - Give user ability to use a lottery calculator or lottery number generator
 *          - once in the calculator, give the user the ability to view the breakdown or not
 *
 * TODO (Array)
 *  - Build lottery number generator 1.0
 *    - Manually fill array with 7 numbers between 0 - 80
 *    - Loop through array using For loop to output them
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

        System.out.print("-------------------------------");
        System.out.print("\n||     Mike's Lottery App   ||");
        System.out.print("\n-------------------------------\n");

        System.out.println("Press 1 for calculator");
        System.out.println("Press 2 for number generator");
        int userChoice = userInput.nextInt();
        System.out.print("\033[H\033[2J");
        System.out.flush();

        if (userChoice == 1)
        {
            int userDollarValue = 0;
            int userCentsValue = 0;

            //calculate seconds for each time frame
            //declare them as final so they cannot be changed after initialization
            final int YEAR_SECONDS = 60*60*24*30*12;
            final int MONTH_SECONDS = 60*60*24*30;
            final int DAY_SECONDS = 60*60*24;
            final int HOUR_SECONDS = 60*60;

            //Declare a decimal format to add commas to outputted numbers
            DecimalFormat addCommas = new DecimalFormat("###,###");

            /******
             * Begin initial lottery calculator output
             */
            System.out.print("-------------------------------");
            System.out.print("\n||     Lottery Calculator    ||");
            System.out.print("\n-------------------------------\n");


            //prompt the user for the money they want and turn into cents/seconds
            System.out.println("\nHow much money do you want?");
            userDollarValue = userInput.nextInt();
            userCentsValue = userDollarValue*100;

            /*******
             * End initial lottery calculator output
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

            int months = yearRemainder / MONTH_SECONDS;
            int monthRemainder = yearRemainder % MONTH_SECONDS;

            int days = monthRemainder / DAY_SECONDS;
            int dayRemainder = monthRemainder % (DAY_SECONDS);

            int hours = dayRemainder / HOUR_SECONDS;
            int hourRemainder = dayRemainder % HOUR_SECONDS;

            int minutes = hourRemainder / 60;
            int secondsLeft = hourRemainder % 60;

            /**********
             * End whole number/left over seconds pattern
             */

            String textMonth = null;

            switch (months){
                case 0:
                    textMonth = "January";
                    break;
                case 1:
                    textMonth = "February";
                    break;
                case 2:
                    textMonth = "March";
                    break;
                case 3:
                    textMonth = "April";
                    break;
                case 4:
                    textMonth = "May";
                    break;
                case 5:
                    textMonth = "June";
                    break;
                case 6:
                    textMonth = "July";
                    break;
                case 7:
                    textMonth = "August";
                    break;
                case 8:
                    textMonth = "September";
                    break;
                case 9:
                    textMonth = "October";
                    break;
                case 10:
                    textMonth = "November";
                    break;
                case 11:
                    textMonth = "December";
                    break;
                default:
                    textMonth = "Oh no, I messed up";
                    break;
            }

            //output values to the user
            System.out.println("It will take you " +addCommas.format(userCentsValue)+ " seconds to make $" +addCommas.format(userDollarValue)+
                    "\nThis is the exact moment in time you'll make it: ");

            System.out.println(textMonth+" "+(days+1)+ ", "+years+
                    " at "+hours+ ":" +minutes+":"+secondsLeft);
            System.out.println("Press 1 to view breakdown");

            int userViewBreakdown = userInput.nextInt();

            if (userViewBreakdown == 1)
            {
                int count = 1;
                int tempDollarValue = 0;

                if (years-2019 > 1)
                {
                    int yearDollars = YEAR_SECONDS / 100;

                    while (tempDollarValue < userDollarValue)
                    {
                        tempDollarValue += yearDollars;

                        System.out.println ("Year "+count+" amount: $" + addCommas.format(tempDollarValue));
                        count++;
                    }

                } else if (months > 1)
                {
                    int monthDollars = MONTH_SECONDS / 100;

                    while (tempDollarValue < userDollarValue)
                    {
                        tempDollarValue += monthDollars;
                        System.out.println ("Month "+count+" amount :$" + addCommas.format(tempDollarValue));
                    }

                } else if (days > 1)
                {
                    int dayDollars = DAY_SECONDS / 100;

                    while (tempDollarValue < userDollarValue)
                    {
                        tempDollarValue += dayDollars;
                        System.out.println("Day "+count+" amount :$" +addCommas.format(tempDollarValue));
                    }
                } else if (hours > 1)
                {
                    int hourDollars = HOUR_SECONDS / 100;
                    while (tempDollarValue < userDollarValue)
                    {
                        tempDollarValue += hourDollars;
                        System.out.println("Hour "+count+" amount :$" +addCommas.format(tempDollarValue));
                    }
                } else if (userChoice == 2)
                {
                    //Begin lottery number generator

                    //End lottery number generator
                }
            }
        }
    }
}

//TODO (Bugs)
//TempDollarValue amount outputting higher than the user entry in the while loop
//Days of the week not displayed correctly (0 = Sunday, 1 = Monday ....)

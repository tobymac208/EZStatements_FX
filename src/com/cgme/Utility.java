package com.cgme;

import com.cgme._Statement.Statement;

import java.util.Scanner;

@Deprecated
/** Gives a user the ability to use small, quick static functions to do necessary operations.
 * Author: Nik Fernandez
 * Created: 08/25/2019
 */
public class Utility{
    /** Rounds a number to two decimal places */
    public static double round_double(double value){
        return Math.round(value * 100.00) / 100.00;
    }

    @Deprecated
    /** Prompts a user for creating a new statement.
     * Precondition: Enter valid information.
     * Post-condition: Receive an initialized Statement object. */
    static Statement add_new_statement(){
        Scanner input = new Scanner(System.in);

        System.out.println("CREATE A NEW STATEMENT");
        System.out.println("Note: You must create a statement with a new name. NO copies.");

        // Ask for the name
        System.out.print("Please enter the name of the statement (mm/dd/yyyy format): ");
        String name = input.nextLine();

        // Ask for the "consolidated" flag
        System.out.print("Has the statement been dealt with already? (Enter Y/N): ");
        boolean actual_flag;
        String consolidated_flag = input.nextLine();

        if(consolidated_flag.equals("Y") || consolidated_flag.equals("y")) {
            actual_flag = true;
        }else if(consolidated_flag.equals("N") || consolidated_flag.equals("n")){
            actual_flag = false;
        }else {
            System.out.println("Invalid entry. You entered: " + consolidated_flag);
            return null;
        }

        System.out.print("Please enter the amount, in $, of the statement: ");
        double amount_of_statement = Double.parseDouble(input.nextLine());

        // Statement creation successful!
        return new Statement(name, actual_flag, amount_of_statement);
    }

    /** Make some specified space in the terminal. Value == number of lines */
    @Deprecated
    static void clearTerminal(int value){
        for(int i = 0; i < value; i++){
            System.out.println();
        }
    }
}
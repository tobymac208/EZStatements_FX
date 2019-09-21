package com.cgme.POJO._Statement;

import com.cgme.Utility;

public class StatementTracker {
    private Statements the_statements;
    private String title_of_tracker;

    public StatementTracker(String title_of_tracker){
        the_statements = new Statements();
        this.title_of_tracker = title_of_tracker;
    }

    public Statements getStatements(){
        return the_statements;
    }

    /** Add a statement to the list. */
    public void addStatement(Statement value){
        the_statements.addStatement(value);
    }

    /** Remove a statement from the list. */
    public boolean removeStatement(Statement value){
        return the_statements.removeStatement(value);
    }

    /** Remove a statement from the list. */
    public boolean removeStatement(int value){
        return the_statements.removeStatement(value);
    }

    /** Returns a detailed list of all the Statement data. */
    public String printStatementData(){
        if(the_statements.getStatements().size() < 1){
            System.out.println("No data to examine.");
            return null; // return to the caller.
        }

        System.out.println("Data for tracker \"" + title_of_tracker + "\"");

        // This is a total counter for all of the statements.
        int count = 0;

        // This is what will be returned
        String bufferedString = "";

        // Local variables for tracking calculations and totals
        float totalAmountOwed = 0;
        float totalAmountPaid = 0;
        float totalAmount = 0;
        float percentagePaid;

        // Iterate through our list
        for(Statement current_statement : the_statements.getStatements()){
            // For each Statement, add the data to the string
            bufferedString += "[" + count + "] " + current_statement.getDate() +
                    "\n\t\tAmount: $" + current_statement.getPriceOfStatement() +
                    "\n\t\tConsolidated: " + current_statement.isConsolidated() + "\n";

            // Add to the total number of Statements
            count++;

            // Add to the total amount of money involved
            totalAmount += current_statement.getPriceOfStatement();

            // Checks whether the statement is settled or not
            if( !(current_statement.isConsolidated()) ){ // is the current statement not dealt with?
                totalAmountOwed += current_statement.getPriceOfStatement();
            }else{
                totalAmountPaid += current_statement.getPriceOfStatement();
            }
        }
        // Calculate the percentage paid
        percentagePaid = (totalAmountPaid / totalAmount) * 100;

        bufferedString += "\n";

        // Add the final data
        bufferedString += "Data collected from " + count + " entry/entries.\n";
        bufferedString += "Total amount owed: $" + Utility.round_double(totalAmountOwed) + "\n";
        bufferedString += "Total amount paid: $" + Utility.round_double(totalAmountPaid) + "\n";
        bufferedString += "% paid: " + Utility.round_double(percentagePaid) + "%\n\n";

        return bufferedString; // return the string to the user
    }



    public void setAllStatements(Statements statements){
        this.the_statements = statements;
    }
}
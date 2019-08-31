package com.cgme.POJO._Statement;

public class Statement{
    // Name for the statement
    private String date;
    // Flag for checking whether the statement has been dealt with
    private boolean statement_consolidated;
    // Amount of money for the statement
    private double price_of_statement;

    /** Default, all-arg constructor. */
    public Statement(String date, boolean statement_consolidated, double price_of_statement){
        this.date = date;
        this.statement_consolidated = statement_consolidated;
        this.price_of_statement = price_of_statement;
    }

    /** Name getter */
    public String getDate(){
        return date;
    }

    /** Statement flag getter */
    public boolean isConsolidated(){
        return statement_consolidated;
    }

    /** Statement flag setter */
    public void setConsolidated(boolean value){
        statement_consolidated = value;
    }

    /** Amount for statement getter */
    public double getPriceOfStatement(){
        return price_of_statement;
    }

    /** Checks if two statements are the same.
     * Precondition: Pass in an initialized Statement
     * Post-condition: Receive a true or false value. */
    boolean equals(Statement value){
        return value.getDate().equals(date);
    }
}
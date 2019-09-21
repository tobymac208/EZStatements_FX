package com.cgme.POJO._Statement;

import java.util.ArrayList;

public class Statements{
    private ArrayList<Statement> list_of_statements;

    /** Default, all-arg constructor. */
    public Statements(){
        list_of_statements = new ArrayList<>();
    }

    /** Adds a statement to the list.
     * Precondition: Pass in a valid Statement. No copied titles.
     * Postcondition: Successfully add a Statement to the list.
     */
    public void addStatement(Statement value){
        for(Statement current_statement : list_of_statements){
            if(current_statement.equals(value)){
                System.out.println("Copy attempted. Name: " + current_statement.getDate());
                return;
            }
        }

        // Statement successfully added.
        list_of_statements.add(value);
    }

    /** Removes a statement from the list */
    boolean removeStatement(Statement value){
        for(int i = 0, length = list_of_statements.size(); i < length; i++){
            if(list_of_statements.size() > 0 && list_of_statements.get(i).equals(value)){ // does that Statement exist?
                list_of_statements.remove(i); // remove that statement from the list
                return true;
            }
        }

        System.out.println("Statement not deleted successfully.");
        return false;
    }

    /** Remove a statement from the list. */
    boolean removeStatement(int value){
        if(value > list_of_statements.size() - 1 || value < 0){
            return false;
        }else{
            list_of_statements.remove(value);
            return true;
        }
    }

    /** Returns an ArrayList of statements. */
    public ArrayList<Statement> getStatements(){
        // Return the statements
        return new ArrayList<>(list_of_statements);
    }
}

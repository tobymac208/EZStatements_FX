package com.cgme;

import com.cgme.POJO._Statement.Statement;
import com.cgme.POJO._Statement.Statements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileOperations {
    private static final String FILENAME = "src/com/cgme/data.txt";

    /** Read in a list of Statements and returns it to the caller
     * Precondition: Have data in FILENAME
     * Post-condition: Read in a list of statements */
    public static Statements read_in_all_statements(){
        Statements statements = new Statements();

        // Create a file to read data from
        File file = new File(FILENAME);

        if( !(file.exists()) ){
            try{
                if(file.createNewFile()) { // create the file with the name we already specified above, and checks if it was created
                    System.out.println("File created.");
                }else{
                    System.out.println("File failed to create.");
                }
            }catch(IOException e){
                System.out.println("Failed to create file.");
                return null;
            }
        }

        // Attempt to read from the file
        try(Scanner reader = new Scanner(file)){
            while(reader.hasNextLine()){
                // Read in the current line
                String current_line = reader.nextLine();

                // Split the string into parts
                String[] current_line_components = current_line.split(", ");

                // Get the name component
                String name = current_line_components[0];

                // Get the boolean component
                boolean isConsolidated = current_line_components[1].equals("true");

                // Get the price of statement component
                double priceOfStatement = Double.parseDouble(current_line_components[2]);

                Statement statement = new Statement(name, isConsolidated, priceOfStatement);
                statements.addStatement(statement);
            }

            return statements;
        }catch(IOException e){
            System.out.println("An exception occurred.");
            return null;
        }
    } // end of read_in_all_statements()

    /** Write all statements, contained in Statements, to FILENAME */
    public static void write_data_to_file(Statements value){
        // Create a file to read data from
        File file = new File(FILENAME);

        if( !(file.exists()) ){
            try{
                if(file.createNewFile()) { // create the file with the name we already specified above, and checks if it was created
                    System.out.println("File created.");
                }else{
                    System.out.println("File failed to create.");
                }
            }catch(IOException e){
                System.out.println("Failed to create file.");
                return;
            }
        }

        try(PrintWriter writer = new PrintWriter(file)){
            for(Statement current_statement : value.getStatements()){
                writer.println(current_statement.getName() + ", " + current_statement.isConsolidated() + ", "
                        + current_statement.getPriceOfStatement());
            }

            System.out.println("Data successfully written to file.");
        }catch(IOException e){
            System.out.println("An exception occurred.");
        }
    } // end of write_data_to_file()
}

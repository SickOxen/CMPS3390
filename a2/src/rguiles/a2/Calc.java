package rguiles.a2;

/**
 * Calc class used by Main
 * @author Richard Guiles
 * @version 1.0
 */

public class Calc
{
    /**
     * Static Function that Evaluates Math Problems
     * @param line   // String input of problem to be solved
     * @return       // String of returned answer to problem
     */

    public static String evaluate(String line)
    {
        double partA, partB;
        String[] parts = line.split(" ");   // Split expression into three separate parts
        if(parts.length != 3) { return "Invalid Input";}

        // Check parts[0] is a double
        try{
            partA = Double.parseDouble(parts[0]);
        }catch (java.lang.NumberFormatException ex){
            return "Invalid Input";
        }

        // Check parts[1] is an operator
        if(!parts[1].equals("+") && !parts[1].equals("-")
                && !parts[1].equals("*") && !parts[1].equals("/")){
            return "Invalid Input";
        }

        // Check parts[2] is a double
        try{
            partB = Double.parseDouble(parts[2]);
        }catch (java.lang.NumberFormatException ex){
            return "Invalid Input";
        }

        // Input is valid if this portion of program is reached
        switch(parts[1]){
            case "+":
                return Double.toString(partA + partB);
            case "-":
                return Double.toString(partA - partB);
            case "*":
                return Double.toString(partA * partB);
            case "/":
                return Double.toString(partA / partB);
        }

        return "Invalid Input";
    }
}

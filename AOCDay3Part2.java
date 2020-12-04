import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * AOC Day3
 * Tobaggon through the trees
 * 
 * @author Jeff Fitzgerald
 * @version 12/03/2020
 */

public class AOCDay3Part2 {

    /**
     * Reads the input file of trees and open squares.
     * 
     * @param filename name of file to read
     * @return an array of input values
     */
    public static Character[][] readFile(String fileName) {

        ArrayList<String> treeLines = new ArrayList<>();
        Scanner inFile = null;
        Character[][] treeLineCharacters;

        try {
            inFile = new Scanner(new File(fileName));
            
            while (inFile.hasNext()) {
                treeLines.add(inFile.nextLine());
            }

        } catch (IOException e) {
            System.out.println("Error reading input file: " + fileName);
            System.exit(0);

        } finally {
            if (inFile != null) {
                inFile.close();
            }
        }

        // Change to a 2d array
        treeLineCharacters = new Character[treeLines.size()][treeLines.get(0).length()];
        for(int i = 0; i < treeLines.size(); i++){
            for(int j = 0; j < treeLines.get(0).length(); j++){
                treeLineCharacters[i][j] = treeLines.get(i).charAt(j);
            }
        }

        return treeLineCharacters;
    }

      
    public static void main(String[] args) {
        String fileName = "AOCDay3Input.txt";
        Character[][] alltreeLines = readFile(fileName); // characters read in, in a 2d array
        int openSquares = 0;
        int trees = 0;
        int neither = 0;
        int posX = 0;
        int treeLineLength = alltreeLines[0].length;  // length of each line
        int[] results = new int[5];  // stores results for each iteration
        int[][] slopes = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};  // slopes for each iteration
        long resultProduct = 1;  // need a larger value than int

        // Iteration for each slope
        for(int i = 0; i < 5; i++){
            // Loop through all characters
            for(int posY = slopes[i][1]; posY < alltreeLines.length; posY += slopes[i][1]){
                posX += slopes[i][0];
                if(posX >= treeLineLength){
                    posX = posX - treeLineLength;
                }
                // Check value of character
                switch (alltreeLines[posY][posX]) {
                    case '.':  
                        openSquares++;
                        break;
                    case '#':
                        trees++;
                        break;
                    default:
                        neither++;
                        break;
                }
            }
            System.out.println("Round " + i);
            System.out.println("The number of trees is " + trees);
            System.out.println("The number of open squares is " + openSquares);
            System.out.println("The number of neithersymbols is " + neither);
            System.out.println("The number of tree lines is " + alltreeLines.length);
            
            results[i] = trees;  // Store result of current iteration
            // reset values for next iteration
            openSquares = 0;
            trees = 0;
            neither = 0;
            posX = 0;
        }
        // find product of result
        for(int i = 0; i < results.length; i++) {
            resultProduct *= results[i];
        }
        System.out.println("Product of results = " + resultProduct);
    }
}
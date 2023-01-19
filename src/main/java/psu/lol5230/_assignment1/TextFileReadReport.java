/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psu.lol5230._assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author luke.leiter
 */
public class TextFileReadReport {

    private String[] fileLines;

    /**
     * Get the value of fileLines
     *
     * @return the value of fileLines
     */
    public String[] getFileLines() {
        return fileLines;
    }

    /**
     * Set the value of fileLines
     *
     * @param fileLines new value of fileLines
     */
    public void setFileLines(String fileLines) {

    }

    /**
     * main() accepts a single commmand line parameter.
     *
     * @param args[0] path to the input text file.
     * @param args[1] is the number of characters the user desires to be
     * analyzed
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Missing at least one command line argument");
            System.out.println("Usage java TextFileReadReport filename numberOfCharToRead");
            System.exit(1);
        }
        String lines;
        // Read text file into a String
        lines = readTextFile(args[0]);
        //Check that the user input for read to index is valid
        try {
            if (!userFileLengthValid(lines, Integer.valueOf(args[1]))) {
                System.out.println("Invalid number of characters to review, try a smaller number");
                System.exit(1);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Make sure to only input whole numbers for the second argument");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Something unexpected happened, try again");
            System.exit(1);
        }

        String numberOfWords = "The numbers of word in this file: "
                + numberOfWords(getFileLines(lines));
        System.out.println(numberOfWords);

        String numberOfNumbers = "The number of numbers in this file: "
                + getNumberOfNumbers(lines);
        System.out.println(numberOfNumbers);

        String numberOfCharacters = "The number of characters in this file: "
                + getNumberOfCharacters(lines);
        System.out.println(numberOfCharacters);

        String numberOfSpaces = "The number of spaces in this file: "
                + getNumberOfSpaces(lines);
        System.out.println(numberOfSpaces);

        String numberOfPunctuation = "The number of punctuation in this file: "
                + getNumberOfPunctuation(lines);
        System.out.println(numberOfPunctuation);

        String numberOfUpperCase = "The number of uppercase in this file: "
                + getNumberOfUpperCase(lines);
        System.out.println(numberOfUpperCase);

        String numberOfLowerCase = "The number of uppercase in this file: "
                + getNumberOfLowerCase(lines);
        System.out.println(numberOfLowerCase);
        
        String reversedText = reverseText(lines);
        System.out.println(reversedText);

// Write file from string to text file
        //printTextFile(lines);
    }

    /**
     * readTextFile() reads the contents of a text file and returns a long
     * string containing the lines of the file.
     *
     * @param filePath path to the input file.
     * @return string with all lines from file.
     */
    private static String readTextFile(String filePath) {
        String lines = "";
        String line = "";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(filePath);
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                // Don't forget to add the newline at the end.
                lines = lines + line + "\n";
            }
            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filePath + "'");
            System.exit(2);
        } catch (IOException ex) {
            System.out.println("Error reading file '" + filePath + "'");
            System.exit(3);
        }
        return lines;
    }

    /**
     * printTextFile() prints the string line by line.
     *
     * @param lines string with the lines of the input file. Each line is
     * separated by a linefeed.
     */
    private static void printTextFile(String lines) {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(lines));
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading string");
            System.exit(4);
        }
    }
    
    /**
     * reverseText takes in a string and returns a string of that text in reverse 
     * order. The returned string will be all lowercase.
     *
     * @param lines a String
     * @return a String, all lower case
     */
    private static String reverseText(String lines)
    {
        String reversedText = "";
        
        for (int i = lines.length()-1; i >= 0; i--)
        {
            reversedText += lines.charAt(i);
        }
        
        return reversedText.toLowerCase();
    }

    /**
     * userFileLengthValid() checks the file length with a given index with a
     * given string
     *
     * @param lines string of the File the user wants to analyze
     * @param possibleLength int of the number of characters the user wants to
     * analyze
     */
    private static Boolean userFileLengthValid(String lines, int possibleLength) {
        System.out.println(lines.length());
        return lines.length() >= possibleLength;
    }

    /**
     * numberOfWords() returns the number of words in all the elements of a
     * given String array (String [])
     *
     * @param fileLines a String[]
     * @return an integer
     */
    private static int numberOfWords(String[] fileLines) {
        int numberOfWords = 0;
        for (int i = 0; i < fileLines.length; i++) {

            String[] words = fileLines[i].trim().split(" ");
            for (String word : words) {
                if (word.matches("[a-zA-Z.?!,;'\"]+")) {
                    numberOfWords++;
                }
            }
        }
        return numberOfWords;
    }

    /**
     * getFileLines return a String[] of the lines of a given String, usually
     * read from a file
     *
     * @param fileString a String to be separated
     * @return a String[] of lines in fileString
     */
    private static String[] getFileLines(String fileString) {
        return fileString.split("\n");
    }

    /**
     * getNumberOfNumbers returns and integer with the value that equals the
     * numbers of numbers in the given string
     *
     * @param string a String
     * @return an integer
     */
    private static int getNumberOfNumbers(String string) {
        int numberOfNumbers = 0;

        String noNewLines = string.replace('\n', ' ');
        String[] words = string.split(" ");
        for (String word : words) {
            if (word.matches("[0-9.?!,;'\"\n]+")) {
                numberOfNumbers++;
            }
        }

        return numberOfNumbers;
    }

    /**
     * getNumberOfCharacters returns the number of characters in a given string
     * not including endlines (\n)
     *
     * @param string a string
     * @return an integer
     */
    private static int getNumberOfCharacters(String string) {
        return string.replace("\n", "").length();
    }

    /**
     * getNumberOfSpaces returns the number of spaces present in a string
     *
     * @param string a String
     * @return an integer
     */
    private static int getNumberOfSpaces(String string) {
        int numberOfSpaces = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                numberOfSpaces++;
            }
        }

        return numberOfSpaces;
    }

    /**
     * getNumberOfPunctuation returns the number of punctuation present in the a
     * given string
     *
     * @param string a String
     * @return an integer
     */
    private static int getNumberOfPunctuation(String string) {
        int numberOfPunctuation = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '.' || c == '?' || c == '!' || c == ',' || c == ';'
                    || c == '\'' || c == '-') {
                numberOfPunctuation++;
            }
        }

        return numberOfPunctuation;
    }

    /**
     * getNumberOfUpperCase returns the number of lowercase characters in a
     * given string
     *
     * @param string a String
     * @return an integer
     */
    private static int getNumberOfUpperCase(String string) {
        int numberOfUpperCase = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (Character.isUpperCase(c)) {
                numberOfUpperCase++;
            }
        }

        return numberOfUpperCase;
    }

    /**
     * getNumberOfLowerCase returns the number of lowercase characters in a
     * given string
     *
     * @param string a String
     * @return an integer
     */
    private static int getNumberOfLowerCase(String string) {
        int numberOfLowerCase = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (Character.isLowerCase(c)) {
                numberOfLowerCase++;
            }
        }

        return numberOfLowerCase;
    }
}

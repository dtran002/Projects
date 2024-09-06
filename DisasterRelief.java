/**
* DisasterRelief.java 
*
* Program will help VCU figure out how many cargo planes and what kinds of cargo planes they need to distribute their supplies.
* Dylan Tran 
* 2/09/2024
* CMSC 255, 901
*/

import java.util.Scanner;

public class DisasterRelief {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // get input for either random generated or specific population
        System.out.println("Would you like to randomly generate a population (1) or enter it yourself (2)?");
        int choice = input.nextInt();

        // assign variables
        int pop = 0;

        // if choice = 1
        if (choice == 1) {
            pop = (int) (Math.random() * (8000000 - 5000 + 1) + 5000);
            System.out.println(pop);
        } 
        // if choice = 2
        else if (choice == 2) {
            System.out.println("Enter a population between 5000 and 8000000");
            pop = input.nextInt();
            if (pop < 5000 || pop > 8000000) {
                System.out.println("Incorrect Input");
                input.close(); // Close the Scanner
                return;
            }
        } 
        else {
            System.out.println("Incorrect Input");
            input.close(); // Close the Scanner
            return;
        }

        // Calculate cargo planes
        int pop1 = pop;
        int industrial = pop1 / (5000 * 9 * 5 * 3);
        pop1 %= (5000 * 9 * 5 * 3);

        int large = pop1 / (5000 * 5 * 3);
        pop1 %= (5000 * 5 * 3);

        int medium = pop1 / (5000 * 3);
        pop1 %= (5000 * 3);

        int small = (pop1 / 5000) + 1;


        // Output number of cargo planes
        System.out.println("To supply a population of " + pop + " people with supplies you must send out:");
        if (industrial > 0) System.out.println(industrial + " industrial cargo planes(s)");
        if (large > 0) System.out.println(large + " large cargo planes(s)");
        if (medium > 0) System.out.println(medium + " medium cargo planes(s)");
        if (small > 0) System.out.println(small + " small cargo planes(s)"); 

        // Close Scanner
        input.close();
    }
}

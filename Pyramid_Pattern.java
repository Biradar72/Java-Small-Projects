import java.util.Scanner;

public class PatternPrinting {
    
    public static void main(String[] args) {
        // Create a scanner object to take user input
        Scanner scanner = new Scanner(System.in);
        
        // Ask user for number of rows
        System.out.print("Enter the number of rows for the pyramid: ");
        int rows = scanner.nextInt();
        
        // Loop to print each row
        for (int i = 1; i <= rows; i++) {
            // Printing spaces to center the stars in the pyramid
            for (int j = i; j < rows; j++) {
                System.out.print(" ");
            }
            
            // Printing stars for the current row
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            
            // Move to the next line after each row
            System.out.println();
        }
        
        // Close the scanner
        scanner.close();
    }
}

import java.util.Scanner;

/**
 * KnapsackDriver
 * This is driver file
 * @author Biswash Kunwar
 */
class KnapsackDriver {
    public static void main(String[] args) {
        
    
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the input file name: ");
        String filename = scanner.nextLine();
        BestBoundKnapsack bestBoundKnapsack = new BestBoundKnapsack(filename);
        bestBoundKnapsack.solve();
        System.out.println("\nOutput:\n"+bestBoundKnapsack.stdOutput());

        

    }
}

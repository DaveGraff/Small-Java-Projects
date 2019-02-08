/*
 * David Graff 2019
 */
package fizzbuzz;

/**
 * FizzBuzz: For every number from 1 to n, print fizz if it is
 * divisible by 3, buzz if is divisible by 5, and fizzbuzz if it
 * is divisible by both. If it is divisible by neither, print the number.
 * @author david
 */
public class FizzBuzz {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        fizzBuzz(100);
    }
    
    public static void fizzBuzz(int n) {
        for (int i = 1; i < n + 1; i++) {
            if (i % 3 == 0)
                System.out.print("fizz");
            if (i % 5 == 0)
                System.out.print("buzz");
            else if (i % 3 != 0) 
                System.out.print(i);
            System.out.println();
        }
    }
}

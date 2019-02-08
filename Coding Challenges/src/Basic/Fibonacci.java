/*
 * David Graff 2019
 */
package fizzbuzz;

/**
 *
 * @author david
 */
public class Fibonacci {
    public static void main(String[] args){
        int val = 15;
        System.out.println(fibonacciRecursive(val));
        System.out.println(fibonacciIterative(val));
    }
    
    public static int fibonacciRecursive(int n){
        if(n < 3)
            return 1;
        return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
    }
    
    public static int fibonacciIterative(int n){
        if( n < 3)
            return 1;
        int[] fibs = new int[n];
        fibs[0] = 1; fibs[1] = 1;
        for(int i = 2; i < n; i++){
            fibs[i] = fibs[i-1] + fibs[i-2];
        }
        return fibs[n-1];
    }
}

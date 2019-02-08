/*
 * David Graff 2019
 */
package fizzbuzz;

/**
 *
 * @author david
 */
public class FindRemainder {
    public static void main(String[] args){
        System.out.println(remainder(1,2));
    }
    
    public static int remainder(int num, int divisor){
        if(divisor == 1)
            return 0;
        int sum = 0;
        while(sum + divisor < num){
            sum += divisor;
        }
        return num - sum;
    }
}

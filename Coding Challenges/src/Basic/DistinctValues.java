/*
 * David Graff 2019
 */
package fizzbuzz;

import java.util.Arrays;

/**
 *  Distinct Values: given an array of integers, return
 *  a subset of values that represents every value, with 
 *  no repetitions.
 */
public class DistinctValues {
    public static void main(String[] args){
        int[] vals = {1,2,3,3};
        int[] output = distinctVals(vals);
        for(int i : output)
            System.out.print(i + " ");
    }
    
    public static int[] distinctVals(int[] arr){
        int size = 0;
        int[] output = new int[arr.length];
        
        for(int i = 0; i < arr.length; i++){
            boolean appears = false;
            for(int j = 0; j < size; j++){
                if(arr[i] == output[j])
                    appears = true;
            }
            if(!appears)
                output[size++] = arr[i];
        }
        return Arrays.copyOfRange(output, 0, size);
    }
}

/*
 * David Graff 2019
 */
package fizzbuzz;

/**
 *
 * @author david
 */
public class StringReverse {
    public static void main(String[] args){
        System.out.println("Reverse: " + reverse("I like my coffee"));
        System.out.println("Reverse word order: " + reverseSentence("I like my coffee"));
        System.out.println("Reverse words: " + reverseWordsInSentence("I like my coffee"));
        System.out.println("Reverse words and order: " + reverseSentenceAndWords("I like my coffee"));
    }
    
    public static String reverse(String input){
        char[] reverse = new char[input.length()];
        for(int i = input.length()-1; i > -1; i--)
            reverse[input.length()-i-1] = input.charAt(i);
        return new String(reverse);
    }
    
    public static String reverseSentence(String input){
        String[] words = input.split(" ");
        String[] reverse = new String[words.length];
        for(int i = words.length-1; i > -1; i--)
            reverse[words.length-i-1] = words[i];
        return String.join(" ", reverse);
    }
    
    public static String reverseWordsInSentence(String input){
        String[] words = input.split(" ");
        for(int i = 0; i < words.length; i++)
            words[i] = reverse(words[i]);
        return String.join(" ", words);
    }
    
    public static String reverseSentenceAndWords(String input){
        String[] words = input.split(" ");
        String[] reverse = new String[words.length];
        for(int i = words.length-1; i > -1; i--)
            reverse[words.length-i-1] = reverse(words[i]);
        return String.join(" ", reverse);
    }
}

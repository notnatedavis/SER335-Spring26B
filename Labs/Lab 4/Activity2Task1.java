/* Labs/Lab 4/Task1.java
 * 
 * Task 1 Code Snippet
 * SEI CERT Guideline: EXP02-J
 * Do not use the Object.equals() method to compare 
 * two arrays
 */

public class Task1 {
    public static void main(String[] args) {
        char[] chars1 = {'a', 'b', 'a'};
        char[] chars2 = {'a', 'b', 'a'};
        
        // use Arrays.equals() for data comparison
        System.out.println(Arrays.equals(chars1, chars2)); // prints true
    }
}
import java.util.Scanner;

 // Creating class
class Book {
    // set up three private properties

    // getters and setters go here
    // Remember: 
    // 1. They must not allow empty string for 'title' and 'author'.
    // 2. They must not allow negative or zero value for 'numberOfPages'.
    // 3. If such values are attempted to be set, the property should remain unchanged.
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an object of the Book class
        Book book = new Book();

        // Take Title, Author and numberOfPages as next inputs and set them using the mutator methods
        // Your code here

        // Then use the accessor methods to get and print these values.
        // Your code here

        scanner.close();
    }
}
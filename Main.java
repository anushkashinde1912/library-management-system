package assignment1;

import java.util.*;

public class Main {
   public static void main(String[] args) {
	   
       // Create Library object
       Library L = new Library();
       // Scanner object for user input
       Scanner sc = new Scanner(System.in);
       // Controls menu loop
       boolean bool = true;
       
       // Main menu loop
       while (bool) {
           System.out.println("***COLLEGE LIBRARY***");
           System.out.println("1.Add Book\n2.Search book by ISBN number.\n3.Sort books by ISBN number\n4.List all books by Author\n5.Exit");
           int opt = sc.nextInt();
           sc.nextLine(); // Consume leftover newline
           
           switch (opt) {
           
           case 1:
               // Add a new book
               L.addBook();
               break;
               
           case 2:
               // Check if library is empty
               if (L.x == 0) {
                   System.out.println("Library is empty!");
                   break;
                   
               }
               // Take ISBN to search
               System.out.print("Enter ISBN no. to be searched : ");
               int target = sc.nextInt();
               
               // Perform binary search
               int index = L.searchByISBN(L.BookList, target);
               if (index != -1) {
                   System.out.println("BOOK FOUND!");
                   L.BookList[index].printInfo();
               }
               else {
                   System.out.println("BOOK NOT FOUND!");
               }
               break;
               
           case 3:
        	   
               // Check if library is empty
               if (L.x == 0) {
                   System.out.println("Library is empty!");
                   break;
               }
               
               // Sort books using Bubble Sort
               L.sortByISBN();
               
               // Display sorted books
               for (int i = 0; i < L.x; i++) {
                   System.out.print((i + 1) + ". ");
                   L.BookList[i].printInfo();
               }
               break;
               
           case 4:
        	   
               // Search books by author using Linear Search
               L.listAllByAuthor();
               break;
               
           case 5:
        	   
               // Exit program
               bool = false;
               System.out.println("Exiting System. Goodbye!");
               break;
               
           default:
        	   
               // Invalid menu choice
               System.out.println("SELECT VALID OPTION NO.");
               break;
               
           }
       }
   }
}
// Class representing a Book
class Book {
   int ISBN;
   String author, name;
   // Constructor to initialize book details
   Book(String a, String n, int I) {
       this.author = a;
       this.name = n;
       this.ISBN = I;
   }
   // Displays book information
   void printInfo() {
       System.out.println("Book name : " + name +
               " | Author name : " + author +
               " | ISBN no. : " + ISBN);
   }
}
// Library class containing all library operations
class Library {
   // Array to store books (maximum 50 books)
   Book BookList[] = new Book[50];
   // Scanner object for input
   Scanner sc = new Scanner(System.in);
   // Keeps track of number of books
   int x = 0;
   // Method to add a new book
   void addBook() {
       // Check if library is full
       if (x >= BookList.length) {
           System.out.println("Library space full!");
           return;
       }
       // Take book details from user
       System.out.print("Enter Book name : ");
       String n = sc.nextLine();
       System.out.print("Enter Author name :");
       String a = sc.nextLine();
       System.out.print("Enter ISBN no. : ");
       int I = sc.nextInt();
       sc.nextLine();
       // Create Book object
       Book b = new Book(a, n, I);
       // Store book in array
       BookList[x] = b;
       x++;
       System.out.println("Book added successfully!");
   }
   // Binary Search to find book using ISBN
   int searchByISBN(Book arr[], int target) {
       // Sort array before Binary Search
       sortByISBN();
       int low = 0;
       int high = x - 1;
       while (low <= high) {
           int mid = (low + high) / 2;
           // ISBN found
           if (arr[mid].ISBN == target) {
               return mid;
           }
           // Search in right half
           else if (target > arr[mid].ISBN) {
               low = mid + 1;
           }
           // Search in left half
           else {
               high = mid - 1;
           }
       }
       // ISBN not found
       return -1;
   }
   // Bubble Sort to sort books according to ISBN
   void sortByISBN() {
       int n = x;
       for (int i = 0; i < n - 1; i++) {
           for (int j = 0; j < n - i - 1; j++) {
               // Swap if current ISBN is greater
               if (BookList[j].ISBN > BookList[j + 1].ISBN) {
                   Book temp = BookList[j];
                   BookList[j] = BookList[j + 1];
                   BookList[j + 1] = temp;
               }
           }
       }
   }
   // Linear Search to display all books by an author
   void listAllByAuthor() {
       // Check if library is empty
       if (x == 0) {
           System.out.println("Library is empty!");
           return;
       }
       System.out.print("Search by Author name: ");
       String searchAuthor = sc.nextLine();
       int ser = 1;
       int found = -1;
       // Traverse all books
       for (int i = 0; i < x; i++) {
           // Compare author names (case-insensitive)
           if (BookList[i].author.equalsIgnoreCase(searchAuthor)) {
               found = 1;
               System.out.print(ser + ". ");
               ser++;
               BookList[i].printInfo();
           }
       }
       // If no books matched
       if (found != 1) {
           System.out.println("NO BOOKS FOUND.");
       }
   }
}

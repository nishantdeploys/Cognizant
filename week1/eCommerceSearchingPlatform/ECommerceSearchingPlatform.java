import java.util.Scanner;
import java.util.Arrays;

public class ECommerceSearchingPlatform {
    private static Product[] unsortedProducts;
    private static Product[] sortedProducts;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        
        initializeProducts();
        
        mainMenu();
        
        scanner.close();
    }

    private static void initializeProducts() {
        Product[] products = {
            new Product(105, "Laptop", "Electronics", 50, 799.99),
            new Product(101, "Mouse", "Electronics", 200, 25.99),
            new Product(103, "Keyboard", "Electronics", 150, 79.99),
            new Product(108, "Monitor", "Electronics", 80, 299.99),
            new Product(102, "USB Cable", "Accessories", 300, 9.99),
            new Product(107, "Headphones", "Electronics", 120, 149.99),
            new Product(104, "Desk Lamp", "Accessories", 75, 39.99),
            new Product(106, "Phone Stand", "Accessories", 200, 15.99),
            new Product(109, "Gaming Mouse", "Electronics", 45, 59.99),
            new Product(110, "Webcam", "Electronics", 60, 89.99)
        };

        unsortedProducts = products.clone();
        sortedProducts = products.clone();
        Arrays.sort(sortedProducts); // Sort by productId for binary search
    }

    private static void mainMenu() {
        boolean running = true;
        
        while (running) {
            System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
            System.out.println("║       E-COMMERCE SEARCH PLATFORM - MAIN MENU              ║");
            System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
            System.out.println("1. Search by Product ID (Linear Search)");
            System.out.println("2. Search by Product ID (Binary Search)");
            System.out.println("3. Search by Product Name (Linear Search)");
            System.out.println("4. Search by Category (Linear Search)");
            System.out.println("5. View All Products");
            System.out.println("6. View Complexity Analysis");
            System.out.println("7. Run Performance Comparison");
            System.out.println("8. Exit");
            System.out.print("\nSelect option (1-8): ");
            
            int choice = getValidInteger(1, 8);
            
            switch (choice) {
                case 1:
                    searchByIdLinear();
                    break;
                case 2:
                    searchByIdBinary();
                    break;
                case 3:
                    searchByName();
                    break;
                case 4:
                    searchByCategory();
                    break;
                case 5:
                    displayAllProducts();
                    break;
                case 6:
                    SearchAlgorithms.displayComplexityAnalysis();
                    break;
                case 7:
                    performanceComparison();
                    break;
                case 8:
                    System.out.println("\n✓ Thank you for using E-Commerce Search Platform!");
                    running = false;
                    break;
            }
        }
    }

    /**
     * Linear search by Product ID
     */
    private static void searchByIdLinear() {
        System.out.println("\n── LINEAR SEARCH BY PRODUCT ID ──");
        System.out.print("Enter Product ID: ");
        int productId = getValidInteger();
        
        System.out.println("\nSearching...");
        Product result = SearchAlgorithms.linearSearchById(unsortedProducts, productId);
        
        if (result != null) {
            System.out.println("✓ FOUND: " + result);
        } else {
            System.out.println("✗ Product not found!");
        }
    }

    /**
     * Binary search by Product ID
     */
    private static void searchByIdBinary() {
        System.out.println("\n── BINARY SEARCH BY PRODUCT ID ──");
        System.out.println("(Requires sorted data by Product ID)");
        System.out.print("Enter Product ID: ");
        int productId = getValidInteger();
        
        System.out.println("\nSearching...");
        Product result = SearchAlgorithms.binarySearchById(sortedProducts, productId);
        
        if (result != null) {
            System.out.println("✓ FOUND: " + result);
        } else {
            System.out.println("✗ Product not found!");
        }
    }

    /**
     * Linear search by Product Name
     */
    private static void searchByName() {
        System.out.println("\n── LINEAR SEARCH BY PRODUCT NAME ──");
        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine().trim();
        
        if (productName.isEmpty()) {
            System.out.println("✗ Invalid input!");
            return;
        }
        
        System.out.println("\nSearching...");
        Product result = SearchAlgorithms.linearSearchByName(unsortedProducts, productName);
        
        if (result != null) {
            System.out.println("✓ FOUND: " + result);
        } else {
            System.out.println("✗ Product not found!");
        }
    }

    /**
     * Linear search by Category
     */
    private static void searchByCategory() {
        System.out.println("\n── LINEAR SEARCH BY CATEGORY ──");
        System.out.println("Available categories: Electronics, Accessories");
        System.out.print("Enter Category: ");
        String category = scanner.nextLine().trim();
        
        if (category.isEmpty()) {
            System.out.println("✗ Invalid input!");
            return;
        }
        
        System.out.println("\nSearching...");
        Product[] results = SearchAlgorithms.linearSearchByCategory(unsortedProducts, category);
        
        if (results.length > 0) {
            System.out.println("✓ FOUND " + results.length + " products:");
            for (int i = 0; i < results.length; i++) {
                System.out.println("  " + (i + 1) + ". " + results[i]);
            }
        } else {
            System.out.println("✗ No products found in this category!");
        }
    }

    /**
     * Display all products in database
     */
    private static void displayAllProducts() {
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║              PRODUCT DATABASE (UNSORTED)                  ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        for (int i = 0; i < unsortedProducts.length; i++) {
            System.out.printf("%-3d: %s%n", i + 1, unsortedProducts[i]);
        }
    }

    /**
     * Run performance comparison between linear and binary search
     */
    private static void performanceComparison() {
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║         PERFORMANCE COMPARISON - LINEAR vs BINARY         ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        System.out.print("Enter Product ID to search: ");
        int productId = getValidInteger();
        
        // Test Linear Search
        System.out.println("\n⏱️  LINEAR SEARCH (Unsorted Array):");
        long startLinear = System.nanoTime();
        Product resultLinear = SearchAlgorithms.linearSearchById(unsortedProducts, productId);
        long endLinear = System.nanoTime();
        long linearTime = endLinear - startLinear;
        
        // Test Binary Search
        System.out.println("\n⏱️  BINARY SEARCH (Sorted Array):");
        long startBinary = System.nanoTime();
        Product resultBinary = SearchAlgorithms.binarySearchById(sortedProducts, productId);
        long endBinary = System.nanoTime();
        long binaryTime = endBinary - startBinary;
        
        // Display results
        System.out.println("\n╔─ COMPARISON RESULTS ─╗");
        System.out.printf("Linear Time:  %.3f µs%n", linearTime / 1000.0);
        System.out.printf("Binary Time:  %.3f µs%n", binaryTime / 1000.0);
        
        if (binaryTime > 0) {
            double speedup = (double) linearTime / binaryTime;
            System.out.printf("Speedup:      %.2fx faster%n", speedup);
        }
        System.out.println("╚──────────────────────╝");
    }

    /**
     * Utility method to get valid integer input
     */
    private static int getValidInteger() {
        try {
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                return num;
            } else {
                scanner.nextLine(); // Clear invalid input
                System.out.println("✗ Invalid input! Please enter a number.");
                return getValidInteger();
            }
        } catch (Exception e) {
            System.out.println("✗ Error reading input!");
            return -1;
        }
    }

    /**
     * Utility method to get valid integer input within range
     */
    private static int getValidInteger(int min, int max) {
        try {
            int num = getValidInteger();
            if (num >= min && num <= max) {
                return num;
            } else {
                System.out.print("✗ Please enter a number between " + min + " and " + max + ": ");
                return getValidInteger(min, max);
            }
        } catch (Exception e) {
            System.out.println("✗ Error reading input!");
            return -1;
        }
    }
}

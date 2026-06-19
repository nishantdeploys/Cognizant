import java.util.Arrays;

/**
 * SearchAlgorithms - Demonstrates linear and binary search for e-commerce platform
 * 
 * TIME COMPLEXITY ANALYSIS:
 * ========================
 * 
 * LINEAR SEARCH:
 * - Best Case: O(1) - Element found at first position
 * - Average Case: O(n) - Element found in middle
 * - Worst Case: O(n) - Element at last position or not found
 * - Space Complexity: O(1)
 * - Use Case: Unsorted data, small datasets
 * 
 * BINARY SEARCH:
 * - Best Case: O(1) - Element found at middle position
 * - Average Case: O(log n)
 * - Worst Case: O(log n)
 * - Space Complexity: O(1) iterative, O(log n) recursive
 * - Use Case: Sorted data, large datasets
 * - Requirement: Array MUST be sorted
 * 
 * REAL-WORLD COMPARISON (1 million products):
 * - Linear: Up to 1,000,000 comparisons (worst case)
 * - Binary: Approximately 20 comparisons (log₂ 1,000,000 ≈ 20)
 * - Speedup: Binary is ~50,000x faster!
 */
public class SearchAlgorithms {

    // ==================== LINEAR SEARCH ====================

    /**
     * Linear Search by Product ID
     * Time Complexity: O(n)
     * Works on unsorted arrays
     */
    public static Product linearSearchById(Product[] products, int productId) {
        int comparisons = 0;
        for (int i = 0; i < products.length; i++) {
            comparisons++;
            if (products[i].getProductId() == productId) {
                System.out.println("  → Found in " + comparisons + " comparisons");
                return products[i];
            }
        }
        System.out.println("  → Not found after " + comparisons + " comparisons");
        return null;
    }

    /**
     * Linear Search by Product Name
     * Time Complexity: O(n)
     */
    public static Product linearSearchByName(Product[] products, String productName) {
        int comparisons = 0;
        for (int i = 0; i < products.length; i++) {
            comparisons++;
            if (products[i].getProductName().equalsIgnoreCase(productName)) {
                System.out.println("  → Found in " + comparisons + " comparisons");
                return products[i];
            }
        }
        System.out.println("  → Not found after " + comparisons + " comparisons");
        return null;
    }

    /**
     * Linear Search by Category
     * Time Complexity: O(n)
     * Returns all products matching the category
     */
    public static Product[] linearSearchByCategory(Product[] products, String category) {
        Product[] results = new Product[products.length];
        int count = 0;

        for (int i = 0; i < products.length; i++) {
            if (products[i].getCategory().equalsIgnoreCase(category)) {
                results[count] = products[i];
                count++;
            }
        }

        System.out.println("  → Found " + count + " products in category");
        return Arrays.copyOf(results, count);
    }

    // ==================== BINARY SEARCH ====================

    /**
     * Binary Search by Product ID (Iterative)
     * Time Complexity: O(log n)
     * IMPORTANT: Array MUST be sorted by productId!
     */
    public static Product binarySearchById(Product[] products, int productId) {
        int comparisons = 0;
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            comparisons++;
            int mid = left + (right - left) / 2;
            int midId = products[mid].getProductId();

            if (midId == productId) {
                System.out.println("  → Found in " + comparisons + " comparisons");
                return products[mid];
            } else if (midId < productId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("  → Not found after " + comparisons + " comparisons");
        return null;
    }

    /**
     * Binary Search by Product ID (Recursive)
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) due to call stack
     */
    public static Product binarySearchByIdRecursive(Product[] products, int productId) {
        return binarySearchRecursiveHelper(products, productId, 0, products.length - 1, new int[1]);
    }

    private static Product binarySearchRecursiveHelper(Product[] products, int productId,
                                                       int left, int right, int[] comparisons) {
        if (left > right) {
            System.out.println("  → Not found after " + comparisons[0] + " comparisons");
            return null;
        }

        comparisons[0]++;
        int mid = left + (right - left) / 2;
        int midId = products[mid].getProductId();

        if (midId == productId) {
            System.out.println("  → Found in " + comparisons[0] + " comparisons");
            return products[mid];
        } else if (midId < productId) {
            return binarySearchRecursiveHelper(products, productId, mid + 1, right, comparisons);
        } else {
            return binarySearchRecursiveHelper(products, productId, left, mid - 1, comparisons);
        }
    }

    /**
     * Display complexity analysis table
     */
    public static void displayComplexityAnalysis() {
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║     ALGORITHM COMPLEXITY - E-COMMERCE SEARCH PLATFORM    ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");

        int[] dataSizes = {100, 1000, 10000, 100000, 1000000};

        System.out.println("THEORETICAL WORST-CASE COMPARISONS:");
        System.out.println("─".repeat(65));
        System.out.printf("%-15s | %-20s | %-20s | %-8s%n", 
            "Data Size", "Linear O(n)", "Binary O(log n)", "Speedup");
        System.out.println("─".repeat(65));

        for (int size : dataSizes) {
            int linearOps = size;
            int binaryOps = (int) Math.ceil(Math.log(size) / Math.log(2));
            double speedup = (double) linearOps / binaryOps;
            System.out.printf("%-15d | %-20d | %-20d | %.1fx%n", 
                size, linearOps, binaryOps, speedup);
        }

        System.out.println("─".repeat(65));
        System.out.println("\n📊 KEY INSIGHTS:");
        System.out.println("  • Linear Search: Simple but slow for large datasets");
        System.out.println("  • Binary Search: Exponentially faster for sorted data");
        System.out.println("  • For 1M products: Binary is ~50,000x faster!");
        System.out.println("  • Tradeoff: Binary requires sorted data");
    }
}

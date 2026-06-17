
// Test class to verify the singleton implementation

public class SingletonTest {

    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Test ===\n");

        // Get first instance
        Logger logger1 = Logger.getInstance();
        logger1.log("First logger instance");

        // Get second instance
        Logger logger2 = Logger.getInstance();
        logger2.log("Second logger instance");

        // Verify both are the same instance
        System.out.println("\nAre they the same instance? " + (logger1 == logger2));
        
        logger1.warn("Warning message");
        logger2.error("Error message");

        System.out.println("\n=== Test Complete ===");
    }
}

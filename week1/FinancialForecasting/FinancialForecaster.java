import java.util.HashMap;
import java.util.Map;

// Financial Forecaster using Recursive Algorithm
public class FinancialForecaster {

    /**
     * Calculate future value using recursive approach
     * Formula: FV = PV * (1 + r)^n
     * Where:
     *   PV = Present Value (initial investment)
     *   r  = Growth rate (as decimal, e.g., 0.05 for 5%)
     *   n  = Number of periods
     * 
     * Recursive approach: FV(n) = FV(n-1) * (1 + r)
     * Base case: FV(0) = PV
     * 
     * Time Complexity: O(2^n) - Exponential (inefficient)
     * Space Complexity: O(n) - Recursive call stack
     */
    public static double calculateFutureValueRecursive(double presentValue, double growthRate, int years) {
        // Base case: no time has passed
        if (years == 0) {
            return presentValue;
        }
        
        // Recursive case: compound growth for remaining years
        return calculateFutureValueRecursive(presentValue, growthRate, years - 1) * (1 + growthRate);
    }

    /**
     * Optimized recursive approach using Memoization
     * Stores already computed values to avoid redundant calculations
     * 
     * Time Complexity: O(n) - Each year computed only once
     * Space Complexity: O(n) - Memoization cache + recursive stack
     */
    private static Map<Integer, Double> memo = new HashMap<>();

    public static double calculateFutureValueMemoized(double presentValue, double growthRate, int years) {
        // Base case
        if (years == 0) {
            return presentValue;
        }
        
        // Check if already computed
        if (memo.containsKey(years)) {
            return memo.get(years) * presentValue;
        }
        
        // Compute and store
        double result = (1 + growthRate) * calculateFutureValueMemoized(presentValue, growthRate, years - 1);
        memo.put(years, result / presentValue);
        
        return result;
    }

    /**
     * Iterative approach (for comparison)
     * Time Complexity: O(n) - Linear
     * Space Complexity: O(1) - Constant
     * This is the most efficient approach
     */
    public static double calculateFutureValueIterative(double presentValue, double growthRate, int years) {
        double futureValue = presentValue;
        for (int i = 0; i < years; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }

    /**
     * Predict future value based on past growth rates
     * Analyzes historical data to forecast future values
     */
    public static double forecastBasedOnHistoricalGrowth(double initialValue, double[] historicalGrowthRates) {
        double predictedValue = initialValue;
        
        // Calculate average growth rate from historical data
        double totalGrowth = 0;
        for (double rate : historicalGrowthRates) {
            totalGrowth += rate;
        }
        double averageGrowthRate = totalGrowth / historicalGrowthRates.length;
        
        // Predict future value using average growth rate
        int forecastYears = historicalGrowthRates.length; // Forecast same number of years as historical data
        return calculateFutureValueIterative(predictedValue, averageGrowthRate, forecastYears);
    }

    /**
     * Clear memoization cache
     */
    public static void clearMemo() {
        memo.clear();
    }
}

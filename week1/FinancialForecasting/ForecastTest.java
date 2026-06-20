// Test class for Financial Forecasting Exercise
public class ForecastTest {
    public static void main(String[] args) {
        System.out.println("=== Financial Forecasting using Recursive Algorithms ===\n");

        // Test parameters
        double initialInvestment = 1000.0; // $1000
        double annualGrowthRate = 0.05;   // 5% annual growth
        int years = 10;

        System.out.println("Initial Investment: $" + initialInvestment);
        System.out.println("Annual Growth Rate: " + (annualGrowthRate * 100) + "%");
        System.out.println("Time Period: " + years + " years\n");

        // Test 1: Simple Recursive Approach
        System.out.println("--- Test 1: Simple Recursive Approach ---");
        long startTime = System.nanoTime();
        double resultRecursive = FinancialForecaster.calculateFutureValueRecursive(initialInvestment, annualGrowthRate, years);
        long endTime = System.nanoTime();
        long timeRecursive = endTime - startTime;
        
        System.out.println("Future Value (Recursive): $" + String.format("%.2f", resultRecursive));
        System.out.println("Time Taken: " + timeRecursive + " nanoseconds\n");

        // Test 2: Memoized Recursive Approach
        System.out.println("--- Test 2: Memoized Recursive Approach ---");
        FinancialForecaster.clearMemo();
        startTime = System.nanoTime();
        double resultMemoized = FinancialForecaster.calculateFutureValueMemoized(initialInvestment, annualGrowthRate, years);
        endTime = System.nanoTime();
        long timeMemoized = endTime - startTime;
        
        System.out.println("Future Value (Memoized): $" + String.format("%.2f", resultMemoized));
        System.out.println("Time Taken: " + timeMemoized + " nanoseconds\n");

        // Test 3: Iterative Approach
        System.out.println("--- Test 3: Iterative Approach (Most Efficient) ---");
        startTime = System.nanoTime();
        double resultIterative = FinancialForecaster.calculateFutureValueIterative(initialInvestment, annualGrowthRate, years);
        endTime = System.nanoTime();
        long timeIterative = endTime - startTime;
        
        System.out.println("Future Value (Iterative): $" + String.format("%.2f", resultIterative));
        System.out.println("Time Taken: " + timeIterative + " nanoseconds\n");

        // Test 4: Performance Comparison
        System.out.println("--- Performance Comparison ---");
        System.out.println("Recursive Time:  " + timeRecursive + " ns");
        System.out.println("Memoized Time:   " + timeMemoized + " ns");
        System.out.println("Iterative Time:  " + timeIterative + " ns");
        System.out.println("Speedup (Recursive vs Iterative): " + (timeRecursive / (double) timeIterative) + "x\n");

        // Test 5: Financial Forecasting based on Historical Data
        System.out.println("--- Test 5: Forecasting Based on Historical Growth ---");
        double[] historicalGrowthRates = {0.03, 0.04, 0.05, 0.06, 0.04};
        double currentValue = 5000.0;
        
        System.out.println("Current Value: $" + currentValue);
        System.out.println("Historical Growth Rates: 3%, 4%, 5%, 6%, 4%");
        
        double forecastedValue = FinancialForecaster.forecastBasedOnHistoricalGrowth(currentValue, historicalGrowthRates);
        System.out.println("Forecasted Value: $" + String.format("%.2f", forecastedValue));
        System.out.println("Average Growth Rate: " + 
            String.format("%.2f", ((forecastedValue - currentValue) / currentValue / historicalGrowthRates.length * 100)) + "%\n");

        // Test 6: Multi-year Projection
        System.out.println("--- Test 6: Year-by-Year Projection ---");
        System.out.println("Year-by-Year Growth Projection:");
        System.out.println("Year 0: $" + String.format("%.2f", initialInvestment));
        for (int i = 1; i <= years; i++) {
            double value = FinancialForecaster.calculateFutureValueIterative(initialInvestment, annualGrowthRate, i);
            System.out.println("Year " + i + ": $" + String.format("%.2f", value));
        }
    }
}

package com.employee;

import com.employee.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.stat.Statistics;

import java.util.List;

public class App {

    public static void main(String[] args) {
        // 1. Initialize Hibernate SessionFactory
        System.out.println("=== 1. Initializing Hibernate SessionFactory ===");
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // Loads hibernate.cfg.xml from classpath
                .build();
        
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            
            // Enable Statistics tracking
            Statistics stats = sessionFactory.getStatistics();
            stats.setStatisticsEnabled(true);

            // 2. Demonstrate Dynamic Insert & Formula & Auditing
            demonstrateDynamicInsertAndFormula(sessionFactory);

            // 3. Demonstrate Dynamic Update
            demonstrateDynamicUpdate(sessionFactory);

            // 4. Demonstrate Batch Processing
            demonstrateBatchProcessing(sessionFactory);

            // 5. Print Performance Statistics
            printPerformanceStatistics(sessionFactory);

        } catch (Exception e) {
            System.err.println("An error occurred during application execution: " + e.getMessage());
            e.printStackTrace();
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        } finally {
            if (sessionFactory != null) {
                System.out.println("\n=== Closing SessionFactory ===");
                sessionFactory.close();
            }
        }
    }

    private static void demonstrateDynamicInsertAndFormula(SessionFactory sessionFactory) {
        System.out.println("\n=== 2. Demonstrating Dynamic Insert, Formula & Auditing ===");
        
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Notice we DO NOT set the department. It is left as null.
        // Because of @DynamicInsert, Hibernate will omit 'department' from the SQL INSERT statement.
        Employee emp = new Employee("John Doe", null, 5000.0);
        System.out.println("Saving employee: John Doe, Department = null, Salary = 5000.0");
        session.save(emp);

        transaction.commit();
        session.close();

        // Open a new session to load the saved employee and check computed fields
        session = sessionFactory.openSession();
        Employee savedEmp = session.get(Employee.class, emp.getId());
        
        System.out.println("\nLoaded Employee details:");
        System.out.println("Name: " + savedEmp.getName());
        System.out.println("Salary: $" + savedEmp.getSalary());
        // @Formula dynamically calculates this value during SELECT
        System.out.println("Calculated Annual Salary (via @Formula): $" + savedEmp.getAnnualSalary());
        // Audit timestamps set automatically
        System.out.println("Created At (via @CreationTimestamp): " + savedEmp.getCreatedAt());
        System.out.println("Updated At (via @UpdateTimestamp): " + savedEmp.getUpdatedAt());
        
        session.close();
    }

    private static void demonstrateDynamicUpdate(SessionFactory sessionFactory) {
        System.out.println("\n=== 3. Demonstrating Dynamic Update ===");

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Load the existing employee
        Employee emp = session.get(Employee.class, 1L);
        System.out.println("Before update: " + emp);

        // Update ONLY the salary.
        // Because of @DynamicUpdate, Hibernate will only update 'salary' (and 'updated_at'), omitting other fields.
        System.out.println("Updating salary from $5000.0 to $6000.0...");
        emp.setSalary(6000.0);
        session.update(emp);

        transaction.commit();
        session.close();

        // Load and check the changes
        session = sessionFactory.openSession();
        Employee updatedEmp = session.get(Employee.class, 1L);
        System.out.println("After update: " + updatedEmp);
        System.out.println("Updated Annual Salary: $" + updatedEmp.getAnnualSalary());
        System.out.println("Updated Timestamp: " + updatedEmp.getUpdatedAt());
        session.close();
    }

    private static void demonstrateBatchProcessing(SessionFactory sessionFactory) {
        System.out.println("\n=== 4. Demonstrating Batch Processing ===");
        System.out.println("Inserting 120 employees in batches of 50. Monitor the logs for batched inserts...");

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        int batchSize = 50;
        for (int i = 1; i <= 120; i++) {
            Employee emp = new Employee("BatchEmp_" + i, "IT_Dept", 3000.0 + (i * 10));
            session.save(emp);

            // Flush and clear the session at the batch boundary
            if (i % batchSize == 0) {
                System.out.println("Reached batch boundary (i = " + i + "). Flushing and clearing first-level cache...");
                session.flush(); // Sends the SQL statements to the database
                session.clear(); // Clears the session cache to free up memory
            }
        }

        // Flush and clear any remaining records (20 records left)
        System.out.println("Flushing remaining records...");
        session.flush();
        session.clear();

        transaction.commit();
        session.close();
        System.out.println("Batch Insertion complete!");
    }

    private static void printPerformanceStatistics(SessionFactory sessionFactory) {
        System.out.println("\n=== 5. Printing Performance Statistics ===");
        Statistics stats = sessionFactory.getStatistics();
        
        System.out.println("Session open count: " + stats.getSessionOpenCount());
        System.out.println("Session close count: " + stats.getSessionCloseCount());
        System.out.println("Entity insert count: " + stats.getEntityInsertCount());
        System.out.println("Entity update count: " + stats.getEntityUpdateCount());
        System.out.println("Prepared statements count: " + stats.getPrepareStatementCount());
        System.out.println("Transaction count: " + stats.getTransactionCount());
        System.out.println("Successful transaction count: " + stats.getSuccessfulTransactionCount());
        
        // Note: Batch size is 50. Total inserts = 1 (John Doe) + 120 (Batch) = 121.
        // Without batching, this requires 121 separate insert executions (or statement roundtrips).
        // With batching, it gets grouped into much fewer statement executions.
    }
}

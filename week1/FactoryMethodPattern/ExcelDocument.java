// Concrete class for Excel Document
public class ExcelDocument implements Document {
    @Override
    public void create() {
        System.out.println("Creating Excel Document...");
    }

    @Override
    public void open() {
        System.out.println("Opening Excel Document in Microsoft Excel");
    }

    @Override
    public void save() {
        System.out.println("Saving Excel Document (.xlsx)");
    }
}

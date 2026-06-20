// Concrete class for PDF Document
public class PdfDocument implements Document {
    @Override
    public void create() {
        System.out.println("Creating PDF Document...");
    }

    @Override
    public void open() {
        System.out.println("Opening PDF Document in Adobe Reader");
    }

    @Override
    public void save() {
        System.out.println("Saving PDF Document (.pdf)");
    }
}

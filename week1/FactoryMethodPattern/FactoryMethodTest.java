// Test class to demonstrate Factory Method Pattern
public class FactoryMethodTest {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo ===\n");

        // Create Word Document
        System.out.println("1. Creating Word Document:");
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.create();
        wordDoc.open();
        wordDoc.save();
        System.out.println();

        // Create PDF Document
        System.out.println("2. Creating PDF Document:");
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.create();
        pdfDoc.open();
        pdfDoc.save();
        System.out.println();

        // Create Excel Document
        System.out.println("3. Creating Excel Document:");
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.create();
        excelDoc.open();
        excelDoc.save();
    }
}

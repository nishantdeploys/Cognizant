// Abstract Factory Class - defines the factory method
public abstract class DocumentFactory {
    // Factory method - to be implemented by subclasses
    abstract public Document createDocument();

    // Client code uses this method without knowing the exact document type
    public void newDocument() {
        Document doc = createDocument();
        doc.create();
    }
}

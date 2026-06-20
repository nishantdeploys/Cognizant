# Factory Method Pattern - Document Management System

## Overview
This implementation demonstrates the Factory Method Pattern using a document management system that creates different types of documents (Word, PDF, Excel).

## Structure

### 1. **Document Interface**
   - Base interface for all document types
   - Defines common methods: `create()`, `open()`, `save()`

### 2. **Concrete Document Classes**
   - **WordDocument**: Represents Word documents (.docx)
   - **PdfDocument**: Represents PDF documents (.pdf)
   - **ExcelDocument**: Represents Excel documents (.xlsx)

### 3. **DocumentFactory Abstract Class**
   - Defines the factory method `createDocument()`
   - Provides template method `newDocument()` that uses the factory method

### 4. **Concrete Factory Classes**
   - **WordDocumentFactory**: Creates WordDocument instances
   - **PdfDocumentFactory**: Creates PdfDocument instances
   - **ExcelDocumentFactory**: Creates ExcelDocument instances

### 5. **Test Class**
   - **FactoryMethodTest**: Demonstrates how to use the pattern

## How It Works

```
Client → Concrete Factory → Document Interface → Concrete Document
```

1. Client requests a factory (e.g., `WordDocumentFactory`)
2. Factory's `createDocument()` method returns the specific document type
3. Client uses the document through the common interface
4. Client doesn't need to know which concrete class is being used

## Key Benefits

✓ **Loose Coupling**: Client doesn't depend on concrete document classes
✓ **Easy to Extend**: Add new document types without modifying existing code
✓ **Single Responsibility**: Each factory handles creation of one document type
✓ **Open/Closed Principle**: Open for extension, closed for modification

## Running the Program

Compile and run `FactoryMethodTest.java` to see the factory method pattern in action:

```
javac *.java
java FactoryMethodTest
```

## Output Example

```
=== Factory Method Pattern Demo ===

1. Creating Word Document:
Creating Word Document...
Opening Word Document in Microsoft Word
Saving Word Document (.docx)

2. Creating PDF Document:
Creating PDF Document...
Opening PDF Document in Adobe Reader
Saving PDF Document (.pdf)

3. Creating Excel Document:
Creating Excel Document...
Opening Excel Document in Microsoft Excel
Saving Excel Document (.xlsx)
```

## Real-World Applications

- UI framework creation (buttons, dialogs, windows for different OS)
- Database connection pooling (MySQL, PostgreSQL, Oracle)
- Logging system (File logger, Console logger, Database logger)
- Document export (PDF, Excel, Word, HTML exporters)

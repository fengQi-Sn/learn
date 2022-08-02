package example.example.behavior.visitor;

public class Compressor {
    public void compressortxt(PPTFile pptFile) {
        //...
        System.out.println("Compressor PPT.");
    }

    public void compressortxt(PdfFile pdfFile) {
        //...
        System.out.println("Compressor PDF.");
    }

    public void compressortxt(WordFile wordFile) {
        //...
        System.out.println("Compressor WORD.");
    }
}

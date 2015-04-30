package sample;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class PDFImageFactory {
    PDDocument doc;
    List<PDPage> listPages;

    public PDFImageFactory(String filePath) {
        doc = getPdDocument(filePath);
        listPages  = doc.getDocumentCatalog().getAllPages();
    }

    public Stream<Image> createImagesStream() {
        return listPages.stream()
                .map(pdfPage -> {
                    try {
                        return (Image) SwingFXUtils.toFXImage(pdfPage.convertToImage(), null);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                });
    }


    PDDocument getPdDocument(String from) {
        return getPdDocument(from, null);
    }

    PDDocument getPdDocument(String from, PDDocument doc) {
        try {
            doc = PDDocument.load(new File(from));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}

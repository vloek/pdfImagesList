package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller implements Initializable, Runnable {
    public TextField txtField;
    public ImageView imageView;

    String filePath = "/Users/admin/IdeaProjects/jfxExmp/src/sample/xcv.pdf";

    public void search(ActionEvent actionEvent) {
        txtField.textProperty().setValue("Hello!");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new Thread(this).start();
    }

    synchronized void setImage(Image img) throws InterruptedException {
        imageView.setImage(img);
        Thread.sleep(5000);
    }


    @Override
    public void run() {
        PDFImageFactory pdfFac    = new PDFImageFactory(filePath);
        Stream<Image> imageStream = pdfFac.createImagesStream();

        imageStream.limit(3)
                .forEach((img) -> {
                    try {
                        this.setImage(img);
                    } catch (Exception e) {
                        System.out.println("Ploho(");
                    }
                });
    }
}

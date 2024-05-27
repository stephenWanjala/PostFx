package com.github.stephenwanjala.postfx.util;



import com.github.stephenwanjala.postfx.domain.model.Post;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;
import java.util.List;

public class PdfExporter {
    public static void exportToPdf(List<Post> posts, String filePath) throws IOException {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);
            try (PDPageContentStream contentStream = new PDPageContentStream(doc, page)) {
                contentStream.setFont((new PDType1Font(Standard14Fonts.FontName.HELVETICA)), 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);

                for (Post post : posts) {
                    contentStream.showText("ID: " + post.getId());
                    contentStream.newLine();
                    contentStream.showText("Title: " + post.getTitle());
                    contentStream.newLine();
                    contentStream.showText("Body: " + post.getBody());
                    contentStream.newLine();
                    contentStream.newLine();
                }

                contentStream.endText();
            }
            doc.save(filePath);
        }
    }
}


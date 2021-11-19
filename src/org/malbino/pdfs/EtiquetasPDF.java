/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.malbino.pdfs;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.malbino.models.Activo;

/**
 *
 * @author tincho
 */
public class EtiquetasPDF {

    private static final Font TITULO = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
    private static final Font NORMAL = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
    private static final Font ESPACIO = FontFactory.getFont(FontFactory.HELVETICA, 4, Font.NORMAL, BaseColor.BLACK);

    private static final int MARGEN_IZQUIERDO = -40;
    private static final int MARGEN_DERECHO = -40;
    private static final int MARGEN_SUPERIOR = 20;
    private static final int MARGEN_INFERIOR = 20;

    public void generarPDF(List<Activo> activos, File file) throws DocumentException, FileNotFoundException, BadElementException, IOException {
        Document document = new Document(PageSize.LETTER, MARGEN_IZQUIERDO, MARGEN_DERECHO, MARGEN_SUPERIOR, MARGEN_INFERIOR);
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();

        document.add(cuerpo(activos));

        document.close();
    }

    public PdfPTable cuerpo(List<Activo> activos) throws BadElementException {
        PdfPTable table = new PdfPTable(45);

        int sumatoriaColumnas = 0;
        for (Activo activo : activos) {
            String qr = String.format("%s|%s|%s|%s",
                    activo.getCodigo(),
                    activo.getCodigoAntiguo(),
                    activo.getDescripcion(),
                    activo.getEstado()
            );

            BarcodeQRCode barcodeQRCode = new BarcodeQRCode(qr, 1, 1, null);
            Image image = barcodeQRCode.getImage();
            image.scaleAbsolute(50f, 50f);
            PdfPCell cell = new PdfPCell(image);
            cell.setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.TOP);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setFixedHeight(60);
            cell.setColspan(5);
            table.addCell(cell);

            Phrase phrase = new Phrase();
            phrase.add(new Chunk(activo.getCodigo() + "\n", TITULO));
            phrase.add(new Chunk("\n", ESPACIO));
            phrase.add(new Chunk(activo.getCodigoAntiguo() + "\n", NORMAL));
            phrase.add(new Chunk("\n", ESPACIO));
            phrase.add(new Chunk("INFOCAL", NORMAL));
            cell = new PdfPCell(phrase);
            cell.setBorder(Rectangle.TOP | Rectangle.RIGHT | Rectangle.BOTTOM);
            cell.setBorderColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setFixedHeight(60);
            cell.setColspan(10);
            table.addCell(cell);

            sumatoriaColumnas += 15;
            if (sumatoriaColumnas == 45) {
                sumatoriaColumnas = 0;
            }
        }

        if (sumatoriaColumnas < 45) {
            PdfPCell cell = new PdfPCell(new Phrase(" ", NORMAL));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
            cell.setColspan(45 - sumatoriaColumnas);
            table.addCell(cell);
        }

        return table;
    }

}

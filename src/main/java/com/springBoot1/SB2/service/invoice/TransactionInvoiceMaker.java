package com.springBoot1.SB2.service.invoice;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.springBoot1.SB2.config.ConfigUploadPathProperties;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TransactionInvoiceMaker {
    private static String basePath ;
    private static String transactionPath;
    private static Integer pathPrefixCount;
    public static String make(TransactionInvoiceData invoiceData) throws IOException {
        String pdfName = invoiceData.getCustomerName().replace(" ","_")+"_"+ RandomString.make(pathPrefixCount)+".pdf";
        String dirPath = basePath+transactionPath;
        String pdfPath = basePath+transactionPath+ File.separator +pdfName;
        Path _pdfPath = Paths.get(pdfPath);
        Path _dirPath = Paths.get(dirPath);
        if (!Files.exists(_dirPath))
            Files.createDirectories(_dirPath);
        Files.createFile(_pdfPath);
        OutputStream outputStream = new FileOutputStream(_pdfPath.toFile());
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,outputStream);
        document.open();
        document.add(new Paragraph(invoiceData.getPharmacyName(),
                FontFactory.getFont(FontFactory.TIMES_BOLDITALIC,30)));
        document.add(new Paragraph(invoiceData.getPharmacyAddress(),
            FontFactory.getFont(FontFactory.TIMES,14)));
        Paragraph invoiceId = new Paragraph();
        invoiceId.setAlignment("right");
        invoiceId.add(new Phrase("Invoice: ",new Font(Font.TIMES_ROMAN,18,Font.BOLD,Color.BLACK)));
        invoiceId.add(new Phrase(invoiceData.getTransactionId().toString(),new Font(Font.TIMES_ROMAN,13,Font.BOLD,Color.BLACK)));
        document.add(invoiceId);
        String timeFormat =  LocalDate.now().toString() +"  "+ LocalTime.now().getHour()+":"+LocalTime.now().getMinute();
        Paragraph time = new Paragraph();
        time.setAlignment("right");
        time.add(new Phrase( "Date: ",new Font(Font.TIMES_ROMAN,18,Font.BOLD,Color.BLACK)));
        time.add(new Phrase(timeFormat,new Font(Font.TIMES_ROMAN,13,Font.BOLD,Color.BLACK)));
        document.add(time);
        document.add(Chunk.NEWLINE);
//        Table name exp unit count amount
        Table table = new Table(5);
        table.setPadding(4);
        table.setWidth(100);
        table.setWidths(new float[]{3.5f,3f,1.5f,1.5f,2f});
        table.setBorderWidth(0);
        Font fontHeader =new Font(Font.TIMES_ROMAN,15,Font.NORMAL,Color.WHITE);
        List<String> headers =Arrays.asList("Medicine","Expiration","Price","Count","Amount");
        headers.forEach(s -> {
            Cell cell = new Cell(new Phrase(s,fontHeader));
            cell.setHeader(true);
            cell.setBackgroundColor(Color.DARK_GRAY);
            table.addCell(cell);
        });
        invoiceData.getMedicines().forEach(m -> {
            table.addCell(new Cell(new Phrase(m.getMedicineMarketName())));
            table.addCell(new Cell(new Phrase(m.getExpiration().toString())));
            table.addCell(new Cell(new Phrase(m.getUnitPrice().toString()+" $")));
            table.addCell(new Cell(new Phrase(m.getCount().toString())));
            table.addCell(new Cell(new Phrase(m.getAmountPrice().toString()+" $")));
        });
        document.add(table);
        document.add(Chunk.NEWLINE);
        Paragraph total = new Paragraph();
        total.add(new Phrase( "TOTAL: ",new Font(Font.TIMES_ROMAN,18,Font.BOLD,Color.BLACK)));
        total.add(new Phrase(invoiceData.getTotPrice().toString()+" $",new Font(Font.TIMES_ROMAN,18,Font.BOLD,Color.BLACK)));
        document.add(total);
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph( "CUSTOMER: "+invoiceData.getCustomerName().toUpperCase(),new Font(Font.HELVETICA,12,Font.NORMAL,Color.BLACK)));
        document.add(new Paragraph( "PHARMACIST: "+invoiceData.getPharmacistName().toUpperCase(),new Font(Font.HELVETICA,12,Font.NORMAL,Color.BLACK)));
        document.add(Chunk.NEWLINE);
        Paragraph thanks = new Paragraph("Thank you for your business.");
        thanks.setAlignment("center");
        document.add(thanks);
        document.close();
        return  pdfPath;
    }
    @Autowired
    public void setBasePath(ConfigUploadPathProperties properties){
        TransactionInvoiceMaker.basePath = properties.getBasePath();
        TransactionInvoiceMaker.transactionPath = properties.getTransactionPath();
        TransactionInvoiceMaker.pathPrefixCount = properties.getPathPrefixCount();
    }
}

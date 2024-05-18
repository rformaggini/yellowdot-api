package com.yellowdot.yellowdotapi.services.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.yellowdot.yellowdotapi.dtos.BillDto;
import com.yellowdot.yellowdotapi.dtos.ProductDto;
import com.yellowdot.yellowdotapi.enums.PaymentStatus;
import com.yellowdot.yellowdotapi.enums.TypeFontPdf;
import com.yellowdot.yellowdotapi.mappers.BillMapper;
import com.yellowdot.yellowdotapi.repositories.BillRepository;
import com.yellowdot.yellowdotapi.repositories.ProductRepository;
import com.yellowdot.yellowdotapi.services.BillService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillMapper billMapper;
    private final ProductRepository productRepository;

    public BillServiceImpl(BillRepository billRepository, BillMapper billMapper, ProductRepository productRepository) {
        this.billRepository = billRepository;
        this.billMapper = billMapper;
        this.productRepository = productRepository;
    }

    @Override
    public String generateReport() {
        return "";
    }

    @Override
    @Transactional
    public BillDto createBill(BillDto dto) throws DocumentException, FileNotFoundException {

        var bill = billMapper.dtoToEntity(dto);
        var productsList = new ArrayList<>();
        dto.products().stream().forEach(product -> {
            var productFromDb = productRepository.findById(product.productId());
            if(productFromDb.isPresent()) {
                productsList.add(productFromDb.get());
            }
        });
        bill.setProducts((List) productsList);
        bill.setStatus(PaymentStatus.OPENED);



        var savedBill = billMapper.entityToDto(billRepository.save(bill));
        generatePdfFromBill(savedBill);
        return savedBill;
    }

    private void generatePdfFromBill(BillDto dto) throws FileNotFoundException, DocumentException {
        var fileName = generateFileName();
        var headerData = "PUB ONLINE - BILL";
        var nameData = "Name: " + dto.name() ;
        var contactNumberData = "Contact Number: " + dto.contactNumber() ;
        var emailData = "Email: " + dto.email() ;
        var paymentMethodData = "Payment Method: " + dto.paymentMethod().toString();
        var total = "Total: " + dto.total();
        var spaceLine = "\n";

        var document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\forgg\\Downloads\\".concat(fileName).concat(".pdf")));

        document.open();
        createBorderInPdf(document);

        var header = new Paragraph(headerData, getFontByType(TypeFontPdf.HEADER));
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);

        var generalInformation = new Paragraph(
                nameData.concat(spaceLine)
                        .concat(contactNumberData)
                        .concat(spaceLine)
                        .concat(emailData)
                        .concat(spaceLine)
                        .concat(paymentMethodData)
                        .concat(spaceLine),
                getFontByType(TypeFontPdf.BODY));
        document.add(generalInformation);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        createHeaderOnTable(table);
        addRowsOnTableFromProductsList(table, dto.products());
        document.add(table);

        var footer = new Paragraph(total.concat(spaceLine), getFontByType(TypeFontPdf.FOOTER));
        document.add(footer);
        document.close();
    }

    private String generateFileName(){
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd-MM-yyyy-hh-mm-ss")
                .withZone(ZoneOffset.UTC);

        var now = LocalDateTime
                .ofInstant(Instant.now(), ZoneOffset.UTC)
                .format(formatter);
        return "Bill-".concat(now.toString());
    }

    private void createBorderInPdf(Document document){
        Rectangle rectangle = new Rectangle(577, 825, 18, 15);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(3);
        rectangle.enableBorderSide(4);
        rectangle.setBorderColor(BaseColor.BLACK);
    }

    private Font getFontByType(TypeFontPdf typeFontPdf){
        switch (typeFontPdf) {
            case HEADER -> {
                return FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            }
            case BODY -> {
                return FontFactory.getFont(FontFactory.TIMES_BOLD, 11, BaseColor.BLACK);
            }
            case FOOTER -> {
                return FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 8, BaseColor.GRAY);
            }
            default -> {
                return FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
            }
        }
    }

    private void createHeaderOnTable(PdfPTable table){
        Stream.of("Product Name", "Category", "Quantity", "Price", "Sub Total")
                .forEach(title -> {
                    var cell = new PdfPCell();
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell.setBorderWidth(2);
                    cell.setPhrase(new Phrase(title, getFontByType(TypeFontPdf.BODY)));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                });
    }

    private void addRowsOnTableFromProductsList(PdfPTable table, List<ProductDto> products){
        products.forEach(product -> {
            var cell = new PdfPCell();
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setBorderWidth(2);
            table.addCell(product.name());
            table.addCell(product.category().name());
            table.addCell(" Quantity ");
            table.addCell(product.price().toString());
            table.addCell(" total ");
        });
    }

}


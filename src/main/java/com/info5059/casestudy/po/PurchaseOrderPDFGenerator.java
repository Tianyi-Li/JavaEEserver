package com.info5059.casestudy.po;
import com.info5059.casestudy.vendor.Vendor;
import com.info5059.casestudy.vendor.VendorRepository;
import com.info5059.casestudy.product.Product;
import com.info5059.casestudy.product.ProductRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PurchaseOrderPDFGenerator - a class for creating dynamic prudct Purchase Order output in PDF
 * format using the iText 7 library
 *
 * @author Tianyi Li
 */
public abstract class PurchaseOrderPDFGenerator extends AbstractPdfView {
    public static ByteArrayInputStream generatePurchaseOrder(String poid,
                                                      PurchaseOrderDAO poDAO,
                                                      VendorRepository vendorRepository,
                                                      ProductRepository productRepository) throws IOException {
        URL imageUrl = PurchaseOrderPDFGenerator.class.getResource("/static/assets/vendor.png");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
// Initialize PDF document to be written to a stream not a file
        PdfDocument pdf = new PdfDocument(writer);
// Document is the main object
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
// add the image to the document
        Image img = new Image(ImageDataFactory.create(imageUrl))
                .scaleAbsolute(120, 40)
                .setFixedPosition(80, 710);
        document.add(img);
// now let's add a big heading
        document.add(new Paragraph("\n\n"));
        Locale locale = new Locale("en", "US");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

        try {
            PurchaseOrder purchaseOrder = poDAO.findOne(Long.parseLong(poid));
            document.add(new Paragraph(String.format("Purchase Order"))
                    .setFont(font)
                    .setFontSize(24)
                    .setMarginRight(75)
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setBold());
            document.add(new Paragraph("#:" + poid)
                    .setFont(font)
                    .setFontSize(16)
                    .setBold()
                    .setMarginRight(90)
                    .setMarginTop(-10)
                    .setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("\n\n"));
// now a 3 column table
            Table table = new Table(2);
            table.setWidth(new UnitValue(UnitValue.PERCENT, 35));
//                 .setHorizontalAlignment(HorizontalAlignment.LEFT);

            Optional<Vendor> opt = vendorRepository.findById(purchaseOrder.getVendorid());
            if (opt.isPresent()) {
                Vendor vendor = opt.get();
// Unfortunately we must format each cell individually :(
// table headings
                Cell cell = new Cell(5,1).add(new Paragraph("Vendor:")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
                cell = new Cell().add(new Paragraph(vendor.getName())
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.LEFT)
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                table.addCell(cell);
                cell = new Cell().add(new Paragraph(vendor.getAddress1())
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.LEFT)
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                table.addCell(cell);
                cell = new Cell().add(new Paragraph(vendor.getCity())
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.LEFT)
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                table.addCell(cell);
                cell = new Cell().add(new Paragraph(vendor.getProvince())
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.LEFT)
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                table.addCell(cell);
                cell = new Cell().add(new Paragraph(vendor.getEmail())
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.LEFT)
                        .setBorder(Border.NO_BORDER)
                        .setBackgroundColor(ColorConstants.LIGHT_GRAY);
                table.addCell(cell);
                document.add(table);

                document.add(new Paragraph("\n\n"));

                // table details
                Table poTable = new Table(5);
                poTable.setWidth(new UnitValue(UnitValue.PERCENT,100));

                cell = new Cell().add(new Paragraph("Product Code")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                poTable.addCell(cell);
                cell = new Cell().add(new Paragraph("Description")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                poTable.addCell(cell);
                cell = new Cell().add(new Paragraph("Qty Sold")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                poTable.addCell(cell);
                cell = new Cell().add(new Paragraph("Price")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                poTable.addCell(cell);
                cell = new Cell().add(new Paragraph("Ext. Price")
                        .setFont(font)
                        .setFontSize(12)
                        .setBold())
                        .setTextAlignment(TextAlignment.CENTER);
                poTable.addCell(cell);

                BigDecimal ext = new BigDecimal(0.0);
                BigDecimal sub = new BigDecimal(0.0);
                BigDecimal tax = new BigDecimal(0.0);
                BigDecimal tot = new BigDecimal(0.0);

// dump out the line items
                for (PurchaseOrderLineItem line : purchaseOrder.getItems()) {
                    Optional<Product> optx = productRepository.findById(line.getProductid());
                    if (optx.isPresent()) {
                        Product product = optx.get();

                        cell = new Cell().add(new Paragraph(product.getId())
                                .setFont(font)
                                .setFontSize(12))
                                .setTextAlignment(TextAlignment.CENTER);
                        poTable.addCell(cell);
                        cell = new Cell().add(new Paragraph(product.getName())
                                .setFont(font)
                                .setFontSize(12))
                                .setTextAlignment(TextAlignment.CENTER);
                        poTable.addCell(cell);
                        cell = new Cell().add(new Paragraph(String.valueOf(line.getQty()))
                                .setFont(font)
                                .setFontSize(12))
                                .setTextAlignment(TextAlignment.RIGHT);
                        poTable.addCell(cell);
                        cell = new Cell().add(new Paragraph(formatter.format(product.getCostprice()))
                                .setFont(font)
                                .setFontSize(12))
                                .setTextAlignment(TextAlignment.RIGHT);
                        poTable.addCell(cell);
                        ext = line.getPrice().multiply(BigDecimal.valueOf(line.getQty()), new MathContext(8, RoundingMode.UP));
                        cell = new Cell().add(new Paragraph(formatter.format(ext))
                                .setFont(font)
                                .setFontSize(12))
                                .setTextAlignment(TextAlignment.RIGHT);
                        poTable.addCell(cell);
                        sub = sub.add(ext, new MathContext(8, RoundingMode.UP));
                    }
                }

                cell = new Cell(1, 4).add(new Paragraph("Sub Total:"))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.RIGHT);
                poTable.addCell(cell);
                cell = new Cell().add(new Paragraph(formatter.format(sub)))
                        .setTextAlignment(TextAlignment.RIGHT);
                poTable.addCell(cell);
                cell = new Cell(1, 4).add(new Paragraph("Tax:"))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.RIGHT);
                poTable.addCell(cell);
                tax = sub.multiply(BigDecimal.valueOf(0.13), new MathContext(8, RoundingMode.UP));
                cell = new Cell().add(new Paragraph(formatter.format(tax)))
                        .setTextAlignment(TextAlignment.RIGHT);
                poTable.addCell(cell);
                cell = new Cell(1, 4).add(new Paragraph("PO Total:"))
                        .setBorder(Border.NO_BORDER)
                        .setTextAlignment(TextAlignment.RIGHT);
                poTable.addCell(cell);
                tot = sub.add(tax, new MathContext(8, RoundingMode.UP));
                cell = new Cell().add(new Paragraph(formatter.format(tot)))
                        .setTextAlignment(TextAlignment.RIGHT)
                        .setBackgroundColor(ColorConstants.YELLOW);
                poTable.addCell(cell);
                document.add(poTable);
            }


            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph(String.valueOf(new Date()))
                    .setTextAlignment(TextAlignment.CENTER));
            document.close();

        } catch (Exception ex) {
            Logger.getLogger(PurchaseOrderPDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
// finally send stream back to the controller
        return new ByteArrayInputStream(baos.toByteArray());
    }
}

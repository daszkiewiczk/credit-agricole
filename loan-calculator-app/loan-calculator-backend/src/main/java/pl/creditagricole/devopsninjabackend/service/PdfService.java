package pl.creditagricole.devopsninjabackend.service;


import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.creditagricole.devopsninjabackend.model.AgroParams;
import pl.creditagricole.devopsninjabackend.model.AgroSchedule;

import java.math.RoundingMode;

@Service
public class PdfService {
    private AgroScheduleService agroScheduleService;
    @Autowired
    public PdfService(AgroScheduleService agroScheduleService) {
        this.agroScheduleService = agroScheduleService;
    }


    public byte[] getAgroPdf(AgroParams agroParams) {
        try {
            AgroSchedule agroSchedule = agroScheduleService.calculateAgroSchedule(agroParams);
            ByteArrayOutputStream pdfDataStream = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, pdfDataStream);

            document.open();
            Table table = new Table(5,agroSchedule.getEntries().size());


            document.add(new Paragraph("Imię i nazwisko: "+agroParams.getName()));
            document.add(new Paragraph("Data zawarcia umowy: "+agroParams.getContractDate().toString()));
            document.add(new Paragraph("Okres finansowania (w miesiącach): "+agroParams.getPeriod().toString()));
            document.add(new Paragraph("Kwota kredytu (w PLN): "+agroParams.getAmount().toString()));
            document.add(new Paragraph("Oprocentowanie: "+agroParams.getInterestRate().toString()));
            table.addCell("Data");
            table.addCell("Zadłużenie");
            table.addCell("rata kapitałowa (PLN)");
            table.addCell("rata odsetkowa (PLN)");
            table.addCell("rata całkowita (PLN)");

            for(int i = 0; i < agroSchedule.getEntries().size(); ++i)
            {
                table.addCell(agroSchedule.getEntries().get(i).getPaymentDate().toString().substring(4,11));
                table.addCell(agroSchedule.getEntries().get(i).getOutstandingPrincipal().toString());
                table.addCell(agroSchedule.getEntries().get(i).getCapitalPart().toString());
                table.addCell(agroSchedule.getEntries().get(i).getInterestPart().toString());
                table.addCell(agroSchedule.getTotalMonthlyPayment().setScale(2, RoundingMode.HALF_EVEN).toString());
            }
            document.add(table);

            document.close();
            pdfDataStream.close();
            return pdfDataStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

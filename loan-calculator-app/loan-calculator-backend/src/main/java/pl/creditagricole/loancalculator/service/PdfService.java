package pl.creditagricole.loancalculator.service;


import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.creditagricole.loancalculator.model.AgroParams;
import pl.creditagricole.loancalculator.model.AgroSchedule;
import pl.creditagricole.loancalculator.model.InvestmentParams;
import pl.creditagricole.loancalculator.model.InvestmentSchedule;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.util.Locale;

@Service
public class PdfService {
    private AgroScheduleService agroScheduleService;
    private InvestmentScheduleService investmentScheduleService;
    private DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.GERMAN);

    @Autowired
    public PdfService(AgroScheduleService agroScheduleService, InvestmentScheduleService investmentScheduleService) {
        this.agroScheduleService = agroScheduleService;
        this.investmentScheduleService = investmentScheduleService;
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

            document.add(new Paragraph("Data zawarcia umowy: "+df.format(agroParams.getContractDate())));    document.add(new Paragraph("Okres finansowania (w miesiącach): "+agroParams.getPeriod().toString()));
            document.add(new Paragraph("Kwota kredytu (w PLN): "+agroParams.getAmount().toString()));
            document.add(new Paragraph("Oprocentowanie: "+agroParams.getInterestRate().toString()+"%"));
            table.addCell("Data");
            table.addCell("Zadłużenie");
            table.addCell("Rata kapitałowa (PLN)");
            table.addCell("Rata odsetkowa (PLN)");
            table.addCell("Rata całkowita (PLN)");

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


    public byte[] getInvestmentPdf(InvestmentParams investmentParams) {
        try {

            InvestmentSchedule investmentSchedule = investmentScheduleService.calculateInvestmentSchedule(investmentParams);
            ByteArrayOutputStream pdfDataStream = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, pdfDataStream);

            document.open();
            Table table = new Table(5,investmentSchedule.getEntries().size());

document.add(new Paragraph("keel pulled this image from docker hub"));
            document.add(new Paragraph("Imię i nazwisko: "+investmentParams.getName()));
            document.add(new Paragraph("Data zawarcia umowy: "+df.format(investmentParams.getContractDate())));
            document.add(new Paragraph("Okres finansowania (w miesiącach): "+investmentParams.getPeriod().toString()));
            document.add(new Paragraph("Kwota kredytu (w PLN): "+investmentParams.getAmount().toString()));
            document.add(new Paragraph("Oprocentowanie: "+investmentParams.getInterestRate().toString()+ "%") );
            document.add(new Paragraph("Wartość inwestycji (w PLN): "+investmentParams.getInvestmentValue().toString()));
            document.add(new Paragraph("Wkład własny (w PLN): "+investmentParams.getOwnContribution().toString()));
            document.add(new Paragraph("Prowizja (w PLN): "+investmentParams.getCommissionValue().toPlainString()));
            table.addCell("Data");
            table.addCell("Zadłużenie");
            table.addCell("Rata kapitałowa (PLN)");
            table.addCell("Rata odsetkowa (PLN)");
            table.addCell("Rata całkowita (PLN)");

            for(int i = 0; i < investmentSchedule.getEntries().size(); ++i)
            {
                table.addCell(investmentSchedule.getEntries().get(i).getPaymentDate().toString().substring(4,11));
                table.addCell(investmentSchedule.getEntries().get(i).getOutstandingPrincipal().toString());
                table.addCell(investmentSchedule.getEntries().get(i).getCapitalPart().toString());
                table.addCell(investmentSchedule.getEntries().get(i).getInterestPart().toString());
                table.addCell(investmentSchedule.getTotalMonthlyPayment().setScale(2, RoundingMode.HALF_EVEN).toString());
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

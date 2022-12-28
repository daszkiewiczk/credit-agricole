package pl.creditagricole.loancalculator.controller;

import org.springframework.web.bind.annotation.*;
import pl.creditagricole.loancalculator.model.AgroParams;
import pl.creditagricole.loancalculator.model.InvestmentParams;
import pl.creditagricole.loancalculator.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;

@RestController
public class Controller {
    private PdfService pdfService;

    @Autowired
    public Controller(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping(value = "/api/agro", produces = APPLICATION_PDF_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte[]> getAgroPdf(@RequestBody AgroParams agroParams) {
        System.out.println(agroParams);
        return ResponseEntity
                .status(200)
                .header("Content-Disposition", "inline; filename=\"agro.pdf\"")
                .body(pdfService.getAgroPdf(agroParams));
    }

    @PostMapping(value = "/api/investment", produces = APPLICATION_PDF_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte[]> getInvestmentPdf(@RequestBody InvestmentParams investmentParams) {
        return ResponseEntity
                .status(200)
                .header("Content-Disposition", "inline; filename=\"investment.pdf\"")
                .body(pdfService.getInvestmentPdf(investmentParams));
    }
}

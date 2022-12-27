package pl.creditagricole.loancalculator.model;

import java.math.BigDecimal;
import java.util.Date;


public class InvestmentScheduleEntry extends ScheduleEntry {

    public InvestmentScheduleEntry(Date paymentDate, BigDecimal capitalPart, BigDecimal interestPart, BigDecimal outstandingPrincipal) {
        super(paymentDate, capitalPart, interestPart, outstandingPrincipal);
    }


}

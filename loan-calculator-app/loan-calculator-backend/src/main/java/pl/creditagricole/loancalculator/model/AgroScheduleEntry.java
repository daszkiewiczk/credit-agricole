package pl.creditagricole.loancalculator.model;

import java.math.BigDecimal;
import java.util.Date;


public class AgroScheduleEntry extends ScheduleEntry {
    public AgroScheduleEntry(Date paymentDate, BigDecimal capitalPart, BigDecimal interestPart, BigDecimal outstandingPrincipal) {
        super(paymentDate, capitalPart, interestPart, outstandingPrincipal);
    }
}

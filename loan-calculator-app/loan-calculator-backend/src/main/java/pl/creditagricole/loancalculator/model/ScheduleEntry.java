package pl.creditagricole.loancalculator.model;

import java.math.BigDecimal;
import java.util.Date;

public class ScheduleEntry {
    private Date paymentDate;
    private BigDecimal capitalPart;
    private BigDecimal interestPart;
    private BigDecimal outstandingPrincipal;

    public ScheduleEntry(Date paymentDate, BigDecimal capitalPart, BigDecimal interestPart, BigDecimal outstandingPrincipal) {
        this.paymentDate = paymentDate;
        this.capitalPart = capitalPart;
        this.interestPart = interestPart;
        this.outstandingPrincipal = outstandingPrincipal;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public BigDecimal getCapitalPart() {
        return capitalPart;
    }

    public BigDecimal getInterestPart() {
        return interestPart;
    }

    public BigDecimal getOutstandingPrincipal() {
        return outstandingPrincipal;
    }


}

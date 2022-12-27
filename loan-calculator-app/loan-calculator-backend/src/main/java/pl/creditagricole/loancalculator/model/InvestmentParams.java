package pl.creditagricole.loancalculator.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "investment_loans")

public class InvestmentParams extends LoanParams {


    private BigDecimal investmentValue;
    private BigDecimal ownContribution;
    private BigDecimal comission;



    public InvestmentParams(String name, Date contractDate, Integer period, BigDecimal amount, BigDecimal interestRate, String scheduleType, BigDecimal investmentValue, BigDecimal ownContribution, BigDecimal comission) {
        super(name, contractDate, period, amount, interestRate, scheduleType);
        this.investmentValue = investmentValue;
        this.ownContribution = ownContribution;
        this.comission = comission;
    }

    public InvestmentParams() {

    }

    public BigDecimal getInvestmentValue() {
        return investmentValue;
    }

    public void setInvestmentValue(BigDecimal investmentValue) {
        this.investmentValue = investmentValue;
    }

    public BigDecimal getOwnContribution() {
        return ownContribution;
    }

    public void setOwnContribution(BigDecimal ownContribution) {
        this.ownContribution = ownContribution;
    }

    public BigDecimal getComission() {
        return comission;
    }

    public void setComission(BigDecimal comission) {
        this.comission = comission;
    }
}

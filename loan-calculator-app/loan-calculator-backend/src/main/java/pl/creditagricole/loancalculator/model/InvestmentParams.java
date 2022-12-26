package pl.creditagricole.loancalculator.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "agro_loans")

public class InvestmentParams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String name;
    @Column(name = "contract_date")
    private Date contractDate;
    private Integer period;
    private BigDecimal amount;
    @Column(name = "interest_rate")
    private BigDecimal interestRate;
    @Column(name = "schedule_type")
    private ScheduleType scheduleType;
    private BigDecimal investmentValue;
    private BigDecimal ownContribution;
    private BigDecimal comission;

    public enum ScheduleType {MONTHLY, QUARTERLY};

    public InvestmentParams() {

    }

    public InvestmentParams(String name, Date contractDate, Integer period, BigDecimal amount, BigDecimal interestRate, String scheduleType) {
        this.name = name;
        this.contractDate = contractDate;
        this.period = period;
        this.amount = amount;
        this.interestRate = interestRate;
        if (scheduleType.equals("monthly")) {
            this.scheduleType = ScheduleType.MONTHLY;
        } else {
            this.scheduleType = ScheduleType.QUARTERLY;
        }
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }


}

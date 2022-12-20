package pl.creditagricole.devopsninjabackend.model;

import java.math.BigDecimal;
import java.util.Date;

public class AgroParams {
    private String name;
    private Date contractDate;
    private Integer period;
    private BigDecimal amount;
    private BigDecimal interestRate;

    private ScheduleType scheduleType;
    public enum ScheduleType {MONTHLY, QUATERLY};


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

    public AgroParams(String name, Date contractDate, Integer period, BigDecimal amount, BigDecimal interestRate, String scheduleType) {
        this.name = name;
        this.contractDate = contractDate;
        this.period = period;
        this.amount = amount;
        this.interestRate = interestRate;
        if(scheduleType.equals("monthly")) {
            this.scheduleType = ScheduleType.MONTHLY;
        } else {
            this.scheduleType = ScheduleType.QUATERLY;
        }
    }
}

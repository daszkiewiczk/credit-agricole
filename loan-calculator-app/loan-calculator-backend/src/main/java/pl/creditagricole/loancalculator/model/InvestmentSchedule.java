package pl.creditagricole.loancalculator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static pl.creditagricole.loancalculator.utils.MathUtils.*;


public class InvestmentSchedule {
    private static InvestmentParams params;
    private List<InvestmentScheduleEntry> entries;
    private BigDecimal totalMonthlyPayment;
    private static BigDecimal pmt(BigDecimal rate, Integer months, BigDecimal presentValue, boolean t) {
        BigDecimal result = BigDecimal.ZERO;
        presentValue = presentValue.subtract(params.getOwnContribution());
        if (rate.compareTo(BigDecimal.ZERO) == 0) {
            result =  new BigDecimal(-1.0).multiply(presentValue).divide(new BigDecimal(months), MATH_CONTEXT);
           // result =presentValue.divide(new BigDecimal(months), MATH_CONTEXT);
        } else {
            BigDecimal r1 = rate.add(BigDecimal.ONE);
            BigDecimal opt = t ? r1 : BigDecimal.ONE;
            result = new BigDecimal(-1.0).multiply(presentValue).multiply(r1.pow(months)).multiply(rate)
                    .divide( opt.multiply(BigDecimal.ONE.subtract(r1.pow(months))), RoundingMode.HALF_EVEN);
        }
        return result;
    }

    public BigDecimal getTotalMonthlyPayment() {
        return totalMonthlyPayment;
    }

    public InvestmentSchedule(InvestmentParams params) {
        this.params = params;
        this.entries = new ArrayList<InvestmentScheduleEntry>();
        BigDecimal a = params.getAmount();

        BigDecimal r = new BigDecimal(0);
        Integer n = 0;

        if(params.getScheduleType() == InvestmentParams.ScheduleType.MONTHLY) {
            r = params.getInterestRate().divide(HUNDRED.multiply(TWELVE),MATH_CONTEXT);
            n = params.getPeriod();
        } else if(params.getScheduleType() == InvestmentParams.ScheduleType.QUARTERLY) {
            r = params.getInterestRate().divide(HUNDRED.multiply(FOUR),MATH_CONTEXT);
            n = params.getPeriod() / 3;
        }


        this.totalMonthlyPayment = pmt(r,n,a,false);

    }


    public List<InvestmentScheduleEntry> getEntries() {
        return entries;
    }
}

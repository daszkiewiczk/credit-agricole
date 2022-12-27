package pl.creditagricole.loancalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.creditagricole.loancalculator.model.InvestmentParams;
import pl.creditagricole.loancalculator.model.InvestmentSchedule;
import pl.creditagricole.loancalculator.model.InvestmentScheduleEntry;
import pl.creditagricole.loancalculator.repository.InvestmentRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import static pl.creditagricole.loancalculator.utils.MathUtils.*;

@Service
public class InvestmentScheduleService {
    private InvestmentRepository investmentRepository;

    private Integer paymentCount;
    private Date paymentInterval;
    @Autowired
    public InvestmentScheduleService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    private Calendar c = Calendar.getInstance();

    public InvestmentSchedule calculateInvestmentSchedule(InvestmentParams investmentParams) {
        investmentRepository.save(investmentParams);

        InvestmentSchedule investmentSchedule = new InvestmentSchedule(investmentParams);

        BigDecimal interestPart = new BigDecimal(0);
        BigDecimal capitalPart = new BigDecimal(0);


        if (investmentParams.getScheduleType() == InvestmentParams.ScheduleType.MONTHLY) {
            paymentCount = investmentParams.getPeriod();

        } else if (investmentParams.getScheduleType() == InvestmentParams.ScheduleType.QUARTERLY)
            paymentCount = investmentParams.getPeriod() / 3;

        BigDecimal outstandingPrincipal = investmentParams.getAmount().subtract(investmentParams.getOwnContribution());


        for (int i = 0; i < paymentCount; i++) {
            c.setTime(investmentParams.getContractDate());
            if (investmentParams.getScheduleType() == InvestmentParams.ScheduleType.QUARTERLY) {

                c.add(Calendar.MONTH * 4, i);
                interestPart = outstandingPrincipal.multiply(investmentParams.getInterestRate().divide(HUNDRED.multiply(FOUR), MATH_CONTEXT)).setScale(2, RoundingMode.HALF_EVEN);
                capitalPart = investmentSchedule.getTotalMonthlyPayment().subtract(interestPart).setScale(2, RoundingMode.HALF_EVEN);

            } else if (investmentParams.getScheduleType() == InvestmentParams.ScheduleType.MONTHLY) {

                c.add(Calendar.MONTH, i);
                interestPart = outstandingPrincipal.multiply(investmentParams.getInterestRate().divide(HUNDRED.multiply(TWELVE), MATH_CONTEXT)).setScale(2, RoundingMode.HALF_EVEN);
                capitalPart = investmentSchedule.getTotalMonthlyPayment().subtract(interestPart).setScale(2, RoundingMode.HALF_EVEN);

            }


            investmentSchedule.getEntries().add(
                    new InvestmentScheduleEntry(
                            c.getTime(),
                            capitalPart,
                            interestPart,
                            outstandingPrincipal.setScale(2, RoundingMode.HALF_EVEN)));

            outstandingPrincipal = outstandingPrincipal.subtract(capitalPart);
        }
        return investmentSchedule;
    }
}

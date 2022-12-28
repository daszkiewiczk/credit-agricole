package pl.creditagricole.loancalculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.creditagricole.loancalculator.model.AgroParams;
import pl.creditagricole.loancalculator.model.AgroSchedule;
import pl.creditagricole.loancalculator.model.AgroScheduleEntry;
import pl.creditagricole.loancalculator.repository.AgroRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import static pl.creditagricole.loancalculator.utils.MathUtils.*;

@Service
public class AgroScheduleService {
    private AgroRepository agroRepository;

    private Integer paymentCount;

    @Autowired
    public AgroScheduleService(AgroRepository agroRepository) {
        this.agroRepository = agroRepository;
    }

    private Calendar c = Calendar.getInstance();

    public AgroSchedule calculateAgroSchedule(AgroParams agroParams) {
        agroRepository.save(agroParams);

        AgroSchedule agroSchedule = new AgroSchedule(agroParams);

        BigDecimal interestPart = new BigDecimal(0);
        BigDecimal capitalPart = new BigDecimal(0);


        if (agroParams.getScheduleType() == AgroParams.ScheduleType.MONTHLY) {
            paymentCount = agroParams.getPeriod();

        } else if (agroParams.getScheduleType() == AgroParams.ScheduleType.QUARTERLY)
            paymentCount = agroParams.getPeriod() / 3;

        BigDecimal outstandingPrincipal = agroParams.getAmount();

        for (int i = 0; i < paymentCount; i++) {
            c.setTime(agroParams.getContractDate());
            if (agroParams.getScheduleType() == AgroParams.ScheduleType.QUARTERLY) {

                c.add(Calendar.MONTH * 4, i);
                interestPart = outstandingPrincipal.multiply(agroParams.getInterestRate().divide(HUNDRED.multiply(FOUR), MATH_CONTEXT)).setScale(2, RoundingMode.HALF_EVEN);
                capitalPart = agroSchedule.getTotalMonthlyPayment().subtract(interestPart).setScale(2, RoundingMode.HALF_EVEN);

            } else if (agroParams.getScheduleType() == AgroParams.ScheduleType.MONTHLY) {

                c.add(Calendar.MONTH, i);
                interestPart = outstandingPrincipal.multiply(agroParams.getInterestRate().divide(HUNDRED.multiply(TWELVE), MATH_CONTEXT)).setScale(2, RoundingMode.HALF_EVEN);
                capitalPart = agroSchedule.getTotalMonthlyPayment().subtract(interestPart).setScale(2, RoundingMode.HALF_EVEN);

            }


            agroSchedule.getEntries().add(
                    new AgroScheduleEntry(
                            c.getTime(),
                            capitalPart,
                            interestPart,
                            outstandingPrincipal.setScale(2, RoundingMode.HALF_EVEN)));

            outstandingPrincipal = outstandingPrincipal.subtract(capitalPart);
        }
        return agroSchedule;
    }
}

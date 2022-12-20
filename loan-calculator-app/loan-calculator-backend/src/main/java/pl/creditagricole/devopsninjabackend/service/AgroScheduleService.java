package pl.creditagricole.devopsninjabackend.service;

import org.springframework.stereotype.Service;
import pl.creditagricole.devopsninjabackend.model.AgroParams;
import pl.creditagricole.devopsninjabackend.model.AgroSchedule;
import pl.creditagricole.devopsninjabackend.model.AgroScheduleEntry;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import static pl.creditagricole.devopsninjabackend.utils.MathUtils.*;

@Service
public class AgroScheduleService {
    private Integer paymentCount;
    private Date paymentInterval;


    private Calendar c = Calendar.getInstance();

    public AgroSchedule calculateAgroSchedule(AgroParams agroParams) {
        AgroSchedule agroSchedule = new AgroSchedule(agroParams);

        BigDecimal interestPart = new BigDecimal(0);
        BigDecimal capitalPart = new BigDecimal(0);


        if (agroParams.getScheduleType() == AgroParams.ScheduleType.MONTHLY) {
            paymentCount = agroParams.getPeriod();

        } else if (agroParams.getScheduleType() == AgroParams.ScheduleType.QUATERLY)
            paymentCount = agroParams.getPeriod() / 3;

        BigDecimal outstandingPrincipal = agroParams.getAmount();

        for (int i = 0; i < paymentCount; i++) {
            c.setTime(agroParams.getContractDate());
            if (agroParams.getScheduleType() == AgroParams.ScheduleType.QUATERLY) {

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

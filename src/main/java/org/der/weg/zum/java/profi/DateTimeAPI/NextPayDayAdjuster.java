package org.der.weg.zum.java.profi.DateTimeAPI;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class NextPayDayAdjuster implements TemporalAdjuster {


    public static void main(String[] args) {
       final var jan31 = LocalDate.of(2020, Month.JANUARY,31);
       final var NextPayDay = jan31.with(new NextPayDayAdjuster());
       System.out.println(NextPayDay);

       final var feb7 = LocalDate.of(2020,Month.FEBRUARY,7);
       final var otherPayday = feb7.with(new NextPayDayAdjuster());
       System.out.println(otherPayday);
    }

    @Override
    public Temporal adjustInto(final Temporal temporal) {
        LocalDate localDate = LocalDate.from(temporal);
        boolean isDecember = localDate.getMonth() == Month.DECEMBER;
        int paymentDay = isDecember ? 15 : 25;

        if(localDate.getDayOfMonth() > paymentDay){
           localDate = localDate.plusMonths(1);
            isDecember = localDate.getMonth().equals(Month.DECEMBER);
            paymentDay = isDecember ? 15:25;
        }

        localDate = localDate.withDayOfMonth(paymentDay);
        if(
                localDate.getDayOfWeek() == DayOfWeek.SATURDAY
                        || localDate.getDayOfWeek() == DayOfWeek.SUNDAY
        ){
            if(isDecember){
                localDate = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
            } else {
                localDate = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY));
            }
        }
        return temporal.with(localDate);
    }
}

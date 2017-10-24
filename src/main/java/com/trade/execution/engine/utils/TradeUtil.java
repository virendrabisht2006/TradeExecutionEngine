package com.trade.execution.engine.utils;


import java.util.Calendar;
import java.util.Date;

public class TradeUtil {

    private TradeUtil() {

    }

    public static Date calculateSettlementDate(Date instructionDate, String currency){
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(instructionDate);
        nextCalendarDay(calendar);

        if(isCurrencyAEDorSAR(currency)){
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                nextTwoCalendarDay(calendar);
            } else if (Calendar.DAY_OF_WEEK == Calendar.SATURDAY) {
                nextCalendarDay(calendar);
            }
        }
        else {
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                calendar.add(Calendar.DAY_OF_YEAR, 2);
            } else if (Calendar.DAY_OF_WEEK == Calendar.SUNDAY) {
                nextCalendarDay(calendar);
            }
        }
        return calendar.getTime();

    }

    private static boolean isCurrencyAEDorSAR(String currency){
        return currency.equals(TradeConstant.CURRENCY_AED) || currency.equals(TradeConstant.CURRENCY_SAR);
    }

    private static void nextCalendarDay(Calendar calendar){
        calendar.add(Calendar.DAY_OF_YEAR, 1);
    }

    private static void nextTwoCalendarDay(Calendar calendar){
        calendar.add(Calendar.DAY_OF_YEAR, 2);
    }
}

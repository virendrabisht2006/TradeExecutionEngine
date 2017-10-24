package com.trade.execution.engine.utils;

import com.trade.execution.engine.model.Indicator;
import com.trade.execution.engine.model.Trade;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TradeUtilTest {

    private DateFormat df;

    @Before
    public void init(){
        df = new SimpleDateFormat(TradeConstant.DATE_FORMAT);
    }

    @Test
    public void shouldReturnSettlementDateBasedOnWeekend() throws Exception{

        Date instructionDate = df.parse("01 Jan 2016");

        Date expectedSettlementDate = df.parse("04 Jan 2016");

        Trade trade = new Trade("foo", Indicator.B, 0.50, "SGP", instructionDate, 200, 100.25 );
        Date actualSettlementDate = trade.getSettlementDate();
        assert(actualSettlementDate).equals(expectedSettlementDate);
    }

    @Test
    public void shouldReturnSettlementDateAsSundayIfCurrencyIsAED() throws Exception{

        Date instructionDate = df.parse("07 Jan 2016");

        Date expectedSettlementDate = df.parse("10 Jan 2016");

        Trade trade = new Trade("foo", Indicator.B, 0.50, "AED", instructionDate, 200, 100.25 );
        Date actualSettlementDate = trade.getSettlementDate();
        assert(actualSettlementDate).equals(expectedSettlementDate);
    }
}
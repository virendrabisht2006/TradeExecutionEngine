package com.virendra.trade.execution.engine;

import com.virendra.trade.execution.engine.utils.FileReaderUtil;
import com.virendra.trade.execution.engine.model.Indicator;
import com.virendra.trade.execution.engine.model.Trade;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class TradeExecutorTest {

    private TradeExecutor tradeExecutor;

    private List<Trade> trades;

    private DateFormat dateFormat;

    //incoming = sell
    //outgoing = buy

    @Before
    public void init() {
        tradeExecutor = new TradeExecutor();
        trades = FileReaderUtil.readFile(null);
        dateFormat = new SimpleDateFormat(TradeConstant.DATE_FORMAT);
    }

    @Test
    public void shouldGetIncomingAmountSettledPerDayInUSD() throws Exception {
        double expectedIncomingAmount = 79136.25;

        String date = "04 Jan 2016";

        double actualIncomingAmount = tradeExecutor.totalTradeAmountPerDay(trades, dateFormat.parse(date), Indicator.S);
        assert (actualIncomingAmount == expectedIncomingAmount);
    }

    @Test
    public void shouldGetOutGoingAmountSettledPerDayInUSD() throws Exception {
        double expectedOutgoingAmount = 90562.5;

        String date = "04 Jan 2016";

        double actualOutgoingAmount = tradeExecutor.totalTradeAmountPerDay(trades, dateFormat.parse(date), Indicator.B);
        assert (actualOutgoingAmount == expectedOutgoingAmount);
    }

    @Test
    public void shouldGetFirstRankedIncomingEntity() throws Exception {
        String expectedEntity = "jpmc";
        List<Trade> rankedTrades = tradeExecutor.getFirstRankedTrade(trades, Indicator.S);
        assert (rankedTrades.size() == 3);
        assert (rankedTrades.get(0).getEntity()).equals(expectedEntity);
    }

    @Test
    public void shouldGetFirstRankedOutgoingEntity() throws Exception {
        String expectedOutgoingEntity = "sbi";
        List<Trade> rankedTrades = tradeExecutor.getFirstRankedTrade(trades, Indicator.B);
        assert (rankedTrades.size() == 3);
        assert (rankedTrades.get(0).getEntity()).equals(expectedOutgoingEntity);
    }


}
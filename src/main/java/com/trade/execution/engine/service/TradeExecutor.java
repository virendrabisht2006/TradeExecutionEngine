package com.trade.execution.engine.service;


import com.trade.execution.engine.model.Indicator;
import com.trade.execution.engine.model.Trade;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TradeExecutor implements TradeRepository {

    private final static Logger logger = Logger.getLogger(TradeExecutor.class);
    private final Comparator<Trade> comparator = Comparator.comparing(trade -> (calculateTotalAmountForTrade(trade)));

    @Override
    public double totalTradeAmountPerDay(List<Trade> trades, Date settledDate, Indicator indicator){
        logger.info("About to calculate Total Traded Amount: for Date: " + settledDate + " and for Indicator: " + indicator.getName());
        List<Double> amountList =  trades.stream().
                filter(trade -> filterByTradeIndicatorAndInstructionDate(trade, indicator, settledDate))
                .map(trade -> calculateTotalAmountForTrade(trade)).collect(Collectors.toList());
        return amountList.stream().reduce(0.0, Double::sum);
    }

    @Override
    public List<Trade> getFirstRankedTrade(List<Trade> trades, Indicator indicator) {
        logger.info("Get List of Trade in descending order by total amount for Indicator: " + indicator.getName());
        return trades.stream().filter(trade -> trade.getIndicator().equals(indicator)).sorted(comparator.reversed()).collect(Collectors.toList());

    }

    private boolean filterByTradeIndicatorAndInstructionDate(Trade trade, Indicator indicator, Date settlementDate){
        return trade.getIndicator().equals(indicator) && trade.getSettlementDate().equals(settlementDate);
    }

    private Double calculateTotalAmountForTrade(Trade trade){
        return trade.getUnits() * trade.getPricePerUnit() * trade.getAgreedFx();
    }

}

package com.virendra.trade.execution.engine;


import com.virendra.trade.execution.engine.model.Indicator;
import com.virendra.trade.execution.engine.model.Trade;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TradeExecutor {

    public double totalTradeAmountPerDay(List<Trade> trades, Date settledDate, Indicator indicator){
        List<Double> amountList =  trades.stream().
                filter(trade -> filterByTradeIndicatorAndInstructionDate(trade, indicator, settledDate))
                .map(trade -> calculateTotalAmountForTrade(trade)).collect(Collectors.toList());
       // amountList.forEach(System.out::println);
        return amountList.stream().reduce(0.0, Double::sum);
    }

    public List<Trade> getFirstRankedTrade(List<Trade> trades, Indicator indicator) {

        List<Trade> rankedTrades = trades.stream().filter(trade -> trade.getIndicator().equals(indicator)).sorted(comparator.reversed()).collect(Collectors.toList());
               // .max(comp)
               // .get();
        return rankedTrades;

    }

    private boolean filterByTradeIndicatorAndInstructionDate(Trade trade, Indicator indicator, Date settlementDate){
        return trade.getIndicator().equals(indicator) && trade.getSettlementDate().equals(settlementDate);
    }

    private Double calculateTotalAmountForTrade(Trade trade){
        return trade.getUnits() * trade.getPricePerUnit() * trade.getAgreedFx();
    }

   final Comparator<Trade> comparator = Comparator.comparing(trade -> (calculateTotalAmountForTrade(trade)));

}

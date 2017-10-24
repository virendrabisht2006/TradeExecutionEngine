package com.trade.execution.engine;


import com.trade.execution.engine.model.Indicator;
import com.trade.execution.engine.model.Trade;
import com.trade.execution.engine.service.TradeExecutor;
import com.trade.execution.engine.utils.TradeFileReaderUtil;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TradeExecutionEngine {

    private final static Logger logger = Logger.getLogger(TradeExecutionEngine.class);

    public static void main(String[] args) throws Exception{

        TradeExecutor tradeExecutor = new TradeExecutor();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ',' separated input trade instruction csv file path for execution");
        String file = scanner.nextLine();

        logger.info("Input file path: " + file);

        List<Trade> trades = TradeFileReaderUtil.readTradesFromFile(file);

        List<Date>  settlementDates = trades.stream().map(trade -> trade.getSettlementDate()).distinct().collect(Collectors.toList());


        System.out.println("\n ------- Calculate Amount in USD settled incoming everyday ----------");
        settlementDates.forEach(settlementDate->{
            System.out.println("Total settled incoming amount in USD: $"+tradeExecutor.totalTradeAmountPerDay(trades, settlementDate, Indicator.S)+", for Date: "+settlementDate);
        });


        System.out.println("\n -------- Calculate Amount in USD settled outgoing everyday ---------");
        settlementDates.forEach(settlementDate->{
            System.out.println("Total settled outgoing amount in USD: $"+tradeExecutor.totalTradeAmountPerDay(trades, settlementDate, Indicator.B)+", for Date: "+settlementDate);
        });


        System.out.println("\n ---------- Ranked Incoming Trade -------------");

        tradeExecutor.getFirstRankedTrade(trades, Indicator.S).forEach(trade -> {
            System.out.println("Entity: "+trade.getEntity()+ ", Total Amount: "+ calculateTotalAmountForTrade(trade));
        });


        System.out.println("\n ---------- Ranked Outgoing Trade -------------");
        tradeExecutor.getFirstRankedTrade(trades, Indicator.B).forEach(trade -> {
            System.out.println("Entity: "+trade.getEntity()+ ", Total Amount: "+ calculateTotalAmountForTrade(trade));
        });

    }

    private static Double calculateTotalAmountForTrade(Trade trade){
        return trade.getUnits() * trade.getPricePerUnit() * trade.getAgreedFx();
    }
}

package com.virendra.trade.execution.engine;


import com.virendra.trade.execution.engine.model.Indicator;
import com.virendra.trade.execution.engine.model.Trade;
import com.virendra.trade.execution.engine.utils.FileReaderUtil;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TradeExecutionEngine {

    public static void main(String[] args) throws Exception{

        TradeExecutor tradeExecutor = new TradeExecutor();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ',' separated input trade instruction csv file path for execution");
        String file = scanner.nextLine();

        System.out.println("Input file path: "+ file);

        List<Trade> trades = FileReaderUtil.readFile(file);

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

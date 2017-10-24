package com.trade.execution.engine.service;


import com.trade.execution.engine.model.Indicator;
import com.trade.execution.engine.model.Trade;

import java.util.Date;
import java.util.List;

public interface TradeRepository {

    double totalTradeAmountPerDay(List<Trade> trades, Date settledDate, Indicator indicator);

    List<Trade> getFirstRankedTrade(List<Trade> trades, Indicator indicator);
}

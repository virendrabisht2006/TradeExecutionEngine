package com.trade.execution.engine.utils;

import com.trade.execution.engine.model.Indicator;
import com.trade.execution.engine.model.Trade;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class TradeFileReaderUtil {

    private static final String FILE_INSTRUCTION ="trade-instruction.csv";

    private static final String DELIMETER =",";

    private final static Logger logger = Logger.getLogger(TradeFileReaderUtil.class);

    private TradeFileReaderUtil() {

    }

    public static List<Trade> readTradesFromFile(String path) {
        String file = path;
        List<Trade> trades = new ArrayList<>();

        if(null == path || path.isEmpty()) {
            file = String.valueOf(TradeFileReaderUtil.class.getClassLoader().getResource(FILE_INSTRUCTION).getFile());
        }

        try (Stream<String> lines = Files.lines(Paths.get(file)).skip(1)){
            lines.forEach(line->{
               String trade[] = line.split(DELIMETER);
                    String entity = trade[0];
                    Indicator indicator = Indicator.valueOf(trade[1]);
                    double agreedFx = Double.valueOf(trade[2]);
                    String currency = trade[3];
                    Date date = getParseDate(trade[4]);
                    long units = Long.valueOf(trade[6]);
                    double pricePerUnit = Double.valueOf(trade[7]);
                    trades.add(new Trade(entity, indicator, agreedFx, currency, date, units, pricePerUnit));
            });
           // lines.close();

        } catch (IOException e) {
            logger.error("Exception occurred while reading file: ", e);
            e.printStackTrace();
        }

        return trades;
    }


    private static Date  getParseDate(String strDate) throws RuntimeException{
        try {
           return new SimpleDateFormat(TradeConstant.DATE_FORMAT).parse(strDate);
        }
        catch(ParseException e) {
            logger.error("Exception in parsing date: ", e);
            throw new RuntimeException(e);
        }

    }
}

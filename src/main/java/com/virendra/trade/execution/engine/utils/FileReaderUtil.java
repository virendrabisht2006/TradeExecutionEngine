package com.virendra.trade.execution.engine.utils;

import com.virendra.trade.execution.engine.TradeConstant;
import com.virendra.trade.execution.engine.model.Indicator;
import com.virendra.trade.execution.engine.model.Trade;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class FileReaderUtil {

    private static final String FILE_INSTRUCTION ="trade-instruction.csv";

    private static final String DELIMETER =",";

    public static List<Trade> readFile(String path) {
        String file = path;
        List<Trade> trades = new ArrayList<>();

        if(null == path || path.isEmpty()) {
            file = String.valueOf(FileReaderUtil.class.getClassLoader().getResource(FILE_INSTRUCTION).getFile());
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
            System.out.println("Exception occurred while reading file: "+e);
            e.printStackTrace();
        }

        return trades;
    }


    private static Date  getParseDate(String strDate) throws RuntimeException{
        try {
           return new SimpleDateFormat(TradeConstant.DATE_FORMAT).parse(strDate);
        }
        catch(ParseException e) {
            System.out.println("Exception in parsing date: "+ e);
            throw new RuntimeException(e);
        }

    }
}

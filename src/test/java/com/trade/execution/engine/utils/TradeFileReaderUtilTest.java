package com.trade.execution.engine.utils;

import com.trade.execution.engine.model.Trade;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;


public class TradeFileReaderUtilTest {


    @Test
    public void shouldReadFileFromTestResourceIfFilePathNotSpecified(){

        List<Trade> trades = TradeFileReaderUtil.readFile(null);
        int expectedSize = 6;
        assertNotNull(trades);
        assert(trades.size() == expectedSize);

    }

    @Test
    public void shouldReadFileFromSpecifiedPath() {
        File file = new File("temp_file.text");

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            bufferedWriter.write("Entity,Buy/Sell,AgreedFx,Currency,InstructionDate,SettlementDate,Units,Price per unit");
            bufferedWriter.write("\n");
            bufferedWriter.write("foo,B,0.5,SGP,01 Jan 2016,04 Jan 2016,200,100.25");
            List<Trade> trades = TradeFileReaderUtil.readFile(file.getAbsolutePath());
            file.deleteOnExit();
            assertNotNull(trades);

        }
        catch (IOException io){
            System.out.println("Error in wrting file: "+io);
        }
    }



}
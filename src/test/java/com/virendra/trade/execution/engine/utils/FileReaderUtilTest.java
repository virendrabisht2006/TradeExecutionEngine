package com.virendra.trade.execution.engine.utils;
import com.virendra.trade.execution.engine.model.Trade;
import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.Assert.*;


public class FileReaderUtilTest {


    @Test
    public void shouldReadFileFromTestResourceIfFilePathNotSpecified(){

        List<Trade> trades = FileReaderUtil.readFile(null);
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
            List<Trade> trades = FileReaderUtil.readFile(file.getAbsolutePath());
            file.deleteOnExit();
            assertNotNull(trades);

        }
        catch (IOException io){
            System.out.println("Error in wrting file: "+io);
        }
    }



}
# TradeExectionEngine

Description: TradeExecutionEngine is the simple application which process the incoming trade everyday from Monday to Friday.

Archetectural Decision: Junit4 and log4j is used as external library. Should use maximum feature of java8.

--Assumption Taken
1- All instructionDate should be in working day. i.e. from Monday to Friday for all currency except AED and SAR.
2- Input Instruction list will be in below format and same sequence of column and will be provided in csv format file. Application will read csv file and produce the required output console.

Entity,Buy/Sell,AgreedFx,Currency,InstructionDate,SettlementDate,Units,Price per unit
foo,B,0.5,SGP,01 Jan 2016,04 Jan 2016,200,100.25
bar,S,0.22,AED,01 Jan 2016,07 Jan 2016,450,150.5

3- Input Instruction Date format would be dd MMM yyyy

4- Ranked trade listed in descending order of total trade amount

How it works:

TradeExecutionEngine is the Entry point of application, which has main method. It will ask for file path of instruction file need to processed.
Enter the Input Instruction csv file path, which will be processed. It assume csv file would be comma (i.e. ",") separated.
If user does not enter any file path, it takes process default file from resource directory of application.

NOTE- some time in csv format file, if we explicitly doest not provide date format it takes dd-mm-yy. So we should format our instructionDate
as dd MMM yyyy


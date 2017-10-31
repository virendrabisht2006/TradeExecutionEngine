package feature.trade;

import com.trade.execution.engine.model.Indicator;
import com.trade.execution.engine.model.Trade;
import com.trade.execution.engine.service.TradeExecutor;
import com.trade.execution.engine.service.TradeRepository;
import com.trade.execution.engine.utils.TradeConstant;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TradeSteps {

    private TradeRepository tradeRepository = new TradeExecutor();

    private List<Trade> trades = new ArrayList<>();

    private double actualAmount;

    private DateFormat dateFormat = new SimpleDateFormat(TradeConstant.DATE_FORMAT);

    @Given("^the incoming instruction for trade initialized with the following data$")
    public void the_incoming_instruction_for_trade_initialized_with_the_following_data(final List<TradeMapper> tradeMappers) throws Throwable {
        for (TradeMapper tradeMapper : tradeMappers) {
            trades.add(new Trade(tradeMapper.getEntity(),
                    tradeMapper.getIndicator(),
                    tradeMapper.getAgreedFx(),
                    tradeMapper.getCurrency(),
                    dateFormat.parse(tradeMapper.getInstructionDate()),
                    tradeMapper.getUnits(),
                    tradeMapper.getPricePerUnit()));
        }

        trades.forEach(trade -> System.out.println(trade));

    }

    @When("^calculate total trade for type '(.*)' for date '(.+)'$")
    public void calculate_total_tarde_for_type_s_for_date(final String s, final String dateStr) throws Throwable {
        Date date = dateFormat.parse(dateStr);
        actualAmount = tradeRepository.totalTradeAmountPerDay(trades, date, Indicator.valueOf(s));
    }

    @Then("^total incoming trade for date '(.+)' should (.+)$")
    public void total_incoming_tarde_for_date_should(final String date, final double amount) throws Throwable {

        assert (actualAmount == amount);
    }
}


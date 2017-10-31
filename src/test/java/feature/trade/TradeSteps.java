package feature.trade;

import com.trade.execution.engine.model.Indicator;
import com.trade.execution.engine.model.Trade;
import com.trade.execution.engine.service.TradeExecutor;
import com.trade.execution.engine.service.TradeRepository;
import com.trade.execution.engine.utils.TradeConstant;
import cucumber.api.Format;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.BeforeClass;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TradeSteps {

    private TradeRepository tradeRepository = new TradeExecutor();

    private List<Trade> trades = new ArrayList<>();

    private double actualAmount;

    private int expectedSize;

    private String expectedEntity;

    private DateFormat dateFormat = new SimpleDateFormat(TradeConstant.DATE_FORMAT);

    @BeforeClass
    @Given("^the incoming instruction for trade initialized with the following data$")
    public void the_incoming_instruction_for_trade_initialized_with_the_following_data(@Format("dd MMM yyyy") final List<Trade> trades) throws Throwable {
        this.trades = trades;

        this.trades.forEach(trade -> System.out.println(trade));

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

    @Then("^calculate first rank trade for type '(.*)'$")
    public void calculate_first_rank_tarde_for_type(final String type) throws Throwable {

        List<Trade> rankedTrades = tradeRepository.getFirstRankedTrade(trades, Indicator.valueOf(type));
        expectedEntity = rankedTrades.get(0).getEntity();
        expectedSize = rankedTrades.size();
    }

    @Then("^first rank trade should be '(.*)' and all trade size should be (\\d+)$")
    public void first_rank_tarde_should_be_and_all_trade_size_should_be(final String entity, final int size) throws Throwable {

        assert (entity.equals(expectedEntity));
        assert (size == expectedSize);
    }
}


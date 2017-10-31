package feature.trade;

import com.trade.execution.engine.utils.TradeConstant;
import cucumber.api.Transformer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by esha on 30/10/17.
 */
public class DateMapper extends Transformer<Date> {

    @Override
    public Date transform(String dateStr) {
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(TradeConstant.DATE_FORMAT);
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Error in date parsing: " + e);
        }
        return date;
    }

}

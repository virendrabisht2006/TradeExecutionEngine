package feature.trade;


import com.trade.execution.engine.model.Indicator;

public class TradeMapper {

    private String entity;

    private Indicator indicator;

    private double agreedFx;

    private String currency;

    private String instructionDate;

    private long units;

    private double pricePerUnit;

    public TradeMapper(String entity, Indicator indicator, double agreedFx, String currency, String instructionDate, long units, double pricePerUnit) {
        this.entity = entity;
        this.indicator = indicator;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }

    public String getEntity() {
        return entity;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public double getAgreedFx() {
        return agreedFx;
    }

    public String getCurrency() {
        return currency;
    }

    public String getInstructionDate() {
        return instructionDate;
    }

    public long getUnits() {
        return units;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }
}

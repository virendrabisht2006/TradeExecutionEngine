package feature.trade;


import com.trade.execution.engine.model.Indicator;

public class TradeMapper {

    private String entity;

    private Indicator indicator;

    private double agreedFx;

    private String currency;

    private String instructionDate;

    private String settlementDate;

    private long units;

    private double pricePerUnit;

    public TradeMapper(String entity, Indicator indicator, double agreedFx, String currency, String instructionDate, String settlementDate, long units, double pricePerUnit) {
        this.entity = entity;
        this.indicator = indicator;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
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

    public String getSettlementDate() {
        return settlementDate;
    }

    public long getUnits() {
        return units;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }
}

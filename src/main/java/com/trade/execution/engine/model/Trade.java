package com.trade.execution.engine.model;


import com.trade.execution.engine.utils.TradeUtil;

import java.util.Date;

public class Trade {

    private String entity;

    private Indicator indicator;

    private double agreedFx;

    private String currency;

    private Date instructionDate;

    private Date settlementDate;

    private long units;

    private double pricePerUnit;

    public Trade(String entity, Indicator indicator, double agreedFx, String currency, Date instructionDate, long units, double pricePerUnit) {
        this.entity = entity;
        this.indicator = indicator;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trade trade = (Trade) o;

        if (Double.compare(trade.agreedFx, agreedFx) != 0) return false;
        if (units != trade.units) return false;
        if (Double.compare(trade.pricePerUnit, pricePerUnit) != 0) return false;
        if (entity != null ? !entity.equals(trade.entity) : trade.entity != null) return false;
        if (indicator != trade.indicator) return false;
        if (currency != null ? !currency.equals(trade.currency) : trade.currency != null) return false;
        return instructionDate != null ? instructionDate.equals(trade.instructionDate) : trade.instructionDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = entity != null ? entity.hashCode() : 0;
        result = 31 * result + (indicator != null ? indicator.hashCode() : 0);
        temp = Double.doubleToLongBits(agreedFx);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (instructionDate != null ? instructionDate.hashCode() : 0);
        result = 31 * result + (int) (units ^ (units >>> 32));
        temp = Double.doubleToLongBits(pricePerUnit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public double getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(double agreedFx) {
        this.agreedFx = agreedFx;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(Date instructionDate) {
        this.instructionDate = instructionDate;
    }

    public long getUnits() {
        return units;
    }

    public void setUnits(long units) {
        this.units = units;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Date getSettlementDate() {
      return TradeUtil.calculateSettlementDate(instructionDate, currency);
    }

    @Override
    public String toString() {
            return "Trade{" +
                    "entity='" + entity + '\'' +
                    ", indicator=" + indicator +
                    ", agreedFx=" + agreedFx +
                    ", currency='" + currency + '\'' +
                    ", instructionDate=" + instructionDate +
                    ", settlementDate=" + this.getSettlementDate() +
                    ", units=" + units +
                    ", pricePerUnit=" + pricePerUnit +
                    '}';

    }
}

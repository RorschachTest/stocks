package com.dalaalstreet.stocks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
/*
 "1. open": "1139.7400",
         "2. high": "1141.6400",
         "3. low": "1139.7400",
         "4. close": "1141.4000",
         "5. volume": "95974"*/

public class CandleData {

    @JsonIgnore
    private Date timeStamp;

    @JsonIgnore
    private String symbol;

    @JsonProperty("1. open")
    private Double open;

    @JsonProperty("2. high")
    private Double high;

    @JsonProperty("3. low")
    private Double low;

    @JsonProperty("4. close")
    private Double close;

    @JsonProperty("5. volume")
    private Double volume;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public CandleData(CandleData c) {
        this.timeStamp = null != c.timeStamp ? c.timeStamp: new Date();
        this.close = c.close;
        this.high = c.high;
        this.low = c.low;
        this.close = c.close;
        this.volume = c.volume;
    }

    public CandleData() {
    }

    @Override
    public String toString() {
        return "CandleData{" +
                "timeStamp=" + timeStamp +
                ", symbol='" + symbol + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                '}';
    }
}

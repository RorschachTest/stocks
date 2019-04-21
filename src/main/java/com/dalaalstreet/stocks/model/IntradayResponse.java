package com.dalaalstreet.stocks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public class IntradayResponse {

    @JsonProperty("Meta Data")
    private MetaData metaData;

    @JsonProperty("Time Series (5min)")
    private Map<String, CandleData> candleData;

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public Map<String, CandleData> getCandleData() {
        return candleData;
    }

    public void setCandleData(Map<String, CandleData> candleData) {
        this.candleData = candleData;
    }

    @Override
    public String toString() {
        return "IntradayResponse{" +
                "metaData='" + metaData + '\'' +
                ", candleData=" + candleData +
                '}';
    }
}

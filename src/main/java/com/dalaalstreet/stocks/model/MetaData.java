package com.dalaalstreet.stocks.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/*"1. Information": "Intraday (1min) open, high, low, close prices and volume",
        "2. Symbol": "GOOG",
        "3. Last Refreshed": "2019-03-01 16:00:00",
        "4. Interval": "1min",
        "5. Output Size": "Compact",
        "6. Time Zone": "US/Eastern"*/


public class MetaData {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("3. Last Refreshed")
    private Date timeStamp;

    @JsonProperty("6. Time Zone")
    private String timeZone;

    @JsonProperty("2. Symbol")
    private String symbol;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "MetaData{" +
                "timeStamp=" + timeStamp +
                ", timeZone='" + timeZone + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}

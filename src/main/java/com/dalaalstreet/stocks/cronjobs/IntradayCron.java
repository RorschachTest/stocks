package com.dalaalstreet.stocks.cronjobs;

import com.dalaalstreet.stocks.config.HibernateUtil;
import com.dalaalstreet.stocks.entity.IntradayCandleEntity;
import com.dalaalstreet.stocks.model.CandleData;
import com.dalaalstreet.stocks.model.IntradayResponse;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.util.resources.cldr.am.TimeZoneNames_am;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@Component
public class IntradayCron {

    Logger logger = LoggerFactory.getLogger(IntradayCron.class);

    @Value("${alphavantage.apikey}")
    private String apikey;

    private Gson gson;

    @Scheduled(fixedDelay = 5000)
    public void getInteraDayData(){
        logger.info("Intraday Cron started at"+ new Date().getTime());

        RestTemplate restTemplate = new RestTemplate();

        StringBuilder urlBuilder = new StringBuilder();

        urlBuilder.append("https://www.alphavantage.co/query?");

        String function = "TIME_SERIES_INTRADAY";
        int interval = 5;
        String symbol = "ZUMZ";
        String outputSize = "full";
        String datatype = "json";

        urlBuilder.append("function="+ function);
        urlBuilder.append("&interval="+interval+"min");
        urlBuilder.append("&symbol="+symbol);
        urlBuilder.append("&outputsize="+outputSize);
        urlBuilder.append("&datatype="+datatype);

        urlBuilder.append("&apikey="+apikey);

        String uri = urlBuilder.toString();
        IntradayResponse intradayResponse = restTemplate.getForObject(uri, IntradayResponse.class);

        logger.info(intradayResponse.toString());

        String dataTimeZone = intradayResponse.getMetaData().getTimeZone();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatter.setTimeZone(TimeZone.getTimeZone(dataTimeZone));

        // Convert Time into IST

        Map<String, CandleData> candleDataMap = intradayResponse.getCandleData();

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            candleDataMap.forEach((timeStamp, candleData) -> {
                IntradayCandleEntity intradayCandleEntity = new IntradayCandleEntity();
                Date candleforTime = null;
                try{
                    candleforTime = dateFormatter.parse(timeStamp);
                }catch (Exception e){
                    logger.error(e.getMessage());
                }

                intradayCandleEntity.setSymbol(symbol);
                intradayCandleEntity.setTickerTime(candleforTime);
                intradayCandleEntity.setOpen(candleData.getOpen());
                intradayCandleEntity.setHigh(candleData.getHigh());
                intradayCandleEntity.setLow(candleData.getLow());
                intradayCandleEntity.setClose(candleData.getClose());
                intradayCandleEntity.setVolume(candleData.getVolume());

                session.save(intradayCandleEntity);
            });

            session.getTransaction().commit();
            HibernateUtil.shutdown();

        }catch (Exception e){
            logger.error(e.toString());
        }
    }

}

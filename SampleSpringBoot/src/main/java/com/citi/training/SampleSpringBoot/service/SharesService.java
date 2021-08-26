package com.citi.training.SampleSpringBoot.service;
import com.citi.training.SampleSpringBoot.entities.Shares;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
@Service
public interface SharesService {
    Collection<Shares> getAllShares();
    Collection<Shares>  getShareBySymbol (String symbol);
    //Collection<Shares> getMyShares();
    Double getTotalNetWorth() throws IOException;
    void  addNewShare(Shares share) throws IOException;
    void sellShares(Shares sh) throws IOException;
    Double getTotalProfit(int id) throws IOException;
    Double getBookValue(int id) throws IOException;
    Double getTotalShares(String symbol);
    String getCurrentStockInfor(String symbol) throws IOException, JSONException;
    ArrayList<String> getMySharesName();
    String getMarketGainers() throws IOException, InterruptedException, JSONException;
    String getMarketLosers() throws IOException, InterruptedException, JSONException;
    String getMarketSummary() throws IOException, InterruptedException, JSONException;
    String getMySharesPrices() throws IOException, JSONException;
    String getMySharesChange() throws IOException, JSONException;
    String getMySharesQuantity() throws JSONException;
}
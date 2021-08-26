package com.citi.training.SampleSpringBoot.service;
import com.citi.training.SampleSpringBoot.entities.Shares;
import com.citi.training.SampleSpringBoot.repo.SharesRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.*;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class SharesServiceImpl implements SharesService {
    @Autowired
    private SharesRepository sharesRepository;
    @Override
    public Collection<Shares> getAllShares() {
        return sharesRepository.findAll();
    }
    @Override
    public Collection<Shares> getShareBySymbol (String symbol) {
        return sharesRepository.findBySymbol(symbol);
    }

    @Override
    public void addNewShare(Shares share) throws IOException {
        share.setTransaction_type("buy");
        share.setTransaction_date();
        share.setTransaction_price();
        sharesRepository.save(share);
    }
    @Override
    public void sellShares(Shares share) throws IOException {
        share.setTransaction_type("sell");
        share.setTransaction_date();
        share.setTransaction_price();
        sharesRepository.save(share);
    }

    public Double getTotalShares(String symbol){
        Double total = 0.0;
        List<Shares> shares  = sharesRepository.findAll();
        for(int i = 0 ; i < shares.size(); i++){
            if(shares.get(i).getSymbol().equals(symbol) && shares.get(i).getTransaction_type().equals("buy")){
                total += shares.get(i).getVolume();
            }
            else if (shares.get(i).getSymbol().equals(symbol)){
                total -= shares.get(i).getVolume();
            }
        }
        return total;
    }

    @Override
    public ArrayList<String> getMySharesName(){
        List<String> shares = new ArrayList<>();
        List<Shares> Reposhares  = sharesRepository.findAll();
        for(int i =0; i < Reposhares.size(); i++){
            if(getTotalShares(Reposhares.get(i).getSymbol()) >0 && !shares.contains(Reposhares.get(i).getSymbol())){
                shares.add(Reposhares.get(i).getSymbol());
            }
        }
        return (ArrayList<String>) shares;
    }

    @Override
    public String getMySharesPrices() throws IOException, JSONException {
        List<String> shares = getMySharesName();
        JSONObject outputJsonObj = new JSONObject();
        for(int i =0; i < shares.size(); i++){
            Stock stock1 = YahooFinance.get(shares.get(i));
            BigDecimal price = stock1.getQuote().getPrice();
            outputJsonObj.put( shares.get(i), price);
        }
        return outputJsonObj.toString();
    }

    @Override
    public String getMySharesChange() throws IOException, JSONException {
        List<String> shares = getMySharesName();
        JSONObject outputJsonObj = new JSONObject();
        for(int i =0; i < shares.size(); i++){
            Stock stock1 = YahooFinance.get(shares.get(i));
            BigDecimal price = stock1.getQuote().getChangeInPercent();
            outputJsonObj.put( shares.get(i), price);
        }
        return outputJsonObj.toString();
    }

    @Override
    public Double getBookValue(int id) throws IOException {
        List<Shares> shares  = (List<Shares>) sharesRepository.findById(id);
        Stock stock = YahooFinance.get(shares.get(0).getSymbol());
        Double total = shares.get(0).getTransaction_price() * shares.get(0).getVolume();
        return total;
    }

    @Override
    public Double getTotalProfit(int id) throws IOException {
        List<Shares> shares  = (List<Shares>) sharesRepository.findById(id);
        Stock stock = YahooFinance.get(shares.get(0).getSymbol());
        Double total = ( stock.getQuote().getPrice().doubleValue()- shares.get(0).getTransaction_price() ) * shares.get(0).getVolume();
        return total;
    }
    @Override
    public String getMySharesQuantity() throws JSONException {
        ArrayList<String> shares = getMySharesName();
        JSONArray outputJsonObj = new JSONArray();
        for(int i = 0 ; i < shares.size(); i++){
            JSONObject obj = new JSONObject();
            obj.put("symbol", shares.get(i));
            obj.put("quantity", getTotalShares(shares.get(i)));
            outputJsonObj.put(i,obj);
        }
        return outputJsonObj.toString();
    }

    @Override
    public Double getTotalNetWorth() throws IOException {
        Double total = 0.0;
        ArrayList<String> shares =  getMySharesName();

        for(int i = 0 ; i < shares.size(); i++){
            Stock stock = YahooFinance.get(shares.get(i));
            total += getTotalShares(shares.get(i)) * stock.getQuote().getPrice().doubleValue();
        }
        return total;
    }

    @Override
    public String getCurrentStockInfor(String symbol) throws IOException, JSONException {
        Stock stock = YahooFinance.get(symbol);

        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal change = stock.getQuote().getChangeInPercent();
        BigDecimal peg = stock.getStats().getPeg();
        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

       // stock.print();
        JSONObject outputJsonObj = new JSONObject();
        outputJsonObj.put("name", stock.getName());
        outputJsonObj.put("price", price);
        outputJsonObj.put("change", change);
        outputJsonObj.put("peg", peg);
        outputJsonObj.put("dividend", dividend);
        return outputJsonObj.toString();
    }

    @Override
    public String getMarketGainers() throws IOException, InterruptedException, JSONException {
        String output ="";
        URL url = new URL("https://financialmodelingprep.com/api/v3/stock/gainers?apikey=d9b53b70b77c85604f56ebd6be5d7909");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                output += line +"\n";
            }
        }
        return output;
    }

    @Override
    public String getMarketLosers() throws IOException, InterruptedException, JSONException {
        String output ="";
        URL url = new URL("https://financialmodelingprep.com/api/v3/stock/losers?apikey=d9b53b70b77c85604f56ebd6be5d7909");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                output += line +"\n";
            }
        }
        return output;
    }

        @Override
    public String getMarketSummary() throws IOException, InterruptedException, JSONException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-summary?region=US"))
                .header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "a252909fb0msh5b82dd41126eef3p14c9f1jsn0c4105f5d916")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
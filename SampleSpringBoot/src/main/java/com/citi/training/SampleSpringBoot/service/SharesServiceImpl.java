package com.citi.training.SampleSpringBoot.service;
import com.citi.training.SampleSpringBoot.entities.Shares;
import com.citi.training.SampleSpringBoot.repo.SharesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
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
    public Collection<Shares> getMyShares(){
        List<Shares> transactions  = sharesRepository.findAll();
        Collection<Shares> output = new ArrayList<Shares>();
        for(int i = 0; i < transactions.size(); i++){
            List<Shares> a = (List<Shares>) getShareBySymbol(transactions.get(i).getSymbol());
            if(a.size() == 1){output.add(transactions.get(i));}
            else{
                int volume = 0;
                for(int j = 0; j < a.size(); j++){
                   if(a.get(j).getTransaction_type().equals("buy")) {volume += a.get(j).getVolume();}
                   else{volume -= a.get(j).getVolume();}
                }
                //output.add(new Shares(a.get(i).getSymbol(),volume));
            }
        }
        return output;
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
    public String getCurrentStockInfor(String symbol) throws IOException {
        Stock stock = YahooFinance.get(symbol);

        BigDecimal price = stock.getQuote().getPrice();
        BigDecimal change = stock.getQuote().getChangeInPercent();
        BigDecimal peg = stock.getStats().getPeg();
        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

       // stock.print();

        return "Current Market Information for " + stock.getName() +" Price: " + price + ", Change: " + change + ", Peg: " + peg + ", Dividend: " + dividend ;
    }
}
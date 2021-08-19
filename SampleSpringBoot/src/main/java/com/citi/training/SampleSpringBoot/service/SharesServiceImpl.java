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
        share.setTransaction_date();
        share.setTransaction_price();
        sharesRepository.save(share);
    }
    @Override
    public void sellShares(String symbol) {
        Object[] toBeSell = sharesRepository.findBySymbol(symbol).toArray();
        if (toBeSell.length == 1) {
            sharesRepository.delete((Shares) toBeSell[0]);
        }
    }
//
//    @Override
//    public Double getBookValue() {
//        Double total = 0.0;
//        List<Shares> shares  = sharesRepository.findAll();
//        for(int i = 0 ; i < shares.size(); i++){
//            total += shares.get(i).getPurchasedPrice() * shares.get(i).getVolume();
//        }
//        return total;
//    }
//
//    @Override
//    public Double getTotlaProfit() {
//        Double total = 0.0;
//        List<Shares> shares  = sharesRepository.findAll();
//        for(int i = 0 ; i < shares.size(); i++){
//            total += shares.get(i).getCurrentPrice() * shares.get(i).getVolume() - shares.get(i).getPurchasedPrice() * shares.get(i).getVolume();
//        }
//        return total;
//    }
//
//    @Override
//    public Double getTotlaNetWorth(){
//        Double total = 0.0;
//        List<Shares> shares  = sharesRepository.findAll();
//        for(int i = 0 ; i < shares.size(); i++){
//            total += shares.get(i).getCurrentPrice() * shares.get(i).getVolume();
//        }
//        return total;
//    }
//
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
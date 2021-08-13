package com.citi.training.SampleSpringBoot.service;
import com.citi.training.SampleSpringBoot.entities.Shares;
import com.citi.training.SampleSpringBoot.repo.SharesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void addNewShare(Shares share) {
        sharesRepository.save(share);
    }
    @Override
    public void sellShares(String symbol) {
        Object[] toBeSell = sharesRepository.findBySymbol(symbol).toArray();
        if (toBeSell.length == 1) {
            sharesRepository.delete((Shares) toBeSell[0]);
        }
    }

    @Override
    public Double getTotlaProfit() {
        Double total = 0.0;
        List<Shares> shares  = sharesRepository.findAll();
        for(int i = 0 ; i < shares.size(); i++){
            total += shares.get(i).getCurrentPrice() * shares.get(i).getVolume() - shares.get(i).getPurchasedPrice() * shares.get(i).getVolume();
        }
        return total;
    }

    @Override
    public Double getTotlaNetWorth(){
        Double total = 0.0;
        List<Shares> shares  = sharesRepository.findAll();
        for(int i = 0 ; i < shares.size(); i++){
            total += shares.get(i).getCurrentPrice() * shares.get(i).getVolume();
        }
        return total;
    }
}
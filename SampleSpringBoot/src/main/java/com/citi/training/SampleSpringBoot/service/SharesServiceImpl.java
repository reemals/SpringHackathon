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
    public Collection<Shares> addNewShare(Shares share) {
        return null;
    }
    @Override
    public void SellShares(String symbol) {
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
package com.citi.training.SampleSpringBoot.service;
import com.citi.training.SampleSpringBoot.entities.Shares;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
@Service
public interface SharesService {
    Collection<Shares> getAllShares();
    Collection<Shares>  getShareBySymbol (String symbol);
    Collection<Shares> getMyShares();
//    Double getTotlaNetWorth();
    void  addNewShare(Shares share) throws IOException;
    void sellShares(String symbol);
//    Double getTotlaProfit();
//    Double getBookValue();
    String getCurrentStockInfor(String symbol) throws IOException;
}
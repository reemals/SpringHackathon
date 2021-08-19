package com.citi.training.SampleSpringBoot.rest;
import com.citi.training.SampleSpringBoot.entities.Shares;
import com.citi.training.SampleSpringBoot.service.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
@RestController
@RequestMapping("/Shares")
public class SharesController {
    @Autowired
    private SharesService sharesService;
    @GetMapping
    public Collection<Shares> getShares() {
        System.out.println(sharesService.getAllShares().toArray());
        return sharesService.getAllShares();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{symbol}")
    public Collection<Shares> getShareBySymbol (@PathVariable("symbol") String symbol) {
        return sharesService.getShareBySymbol(symbol);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/myShares")
    public Collection<Shares> getMyShares () {
        return sharesService.getMyShares();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/networth")
    public String getTotlaNetWorth() throws IOException {
        return "The total current Netwoth: " + String.format("%,.2f", sharesService.getTotalNetWorth());
    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/profit")
//    public String getTotlaProfit() {
//        return "Total Profit: " + String.format("%,.2f", sharesService.getTotlaProfit());
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/bookValue")
//    public String getBookValue() {
//        return "Total Book Value: " + String.format("%,.2f", sharesService.getBookValue());
//    }
//
    @RequestMapping(method = RequestMethod.POST, value = "sell")
    public void sellShareBySymbol (@RequestBody Shares sh) throws IOException {
        sharesService.sellShares(sh);
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buyShare(@RequestBody Shares sh) throws IOException {
        sharesService.addNewShare(sh);
    }

    @RequestMapping(method = RequestMethod.GET, value = "API/{symbol}")
    public String getCurrentStockInfor(@PathVariable("symbol") String symbol) throws IOException {
        return sharesService.getCurrentStockInfor(symbol);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/Total{symbol}")
    public String getTotalShares(@PathVariable("symbol") String symbol) throws IOException {
        return "Total " + symbol + " shares I own: " + sharesService.getTotalShares(symbol);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/SharesOwned")
    public String getMySharesName()  {
        return "shares owned " + sharesService.getMySharesName().toString();
    }
}
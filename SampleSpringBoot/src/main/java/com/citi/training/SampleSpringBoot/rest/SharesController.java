package com.citi.training.SampleSpringBoot.rest;
import com.citi.training.SampleSpringBoot.entities.Shares;
import com.citi.training.SampleSpringBoot.service.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(method = RequestMethod.DELETE, value = "/{symbol}")
    public void sellShareBySymbol (@PathVariable("symbol") String symbol) {
        sharesService.sellShares(symbol);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/networth")
//    public Collection<Shares> getTotalNetWorth () {
//        double totalNet = 0.0;
//        Collection<Shares> totalShares = sharesService.getAllShares();
//        totalShares.toArray();
//        return sharesService.getShareBySymbol(symbol);
//    }
}
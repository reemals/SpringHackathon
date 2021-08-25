package com.citi.training.SampleSpringBoot.rest;
import com.citi.training.SampleSpringBoot.entities.Shares;
import com.citi.training.SampleSpringBoot.service.SharesService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")

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

//    @RequestMapping(method = RequestMethod.GET, value = "/myShares")
//    public Collection<Shares> getMyShares () {
//        return sharesService.getMyShares();
//    }
    @RequestMapping(method = RequestMethod.GET, value = "/networth")
    public String getTotalNetWorth() throws IOException, JSONException {
        JSONObject outputJsonObj = new JSONObject();
        outputJsonObj.put("output", String.format("%,.2f", sharesService.getTotalNetWorth()));
        return outputJsonObj.toString();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, value = "profit/{id}")
    public String getTotalProfit(@PathVariable("id") int id) throws IOException, JSONException {
        JSONObject outputJsonObj = new JSONObject();
        outputJsonObj.put("output", String.format("%,.2f", sharesService.getTotalProfit(id)));
        return outputJsonObj.toString();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/bookValue/{id}")
    public String getBookValue(@PathVariable("id") int id) throws IOException, JSONException {
        JSONObject outputJsonObj = new JSONObject();
        outputJsonObj.put("output", String.format("%,.2f", sharesService.getBookValue( id)));
        return outputJsonObj.toString();
    }
    @RequestMapping(method = RequestMethod.POST, value = "sell")
    public ResponseEntity sellShareBySymbol (@RequestBody Shares sh) throws IOException {
        if(sharesService.getTotalShares(sh.getSymbol())-sh.getVolume() >= 0){
            sharesService.sellShares(sh);
            return new ResponseEntity<>(HttpStatus.ACCEPTED );
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }
    @RequestMapping( method = RequestMethod.POST, value = "/buy")
    public void buyShare(@RequestBody Shares sh) throws IOException {
        sharesService.addNewShare(sh);
    }
    @RequestMapping(method = RequestMethod.GET, value = "API/{symbol}")
    public String getCurrentStockInfor(@PathVariable("symbol") String symbol) throws IOException, JSONException {
        return sharesService.getCurrentStockInfor(symbol);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/total/{symbol}")
    public String getTotalShares(@PathVariable("symbol") String symbol) throws IOException, JSONException {
        JSONObject outputJsonObj = new JSONObject();
        outputJsonObj.put("output", sharesService.getTotalShares(symbol));
        return outputJsonObj.toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sharesOwned")
    public String getMySharesName() throws JSONException {
        JSONObject outputJsonObj = new JSONObject();
        outputJsonObj.put("output", sharesService.getMySharesName().toString());
        return outputJsonObj.toString();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/marketGainers")
    public String getMarketGainers() throws JSONException, IOException, InterruptedException {
        return sharesService.getMarketGainers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/marketLosers")
    public String getMarketLosers() throws JSONException, IOException, InterruptedException {
        return sharesService.getMarketLosers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/marketSummary")
    public String getMarketSummary() throws JSONException, IOException, InterruptedException {
        return sharesService.getMarketSummary();
    }
}
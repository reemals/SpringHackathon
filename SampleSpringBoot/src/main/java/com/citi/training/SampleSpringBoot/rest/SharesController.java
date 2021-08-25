package com.citi.training.SampleSpringBoot.rest;
import com.citi.training.SampleSpringBoot.entities.Shares;
import com.citi.training.SampleSpringBoot.service.SharesService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/")
public class SharesController {
    @Autowired
    private SharesService sharesService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public Collection<Shares> getShares() {
        System.out.println(sharesService.getAllShares().toArray());
        return sharesService.getAllShares();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, value = "/{symbol}")
    public Collection<Shares> getShareBySymbol (@PathVariable("symbol") String symbol) {
        return sharesService.getShareBySymbol(symbol);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/myShares")
//    public Collection<Shares> getMyShares () {
//        return sharesService.getMyShares();
//    }
    @CrossOrigin(origins = "http://localhost:4200")
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
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, value = "/bookValue/{id}")
    public String getBookValue(@PathVariable("id") int id) throws IOException, JSONException {
        JSONObject outputJsonObj = new JSONObject();
        outputJsonObj.put("output", String.format("%,.2f", sharesService.getBookValue( id)));
        return outputJsonObj.toString();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST, value = "sell")
    public void sellShareBySymbol (@RequestBody Shares sh) throws IOException {
        sharesService.sellShares(sh);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping( method = RequestMethod.POST, value = "/buy")
    public void buyShare(@RequestBody Shares sh) throws IOException {
        sharesService.addNewShare(sh);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, value = "API/{symbol}")
    public String getCurrentStockInfor(@PathVariable("symbol") String symbol) throws IOException, JSONException {
        return sharesService.getCurrentStockInfor(symbol);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, value = "/total/{symbol}")
    public String getTotalShares(@PathVariable("symbol") String symbol) throws IOException, JSONException {
        JSONObject outputJsonObj = new JSONObject();
        outputJsonObj.put("output", sharesService.getTotalShares(symbol));
        return outputJsonObj.toString();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, value = "/sharesOwned")
    public String getMySharesName() throws JSONException {
        JSONObject outputJsonObj = new JSONObject();
        outputJsonObj.put("output", sharesService.getMySharesName().toString());
        return outputJsonObj.toString();
    }
}
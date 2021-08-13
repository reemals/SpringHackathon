package com.citi.training.SampleSpringBoot.rest;

import com.citi.training.SampleSpringBoot.entities.Shares;
import com.citi.training.SampleSpringBoot.service.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/Shares")
public class SharesController {

    @Autowired
    private SharesService sharesService;

    @GetMapping
    public Collection<Shares> getShares() {
        return sharesService.getAllShares();
    }
}

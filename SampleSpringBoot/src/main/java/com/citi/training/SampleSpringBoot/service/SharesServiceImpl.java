package com.citi.training.SampleSpringBoot.service;

import com.citi.training.SampleSpringBoot.entities.Shares;
import com.citi.training.SampleSpringBoot.repo.SharesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public class SharesServiceImpl implements SharesService {

    @Autowired
    private SharesRepository sharesRepository;

    @Override
    public Collection<Shares> getAllShares() {
        return sharesRepository.findAll();
    }
}

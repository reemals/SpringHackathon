package com.citi.training.SampleSpringBoot.service;

import com.citi.training.SampleSpringBoot.entities.Shares;
import com.citi.training.SampleSpringBoot.repo.SharesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SharesServiceImpl implements SharesService {

    @Autowired
    private SharesRepository sharesRepository;

    @Override
    public Collection<Shares> getAllShares() {
        return sharesRepository.findAll();
    }

}

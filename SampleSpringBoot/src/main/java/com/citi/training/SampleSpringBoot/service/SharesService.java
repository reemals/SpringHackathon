package com.citi.training.SampleSpringBoot.service;

import com.citi.training.SampleSpringBoot.entities.Shares;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface SharesService {
    Collection<Shares> getAllShares();


}

package com.citi.training.SampleSpringBoot.repo;

import com.citi.training.SampleSpringBoot.entities.Shares;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SharesRepository extends JpaRepository<Shares, String > {
    Collection<Shares> findBySymbol(String name);
    Collection<Shares> findById(int id);

}

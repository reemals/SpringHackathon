package com.citi.training.SampleSpringBoot;

import com.citi.training.SampleSpringBoot.entities.Shares;
import com.citi.training.SampleSpringBoot.repo.SharesRepository;
import com.citi.training.SampleSpringBoot.rest.SharesController;
import com.citi.training.SampleSpringBoot.service.SharesService;
import com.citi.training.SampleSpringBoot.service.SharesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SampleSpringBootApplicationTests {

	private SharesServiceImpl sc;
	private SampleSpringBootApplication portfolio;
	private SharesRepository sharesRepository;

	@BeforeEach
	public void setup() {
		sc = new SharesServiceImpl();
		portfolio = new SampleSpringBootApplication();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void canGetShares() throws IOException {


		Collection<Shares> allShares = sharesRepository.findAll();

		System.out.println(allShares.toArray().length);

		assertTrue(allShares != null);

	}

	@Test
	public void canGetNetWorth() throws IOException {

		Double networth = sc.getTotalNetWorth();
		System.out.println(networth);
		assertTrue(networth == null);

	}

//	@Test
//	public void canGetStockInfo() throws IOException {
//
//		String stockInfo = sc.getCurrentStockInfor("TSLA");
//		assertTrue(stockInfo != null);
//
//	}

	@Test
	public void canAddStock() throws IOException {

		Shares share = new Shares();
		share.setId(1);
		share.setSymbol("FB");
		share.setVolume(10);
		share.setTransaction_price();
		share.setTransaction_date();
		share.setTransaction_type("buy");
		assertTrue(share.getVolume() == 10 && share.getTransaction_type().equals("buy"));

	}



}

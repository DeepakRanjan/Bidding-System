package com.bidding.system.Bidding.System.service;

import static org.testng.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bidding.system.Bidding.System.beans.GetRunningAuctionAPIResponse;
import com.bidding.system.Bidding.System.dao.BiddingSystemDao;

public class BiddingSystemServiceImplTest {

	@InjectMocks
	BiddingSystemServiceImpl biddingSystemServiceImpl;
	
	@Mock
	BiddingSystemDao biddingSystemDao;
	
	private MockMvc mvc;

	@BeforeMethod
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(biddingSystemServiceImpl).build();
	}

	@Test
	public void getRunningAuctionsSuccessfully() throws Exception {
		
		List<GetRunningAuctionAPIResponse> runningAuctions = new LinkedList<>();
		GetRunningAuctionAPIResponse runningAuction = new GetRunningAuctionAPIResponse();
		runningAuction.setHighestBidAmount(1000);
		runningAuction.setItemCode(100);
		runningAuction.setStepRate(500);
		runningAuctions.add(runningAuction);
		Mockito.when(biddingSystemDao.getRunningAuctions("RUNNING", 0, 1)).thenReturn(runningAuctions);
		List<GetRunningAuctionAPIResponse> result = biddingSystemServiceImpl.getRunningAuctions("RUNNING", 0, 1);
		assertEquals(result.size(), 1);
		assertEquals(result.get(0).getHighestBidAmount(), 1000);
		assertEquals(result.get(0).getStepRate(), 500);
	}
	
	@Test
	public void placeBidSuccessfully() throws Exception {
		
		Mockito.doNothing().when(biddingSystemDao).placeBid(100, 1000, "Deepak");
		biddingSystemDao.placeBid(100, 1000, "Deepak");
		Mockito.verify(biddingSystemDao, Mockito.times(1)).placeBid(100, 1000, "Deepak");
	}
}

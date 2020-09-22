package com.bidding.system.Bidding.System.dao;

import static org.testng.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bidding.system.Bidding.System.beans.Auction;
import com.bidding.system.Bidding.System.beans.Bid;
import com.bidding.system.Bidding.System.beans.GetRunningAuctionAPIResponse;
import com.bidding.system.Bidding.System.beans.Item;
import com.bidding.system.Bidding.System.enums.AuctionStatus;

public class BiddingSystemDaoImplTest {

	@InjectMocks
	BiddingSystemDaoImpl biddingSystemDaoImpl;
	
	private MockMvc mvc;

	@BeforeMethod
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(biddingSystemDaoImpl).build();
		BiddingSystemDaoImpl biddingSystemDaoImpl = new BiddingSystemDaoImpl();
		LinkedList<Auction> auctions = new LinkedList<Auction>();
		LinkedList<Bid> bidList1 = new LinkedList<Bid>();
		Auction auction1 = new Auction(AuctionStatus.RUNNING, 1000, new Item(100, "Godrej", "Brand new godrej"), bidList1);
		auctions.add(auction1);
		LinkedList<Bid> bidList2 = new LinkedList<Bid>();
		Auction auction2 = new Auction(AuctionStatus.RUNNING, 2000, new Item(101, "Sofa", "Brand new Sofa"), bidList2);
		auctions.add(auction2);
		biddingSystemDaoImpl.auctionBoard.addAll(auctions);
	}

	@Test
	public void getRunningAuctionsSuccessfully() throws Exception {

		List<GetRunningAuctionAPIResponse> response = biddingSystemDaoImpl.getRunningAuctions("RUNNING", 0, 2);
		assertEquals(response.size(), 2);
		assertEquals(response.get(0).getItemCode().toString(), "100");
		assertEquals(response.get(1).getItemCode().toString(), "101");
	}
}

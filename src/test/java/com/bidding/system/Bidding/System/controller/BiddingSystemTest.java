package com.bidding.system.Bidding.System.controller;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bidding.system.Bidding.System.beans.GetRunningAuctionAPIResponse;
import com.bidding.system.Bidding.System.service.BiddingSystemService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebAppConfiguration
public class BiddingSystemTest {

	@InjectMocks
	BiddingSystem biddingSystem;
	
	@Mock
	BiddingSystemService biddingSystemService;
	
	private MockMvc mvc;

	@BeforeMethod
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(biddingSystem).build();
	}

	@Test
	public void getRunningAuctionsSuccessfully() throws Exception {

		List<GetRunningAuctionAPIResponse> runningAuctions = new LinkedList<>();
		GetRunningAuctionAPIResponse runningAuction = new GetRunningAuctionAPIResponse();
		runningAuction.setHighestBidAmount(1000);
		runningAuction.setItemCode(100);
		runningAuction.setStepRate(500);
		runningAuctions.add(runningAuction);
		Mockito.when(biddingSystemService.getRunningAuctions("RUNNING", 0, 1)).thenReturn(runningAuctions);
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get("/auction").param("status", "RUNNING").param("from", "0").param("size", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		JsonObject obj = (JsonObject) new JsonParser().parse(mvcResult.getResponse().getContentAsString());
		assertEquals(true, obj.get("status").getAsBoolean());
		assertEquals(200, obj.get("code").getAsInt());
	}
	
	@Test
	public void placeBidsSuccessfully() throws Exception {

		Mockito.doNothing().when(biddingSystemService).placeBid(100, 1000, "Deepak");
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post("/auction/100/bid").param("bidAmount", "1000").param("userName", "Deepak"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		JsonObject obj = (JsonObject) new JsonParser().parse(mvcResult.getResponse().getContentAsString());
		assertEquals(true, obj.get("status").getAsBoolean());
		assertEquals(201, obj.get("code").getAsInt());
	}
}

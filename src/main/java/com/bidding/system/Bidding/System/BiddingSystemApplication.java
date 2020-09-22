package com.bidding.system.Bidding.System;

import java.util.LinkedList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bidding.system.Bidding.System.beans.Auction;
import com.bidding.system.Bidding.System.beans.Bid;
import com.bidding.system.Bidding.System.beans.Item;
import com.bidding.system.Bidding.System.dao.BiddingSystemDaoImpl;
import com.bidding.system.Bidding.System.enums.AuctionStatus;

@SpringBootApplication
public class BiddingSystemApplication {
	public static void main(String[] args) {
		BiddingSystemDaoImpl biddingSystemDaoImpl = new BiddingSystemDaoImpl();
		LinkedList<Auction> auctions = new LinkedList<Auction>();
		LinkedList<Bid> bidList1 = new LinkedList<Bid>();
		Auction auction1 = new Auction(AuctionStatus.RUNNING, 1000, new Item(100, "Godrej", "Brand new godrej"), bidList1);
		auctions.add(auction1);
		LinkedList<Bid> bidList2 = new LinkedList<Bid>();
		Auction auction2 = new Auction(AuctionStatus.RUNNING, 2000, new Item(101, "Sofa", "Brand new Sofa"), bidList2);
		auctions.add(auction2);
		biddingSystemDaoImpl.auctionBoard.addAll(auctions);
		SpringApplication.run(BiddingSystemApplication.class, args);
	}

}

package com.bidding.system.Bidding.System.beans;

import java.util.LinkedList;
import java.util.List;

import com.bidding.system.Bidding.System.enums.AuctionStatus;

public class Auction {

	private final AuctionStatus auctionStatus;
	
	private final Integer minBasePrice;

	private final Item item;

	private final LinkedList<Bid> bids;
	
	public Auction(AuctionStatus auctionStatus, Integer minBasePrice, Item item, LinkedList<Bid> bids) {
		this.auctionStatus = auctionStatus;
		this.minBasePrice = minBasePrice;
		this.item = item;
		this.bids = bids;
	}

	public AuctionStatus getAuctionStatus() {
		return auctionStatus;
	}

	public Item getItem() {
		return item;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public Integer getMinBasePrice() {
		return minBasePrice;
	}
}

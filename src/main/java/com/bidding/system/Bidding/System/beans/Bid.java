package com.bidding.system.Bidding.System.beans;

import com.bidding.system.Bidding.System.enums.BidStatus;

/**
 * The representation of an auction bid from a user
 */
public class Bid {
	private final User user;
	private final int amount;
	private final BidStatus bidStatus;

	public Bid(User user, int amount, BidStatus bidStatus) {
		this.user = user;
		this.amount = amount;
		this.bidStatus = bidStatus;
	}

	public User getUser() {
		return user;
	}

	public int getamount() {
		return amount;
	}

	public boolean isFromUser(User user) {
		return this.user.equals(user);
	}

	public int getAmount() {
		return amount;
	}

	public BidStatus getBidStatus() {
		return bidStatus;
	}

}
package com.bidding.system.Bidding.System.beans;

public class GetRunningAuctionAPIResponse {

	private Integer itemCode;
	
	private long highestBidAmount;
	
	private long stepRate;

	public Integer getItemCode() {
		return itemCode;
	}

	public void setItemCode(Integer itemCode) {
		this.itemCode = itemCode;
	}

	public long getHighestBidAmount() {
		return highestBidAmount;
	}

	public void setHighestBidAmount(long highestBidAmount) {
		this.highestBidAmount = highestBidAmount;
	}

	public long getStepRate() {
		return stepRate;
	}

	public void setStepRate(long stepRate) {
		this.stepRate = stepRate;
	}

	@Override
	public String toString() {
		return "GetRunningAuctionAPIResponse [itemCode=" + itemCode + ", highestBidAmount=" + highestBidAmount
				+ ", stepRate=" + stepRate + "]";
	}
}

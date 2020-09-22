package com.bidding.system.Bidding.System.service;

import java.util.List;
import com.bidding.system.Bidding.System.beans.GetRunningAuctionAPIResponse;
import com.bidding.system.Bidding.System.exceptions.InvalidBidException;


public interface BiddingSystemService {

	/**
	 * Method will return all running auctions details
	 * @author deepakranjan
	 * @param status is Auction status
	 * @param from and size is for pagination. From is the starting index of list
	 * and size is number of records to be fetched from starting ( from ) index.
	 */
	List<GetRunningAuctionAPIResponse> getRunningAuctions(String status, Integer from, Integer size);
	
	/**
	 * Method shows all live Auctions information as well as rejected bids for 
	 * particular item  
	 */
	void realTimeBroadcasting();

	/**
	 * Method helps to place bid for given item
	 * @param itemCode is code of item for which bid has to place
	 * @param bidAmount is the bidding amount
	 * @param userName is name of user who is placing the bid
	 * @throws InvalidBidException
	 */
	void placeBid(Integer itemCode, Integer bidAmount, String userName) throws InvalidBidException;
}

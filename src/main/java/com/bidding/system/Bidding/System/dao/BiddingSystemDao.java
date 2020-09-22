package com.bidding.system.Bidding.System.dao;

import java.util.List;

import com.bidding.system.Bidding.System.beans.Auction;
import com.bidding.system.Bidding.System.beans.GetRunningAuctionAPIResponse;
import com.bidding.system.Bidding.System.exceptions.InvalidBidException;

public interface BiddingSystemDao {

	/**
	 * Method will return all running auctions details
	 * @author deepakranjan
	 * @param status is Auction status
	 * @param from and size is for pagination. From is the starting index of list
	 * and size is number of records to be fetched from starting ( from ) index.
	 */
	List<GetRunningAuctionAPIResponse> getRunningAuctions(String status, Integer from, Integer size);
	
	/**
	 * Method helps to place bid for given item
	 * @param itemCode is code of item for which bid has to place
	 * @param bidAmount is the bidding amount
	 * @param userName is name of user who is placing the bid
	 * @throws InvalidBidException
	 */
	void placeBid(Integer itemCode, Integer bidAmount, String userName) throws InvalidBidException;
	
	/**
	 * method helps to check whether placed bid is valid or not
	 * @param auction is Auction details for which bid has to be placed.
	 * @param bidAmount is the bidding amount
	 * @param userName is name of user who is placing the bid
	 * @throws InvalidBidException
	 */
	void validateAndPlaceBid(Auction auction, Integer bidAmount, String userName) throws InvalidBidException;
	
	/**
	 * 
	 * Method returns all Auctions including completed Auction
	 */
	List<Auction> getAllAuctions();
}

package com.bidding.system.Bidding.System.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.bidding.system.Bidding.System.beans.Auction;
import com.bidding.system.Bidding.System.beans.Bid;
import com.bidding.system.Bidding.System.beans.GetRunningAuctionAPIResponse;
import com.bidding.system.Bidding.System.beans.Item;
import com.bidding.system.Bidding.System.beans.User;
import com.bidding.system.Bidding.System.enums.BidStatus;
import com.bidding.system.Bidding.System.exceptions.InvalidBidException;
import com.bidding.system.Bidding.System.messages.Messages;

@Repository
public class BiddingSystemDaoImpl implements BiddingSystemDao {

	public static LinkedList<Auction> auctionBoard = new LinkedList<Auction>();

	public static List<User> users = new ArrayList<User>();

	@Override
	public List<GetRunningAuctionAPIResponse> getRunningAuctions(String status, Integer from, Integer size) {

		List<GetRunningAuctionAPIResponse> runningAuctionApiResponse = new ArrayList<GetRunningAuctionAPIResponse>();
		List<Auction> runningAuctions = new LinkedList<Auction>();
		List<Auction> filteredRunningAuctions = new LinkedList<Auction>();
		if (!CollectionUtils.isEmpty(auctionBoard)) {
			runningAuctions = auctionBoard.stream()
					.filter(index -> (status.equalsIgnoreCase(index.getAuctionStatus().toString())))
					.collect(Collectors.toList());
			if (!CollectionUtils.isEmpty(runningAuctions)) {
				filteredRunningAuctions = runningAuctions.subList(from,
						Math.min((from + size), runningAuctions.size()));
			}
			filteredRunningAuctions.stream().forEach(index -> {
				GetRunningAuctionAPIResponse runningAuction = new GetRunningAuctionAPIResponse();
				List<Bid> bids = new LinkedList<Bid>();
				bids = index.getBids();
				Item item = index.getItem();
				int bidsCount = bids.size();
				if (bidsCount > 0)
					runningAuction.setHighestBidAmount(bids.get(bidsCount - 1).getamount());
				runningAuction.setItemCode(item.getCode());
				if (bidsCount == 1)
					runningAuction.setStepRate(bids.get(bidsCount - 1).getamount() - index.getMinBasePrice());
				else if (bidsCount > 1)
					runningAuction
							.setStepRate(bids.get(bidsCount - 1).getamount() - bids.get(bidsCount - 2).getamount());
				runningAuctionApiResponse.add(runningAuction);

			});
		}
		return runningAuctionApiResponse;
	}

	@Override
	public void placeBid(Integer itemCode, Integer bidAmount, String userName) throws InvalidBidException {

		Auction auction = null;
		if (!CollectionUtils.isEmpty(auctionBoard))
			auction = auctionBoard.stream().filter(index -> (itemCode.equals(index.getItem().getCode()))).findAny()
					.orElse(null);
		if (Objects.nonNull(auction))
			validateAndPlaceBid(auction, bidAmount, userName);
		else
			throw new InvalidBidException(Messages.AUCTION_NOT_FOUND, 404);
	}

	@Override
	public synchronized void validateAndPlaceBid(Auction auction, Integer bidAmount, String userName)
			throws InvalidBidException {

		List<Bid> bids = auction.getBids();
		Date date = new Date();
		User user = null;

		List<Bid> acceptedBids = bids.stream()
				.filter(index -> (BidStatus.ACCEPTED.name().equalsIgnoreCase(index.getBidStatus().name())))
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(users))
			user = users.stream().filter(index -> (userName.equalsIgnoreCase(index.getName()))).findAny().orElse(null);
		if (Objects.isNull(user))
			user = new User("user" + date.getTime(), userName);

		if (isBidValid(acceptedBids, bidAmount, auction))
			bids.add(0, new Bid(user, bidAmount, BidStatus.ACCEPTED));
		else {
			bids.add(new Bid(user, bidAmount, BidStatus.REJECTED));
			throw new InvalidBidException(Messages.BID_REJECTED + auction.getItem().getCode(), 406);
		}

	}

	@Override
	public List<Auction> getAllAuctions() {

		return auctionBoard;
	}

	private boolean isBidValid(List<Bid> acceptedBids, Integer bidAmount, Auction auction) {

		int bidsCount = acceptedBids.size();
		int stepRate = calStepRate(auction, acceptedBids);
		if ((bidAmount < auction.getMinBasePrice())
				|| (bidsCount > 0 && bidAmount < acceptedBids.get(0).getAmount() + stepRate))
			return false;
		return true;
	}

	private int calStepRate(Auction auction, List<Bid> acceptedBids) {

		int stepRate = 0;
		int size = acceptedBids.size();
		if (size == 1)
			stepRate = acceptedBids.get(0).getamount() - auction.getMinBasePrice();
		else if (size > 1)
			stepRate = acceptedBids.get(0).getamount() - acceptedBids.get(1).getamount();
		return stepRate;
	}
}

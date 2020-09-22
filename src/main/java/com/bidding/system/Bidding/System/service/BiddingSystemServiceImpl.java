package com.bidding.system.Bidding.System.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bidding.system.Bidding.System.beans.Auction;
import com.bidding.system.Bidding.System.beans.Bid;
import com.bidding.system.Bidding.System.beans.GetRunningAuctionAPIResponse;
import com.bidding.system.Bidding.System.dao.BiddingSystemDao;
import com.bidding.system.Bidding.System.enums.BidStatus;
import com.bidding.system.Bidding.System.exceptions.InvalidBidException;

@Service
public class BiddingSystemServiceImpl implements BiddingSystemService {

	@Autowired
	BiddingSystemDao biddingSystemDao;

	@Override
	public List<GetRunningAuctionAPIResponse> getRunningAuctions(String status, Integer from, Integer size) {

		return biddingSystemDao.getRunningAuctions(status, from, size);
	}

	@Override
	public void placeBid(Integer itemCode, Integer bidAmount, String userName) throws InvalidBidException {

		biddingSystemDao.placeBid(itemCode, bidAmount, userName);
	}

	@Override
	public void realTimeBroadcasting() {

		List<Auction> auctions = biddingSystemDao.getAllAuctions();
		if (!CollectionUtils.isEmpty(auctions)) {
			System.out.println("===  Real Time Auction Board ====");
			System.out.println();
			auctions.stream().forEach(auction -> {
				List<Bid> bids = auction.getBids();
				if (!CollectionUtils.isEmpty(bids)) {
					System.out.println("=== Item Code " + auction.getItem().getCode() + " ====");
					System.out.println();
					List<Bid> rejectedBids = new ArrayList<Bid>();
					bids.stream().forEach(bid -> {
						if (BidStatus.ACCEPTED.name().equalsIgnoreCase(bid.getBidStatus().name()))
							System.out.println("User Name : " + bid.getUser().getName() + "     Bidding Amount : "
									+ bid.getamount());
						else
							rejectedBids.add(bid);
					});
					if (!CollectionUtils.isEmpty(rejectedBids)) {
						System.out.println();
						System.out.println(" *** Rejected Bids ****");
						rejectedBids.stream().forEach(bid -> {
							System.out.println("User Name : " + bid.getUser().getName() + "     Bidding Amount : "
									+ bid.getamount());
						});
					}
					System.out.println();
				}
			});
		}
	}
}

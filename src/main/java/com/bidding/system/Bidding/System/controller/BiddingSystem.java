package com.bidding.system.Bidding.System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bidding.system.Bidding.System.Utiility.JsonResponseWrapper;
import com.bidding.system.Bidding.System.beans.JsonResponse;
import com.bidding.system.Bidding.System.exceptions.InvalidBidException;
import com.bidding.system.Bidding.System.messages.Messages;
import com.bidding.system.Bidding.System.service.BiddingSystemService;

@RestController
@RequestMapping("/auction")
public class BiddingSystem {

	@Autowired
	BiddingSystemService biddingSystemService;

	@GetMapping()
	public JsonResponse getRunningAuctions(@RequestParam(value = "status", defaultValue = "RUNNING") String status,
			@RequestParam(value = "from", defaultValue = "0") Integer from,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			jsonResponse = JsonResponseWrapper.getSuccessResponse(
					biddingSystemService.getRunningAuctions(status, from, size),
					Messages.FETCHED_RUNNING_BIDS_SUCCESSFULLY, 200);

		} catch (Exception e) {
			jsonResponse = JsonResponseWrapper.getFailureResponse(null, Messages.FETCHED_RUNNING_BIDS_EXCEPTION, 400);
		}
		return jsonResponse;
	}

	@PostMapping("/{itemCode}/bid")
	public JsonResponse placeBid(@RequestParam(value = "bidAmount") Integer bidAmount,
			@RequestParam(value = "userName") String userName,
			@PathVariable(name = "itemCode") final Integer itemCode) {
		JsonResponse jsonResponse = new JsonResponse();
		try {
			biddingSystemService.placeBid(itemCode, bidAmount, userName);
			jsonResponse = JsonResponseWrapper.getSuccessResponse(null, Messages.BID_ACCEPTED, 201);
		} catch (InvalidBidException e) {
			jsonResponse = JsonResponseWrapper.getFailureResponse(null, e.getMessage(), e.getCode());
		}
		biddingSystemService.realTimeBroadcasting();
		return jsonResponse;
	}

}

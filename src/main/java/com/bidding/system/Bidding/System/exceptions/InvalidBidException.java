package com.bidding.system.Bidding.System.exceptions;

/**
 * A Generic {@link Exception} thrown when trying to make an invalid bid. Could
 * be for multiple reasons: The bid is too low, the item is not in the auction
 * anymore, etc.. The reason should be explained in the message or this
 * Exception could be subclassed by finer grain Exceptions.
 */
public class InvalidBidException extends Exception {

	private static final long serialVersionUID = 1L;

	private Integer code;

	public InvalidBidException(String message, Integer code) {
		super(message);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
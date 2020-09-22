package com.bidding.system.Bidding.System.beans;

import java.util.Objects;

/**
 * The representation of a user of the auction
 */
public class User {
	private final String id;
	private final String name;

	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}

package com.bidding.system.Bidding.System.beans;

import java.util.Objects;

/**
 * The representation of an item of the auction
 */
public class Item {
	private final Integer code;
	private final String name;
	private final String description;

	public Item(Integer code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return String.format("{ code: %s, name: %s, description: %s }", code, name, description);
	}

	@Override
	public boolean equals(Object o) {
		boolean equals = false;
		if (o == this) {
			equals = true;
		} else if (o instanceof Item) {
			Item item = (Item) o;
			equals = Objects.equals(code, item.code) && Objects.equals(name, item.name)
					&& Objects.equals(description, item.description);
		}
		return equals;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, name, description);
	}
}
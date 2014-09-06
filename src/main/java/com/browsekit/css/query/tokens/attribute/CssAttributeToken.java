package com.browsekit.css.query.tokens.attribute;

public interface CssAttributeToken {

	// public String toString(); ???
	
	public Boolean matches(String key, String value);

}

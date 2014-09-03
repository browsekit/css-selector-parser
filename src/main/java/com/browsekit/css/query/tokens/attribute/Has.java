package com.browsekit.css.query.tokens.attribute;

public class Has implements CssAttributeToken {

	public String key;

	public Has(String key) {
		this.key = key;
	}

	public String toString(){
		return "["+this.key+"]";
	}

	@Override
	public Boolean matches(String key, String value) {
		// ignore `value`
		return matches(key);
	}

	public Boolean matches(String key){
		if(null == this.key){
			return false;
		}

		if(!this.key.equals(key)){
			return false;
		}

		return true;
	}
}
package com.browsekit.css.query.tokens.attribute;

public class BeginsWith implements CssAttributeToken {

	public String key;

	public String value;

	public BeginsWith(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String toString(){
		return "["+this.key+"^=\""+this.value+"\"]";
	}

	@Override
	public Boolean matches(String key, String value) {
		if(null == this.key || null == this.value){
			return false;
		}

		if(!this.key.equals(key)){
			return false;
		}
		if(this.value.startsWith(value)){
			return false;
		}

		return true;
	}

}
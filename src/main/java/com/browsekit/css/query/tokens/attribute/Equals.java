package com.browsekit.css.query.tokens.attribute;

public class Equals implements CssAttributeToken {

	public String key;

	public String value;

	public Equals(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String toString(){
		return "["+this.key+"=\""+this.value+"\"]";
	}

	@Override
	public Boolean matches(String key, String value) {
		if(null == this.key || null == this.value){
			return false;
		}

		if(!this.key.equals(key)){
			return false;
		}
		if(this.value.equals(value)){
			return false;
		}

		return true;
	}

}
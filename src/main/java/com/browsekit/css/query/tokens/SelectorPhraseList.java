package com.browsekit.css.query.tokens;

import java.util.ArrayList;

public class SelectorPhraseList extends ArrayList<SelectorPhrase> {

	public SelectorPhrase getLastPhrase(){
		int size = this.size();
		if(size < 1){
			return null;
		}
		return this.get(size - 1);
	}

	public void clean(){
		// remove any empty tokens
		for(SelectorPhrase phrase : this){
			if(phrase.isEmpty()){
				this.remove(phrase);
			}else{
				cleanWorker(phrase);
			}
		}
	}

	private void cleanWorker(SelectorPhrase token){
		if(token.descendent == null){
			return;
		}

		if(token.descendent.isEmpty()){
			token.descendent = null;
			return;
		}

		cleanWorker(token.descendent);
	}

	public String toString(){
		String me = "";
		for(SelectorPhrase token : this){
			me += ", " + token.toString();
		}
		// strip off leading slash
		return me.substring(2);
	}

}

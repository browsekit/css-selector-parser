package com.browsekit.css.query.tokens.pseudo;

import com.browsekit.css.query.tokens.SelectorPhraseList;

public class Matches implements CssPseudoToken {

	public SelectorPhraseList cssParams;

	public Matches(SelectorPhraseList tokenList) {
		cssParams = tokenList;
	}

	public String toString(){
		return ":matches(" + this.cssParams.toString() + ")";
	}

}

package com.browsekit.css.query.tokens.pseudo;

import com.browsekit.css.query.tokens.SelectorPhraseList;

public class Not implements CssPseudoToken {

	public SelectorPhraseList cssParams;

	public Not(SelectorPhraseList tokenList) {
		cssParams = tokenList;
	}

	public String toString(){
		return ":not(" + this.cssParams.toString() + ")";
	}

}

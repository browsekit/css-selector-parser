package com.browsekit.css.query.tokens.pseudo;

import com.browsekit.css.query.tokens.SelectorPhraseList;

public class Has implements CssPseudoToken {

	public SelectorPhraseList cssParams;

	public Has(SelectorPhraseList tokenList) {
		cssParams = tokenList;
	}

	public String toString(){
		return ":has(" + this.cssParams.toString() + ")";
	}

}

package com.browsekit.css.query.grammar;

import com.browsekit.css.query.CssQueryUnknownGrammarException;
import com.browsekit.css.query.CssSelectorTokenizer;
import com.browsekit.css.query.tokens.SelectorPhraseList;

public abstract class Grammar implements Comparable<Grammar>{
	
	public static int SPECIFICITY_SEPARATOR = 1; // per http://css-tricks.com/specifics-on-css-specificity/ :
	public static int SPECIFICITY_1_0_0     = 2; // id
	public static int SPECIFICITY_0_1_0     = 3; // class, psuedo-class, attr
	public static int SPECIFICITY_0_0_1     = 4; // element
	
	public final int specificity;
	
	// http://stackoverflow.com/a/3453795
	public Grammar(int specificity){
		this.specificity = specificity;
	}
	
	public abstract String match(String selector, SelectorPhraseList rootTokenList, CssSelectorTokenizer cssSelectorTokenizer) throws CssQueryUnknownGrammarException;
	
	public int compareTo(Grammar other){
		int specificityDifference = this.specificity - other.specificity;
		
		if(specificityDifference != 0){
			return specificityDifference;
		}
		
		//TODO: sort by parsing order
		return 0;
	}

}

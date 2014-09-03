package com.browsekit.css.query.grammar;

import com.browsekit.css.query.CssQueryUnknownGrammarException;
import com.browsekit.css.query.tokens.SelectorPhraseList;

public interface Part {

	public abstract String match(String selector, SelectorPhraseList rootTokenList) throws CssQueryUnknownGrammarException;

}

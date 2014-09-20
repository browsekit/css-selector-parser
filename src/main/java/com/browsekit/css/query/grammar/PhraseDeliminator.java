package com.browsekit.css.query.grammar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.browsekit.css.query.CssSelectorTokenizer;
import com.browsekit.css.query.tokens.SelectorPhrase;
import com.browsekit.css.query.tokens.SelectorPhraseList;

public class PhraseDeliminator extends Grammar {

	public PhraseDeliminator() {
		super(Grammar.SPECIFICITY_SEPARATOR);
	}

	public static Pattern pattern = Pattern.compile("^\\s*,\\s*", Pattern.CASE_INSENSITIVE);

	public String match(String selector, SelectorPhraseList tokenList, CssSelectorTokenizer cssSelectorTokenizer) {
		Matcher matcher = pattern.matcher(selector);

		if( ! matcher.find() ){
			return null;
		}

		SelectorPhrase newPhrase = new SelectorPhrase();
		tokenList.add(newPhrase);

		return selector.substring(matcher.end());
	}

}

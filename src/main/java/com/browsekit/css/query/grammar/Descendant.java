package com.browsekit.css.query.grammar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.browsekit.css.query.tokens.SelectorPhraseList;

public class Descendant implements Part {

	public static Pattern pattern = Pattern.compile("^\\s+");

	public String match(String selector, SelectorPhraseList tokenList) {
		Matcher matcher = pattern.matcher(selector);

		if( ! matcher.find() ){
			return null;
		}

		tokenList.getLastPhrase().getLastDescendant().createDescendant();

		return selector.substring(matcher.end());
	}

}

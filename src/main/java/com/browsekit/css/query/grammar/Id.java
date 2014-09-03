package com.browsekit.css.query.grammar;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.browsekit.css.query.tokens.SelectorPhrase;
import com.browsekit.css.query.tokens.SelectorPhraseList;

public class Id implements Part {

	public static Pattern pattern = Pattern.compile("^\\#([a-z0-9\\-]+)", Pattern.CASE_INSENSITIVE);

	public String match(String selector, SelectorPhraseList tokenList) {
		Matcher matcher = pattern.matcher(selector);

		if( ! matcher.find() ){
			return null;
		}

		SelectorPhrase lastToken = tokenList.getLastPhrase().getLastDescendant();
		lastToken.id = matcher.group(1);

		return selector.substring(matcher.end());
	}

}

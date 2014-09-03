package com.browsekit.css.query.grammar;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.browsekit.css.query.tokens.SelectorPhrase;
import com.browsekit.css.query.tokens.SelectorPhraseList;

public class PhraseDeliminator implements Part {

	public static Pattern pattern = Pattern.compile("^\\s*,\\s*", Pattern.CASE_INSENSITIVE);

	public String match(String selector, SelectorPhraseList tokenList) {
		Matcher matcher = pattern.matcher(selector);

		if( ! matcher.find() ){
			return null;
		}

		SelectorPhrase newPhrase = new SelectorPhrase();
		tokenList.add(newPhrase);

		return selector.substring(matcher.end());
	}

}

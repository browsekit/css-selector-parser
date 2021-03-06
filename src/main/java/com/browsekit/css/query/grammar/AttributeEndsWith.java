package com.browsekit.css.query.grammar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.browsekit.css.query.tokens.SelectorPhrase;
import com.browsekit.css.query.tokens.SelectorPhraseList;
import com.browsekit.css.query.tokens.attribute.CssAttributeToken;
import com.browsekit.css.query.tokens.attribute.EndsWith;

public class AttributeEndsWith implements Part {

	public static Pattern pattern = Pattern.compile("^\\[([a-z0-9\\-]+)\\$=\"(.+?)\"]", Pattern.CASE_INSENSITIVE);

	public String match(String selector, SelectorPhraseList tokenList) {
		Matcher matcher = pattern.matcher(selector);

		if( ! matcher.find() ){
			return null;
		}

		CssAttributeToken attribute = new EndsWith(matcher.group(1), matcher.group(2));
		SelectorPhrase lastToken = tokenList.getLastPhrase().getLastDescendant();
		lastToken.attributes.add(attribute);

		return selector.substring(matcher.end());
	}

}

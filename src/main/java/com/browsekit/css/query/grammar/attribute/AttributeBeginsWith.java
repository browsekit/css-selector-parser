package com.browsekit.css.query.grammar.attribute;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.browsekit.css.query.CssSelectorTokenizer;
import com.browsekit.css.query.grammar.Grammar;
import com.browsekit.css.query.tokens.SelectorPhrase;
import com.browsekit.css.query.tokens.SelectorPhraseList;
import com.browsekit.css.query.tokens.attribute.BeginsWith;
import com.browsekit.css.query.tokens.attribute.CssAttributeToken;

public class AttributeBeginsWith extends Grammar {

	public AttributeBeginsWith() {
		super(Grammar.SPECIFICITY_0_1_0);
	}

	public static Pattern pattern = Pattern.compile("^\\[([a-z0-9\\-]+)\\^=\"(.+?)\"]", Pattern.CASE_INSENSITIVE);

	public String match(String selector, SelectorPhraseList tokenList, CssSelectorTokenizer cssSelectorTokenizer) {
		Matcher matcher = pattern.matcher(selector);

		if( ! matcher.find() ){
			return null;
		}

		CssAttributeToken attribute = new BeginsWith(matcher.group(1), matcher.group(2));
		SelectorPhrase lastToken = tokenList.getLastPhrase().getLastDescendant();
		lastToken.attributes.add(attribute);

		return selector.substring(matcher.end());
	}

}

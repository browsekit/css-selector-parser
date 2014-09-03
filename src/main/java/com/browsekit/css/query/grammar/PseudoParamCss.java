package com.browsekit.css.query.grammar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.browsekit.css.query.CssSelectorTokenizer;
import com.browsekit.css.query.CssQueryUnknownGrammarException;
import com.browsekit.css.query.tokens.SelectorPhrase;
import com.browsekit.css.query.tokens.SelectorPhraseList;
import com.browsekit.css.query.tokens.pseudo.*;

public class PseudoParamCss implements Part {

	public static Pattern pattern = Pattern.compile("^\\:(not|matches|has)\\((.+)\\)", Pattern.CASE_INSENSITIVE);

	public static final String TYPE_NOT = "not";
	public static final String TYPE_MATCHES = "matches";
	public static final String TYPE_HAS = "has";

	public String match(String selector, SelectorPhraseList tokenList) throws CssQueryUnknownGrammarException {
		Matcher matcher = pattern.matcher(selector);

		if( ! matcher.find() ){
			return null;
		}

		CssPseudoToken pseudoToken = null;
		SelectorPhraseList cssParams = CssSelectorTokenizer.parse(matcher.group(2));

		// could use a switch statement in Java7, but is it the right way to do it?
		String pseudoParamType = matcher.group(1);
		if(TYPE_NOT.equals(pseudoParamType)){
			pseudoToken = new Not(cssParams);
		} else if (TYPE_MATCHES.equals(pseudoParamType)) {
			pseudoToken = new Matches(cssParams);
		} else if (TYPE_HAS.equals(pseudoParamType)){
			pseudoToken = new Has(cssParams);
		}

		// TODO or throw error?? giving null back will let another grammar try
		// though this grammar shouldn't have matched at all...
		if(null == pseudoToken){
			return null;
		}

		SelectorPhrase lastToken = tokenList.getLastPhrase().getLastDescendant();
		lastToken.pseudos.add(pseudoToken);

		return selector.substring(matcher.end());
	}

}

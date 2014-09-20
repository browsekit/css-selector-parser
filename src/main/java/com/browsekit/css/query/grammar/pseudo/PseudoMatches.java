package com.browsekit.css.query.grammar.pseudo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.browsekit.css.query.CssSelectorTokenizer;
import com.browsekit.css.query.CssQueryUnknownGrammarException;
import com.browsekit.css.query.grammar.Grammar;
import com.browsekit.css.query.tokens.SelectorPhrase;
import com.browsekit.css.query.tokens.SelectorPhraseList;
import com.browsekit.css.query.tokens.pseudo.CssPseudoToken;
import com.browsekit.css.query.tokens.pseudo.Matches;

public class PseudoMatches extends Grammar {

	public PseudoMatches() {
		super(Grammar.SPECIFICITY_0_0_1);
	}

	public static Pattern pattern = Pattern.compile("^\\:matches\\((.+)\\)", Pattern.CASE_INSENSITIVE);

	public String match(String selector, SelectorPhraseList tokenList, CssSelectorTokenizer cssSelectorTokenizer) throws CssQueryUnknownGrammarException {
		Matcher matcher = pattern.matcher(selector);

		if( ! matcher.find() ){
			return null;
		}

		SelectorPhraseList cssParams = cssSelectorTokenizer.parse(matcher.group(1));
		
		CssPseudoToken pseudoToken = new Matches(cssParams);

		SelectorPhrase lastToken = tokenList.getLastPhrase().getLastDescendant();
		lastToken.pseudos.add(pseudoToken);

		return selector.substring(matcher.end());
	}

}

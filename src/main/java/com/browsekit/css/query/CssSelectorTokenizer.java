package com.browsekit.css.query;

import com.browsekit.css.query.grammar.*;
import com.browsekit.css.query.grammar.attribute.*;
import com.browsekit.css.query.grammar.pseudo.*;
import com.browsekit.css.query.tokens.SelectorPhrase;
import com.browsekit.css.query.tokens.SelectorPhraseList;

public class CssSelectorTokenizer {
	
	public GrammarList parts = new GrammarList();
	
	public void addDefaultGrammar() {
		
		parts.addAll(
			new PhraseDeliminator(),
			//TODO add(new DirectDescendant()); // /^\s*>\s*/
			new Descendant(),

			new Id(),

			new ClassName(),
			new AttributeEquals(),
			new AttributeBeginsWith(),
			new AttributeEndsWith(),
			new AttributeContains(),
			new AttributeHas(),

			new Element(),
			new PseudoHas(),
			new PseudoNot(),
			new PseudoMatches()
			//TODO add(new PseudoParam()); // /^\:([a-z0-9\-]+)\((.+?)\)/i
			//TODO add(new Pseudo()); // /^\:([a-z0-9\-]+)/i
		);
		
	}

	public SelectorPhraseList parse(String selector) throws CssQueryUnknownGrammarException {
		//TODO: disable adding more grammar while parsing?
		
		// setup with initial token
		SelectorPhraseList tokens = new SelectorPhraseList();
		SelectorPhrase currentToken = new SelectorPhrase();
		String selectorRemaining;

		tokens.add(currentToken);

		// parse
		int lastLength = selector.length();
		int currentLength;
		do{
			// loop through known phrases to parse the string and add tokens
			for(Grammar part : parts){
				selectorRemaining = part.match(selector, tokens, this);
				// TODO: rather than building new strings all the time, maybe just return integer of where to start looking? (cursor)
				if(selectorRemaining != null){
					selector = selectorRemaining;
					break;
				}
			}
			currentLength = selector.length();
			if(lastLength == currentLength){
				// no known phrases were able to match the next section, unknown grammar
				throw new CssQueryUnknownGrammarException(selector);
			}
			lastLength = currentLength;
		} while(currentLength > 0);

		// remove any erroneous blank tokens (deep)
		tokens.clean();
		return tokens;
	}


}

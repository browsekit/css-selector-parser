package com.browsekit.css.query.grammar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GrammarList extends ArrayList<Grammar> {

	@Override
	public boolean add(Grammar grammar){
		if(!isAddableGrammar(grammar)){
			return false;
		}
		
		if(!super.add(grammar)){
			return false;
		}
		
		Collections.sort(this);
		
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends Grammar>grammarCollection){
		Grammar[] grammarArray = null;
		grammarCollection.toArray(grammarArray);
		
		return addAll(grammarArray);
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends Grammar>grammarCollection){
		// we sort anyway, so ignore specified index 
		return addAll(grammarCollection);
	}
	
	public boolean addAll(Grammar...grammars){
		boolean canAddAll = true;
		for(Grammar grammar : grammars){
			canAddAll &= isAddableGrammar(grammar);
		}
		if(!canAddAll){
			return false;
		}
		
		for(Grammar grammar : grammars){
			super.add(grammar);
		}
		
		Collections.sort(this);
		
		return true;
	}
	
	private boolean isAddableGrammar(Grammar grammar){
		if(grammar == null){
			return false;
		}
		
		if(
			grammar.specificity < Grammar.SPECIFICITY_SEPARATOR || 
			grammar.specificity > Grammar.SPECIFICITY_0_0_1
		){
			return false;
		}
		
		return true;
	}
}

package com.browsekit.css.query.tokens;

import java.util.ArrayList;
import java.util.List;

import com.browsekit.css.query.tokens.attribute.CssAttributeToken;
import com.browsekit.css.query.tokens.pseudo.CssPseudoToken;

public class SelectorPhrase {

	public String element;

	public String id;

	public List<String> classes = new ArrayList<String>();

	public List<CssPseudoToken> pseudos = new ArrayList<CssPseudoToken>();

	public List<CssAttributeToken> attributes = new ArrayList<CssAttributeToken>();

	public SelectorPhrase descendent;

	public Boolean isEmpty(){
		if(this.element != null && this.element.length() > 0){
			return false;
		}

		if(this.id != null && this.id.length() > 0){
			return false;
		}

		if(this.classes.size() > 0){
			return false;
		}

		if(this.pseudos.size() > 0){
			return false;
		}

		if(this.attributes.size() > 0){
			return false;
		}

		// add other field checks here

		return true;
	}

	public String toString(){
		String me = "";

		if(this.element != null){
			me += this.element;
		}

		if(this.id != null){
			me += "#" + this.id;
		}

		if(this.classes.size() > 0){
			for(String className : this.classes){
				me += "." + className;
			}
		}

		if(this.attributes.size() > 0){
			for(CssAttributeToken attr : this.attributes){
				me += attr.toString();
			}
		}

		if(this.pseudos.size() > 0){
			for(CssPseudoToken pseudo : this.pseudos){
				me += pseudo.toString();
			}
		}

		if(this.descendent != null){
			me += " " + this.descendent.toString();
		}

		return me;
	}

	public SelectorPhrase createDescendant(){
		if(this.descendent != null){
			return this.descendent;
		}
		return this.descendent = new SelectorPhrase();
	}

	public SelectorPhrase getLastDescendant(){
		if(this.descendent == null){
			return this;
		}

		return this.descendent.getLastDescendant();
	}

}



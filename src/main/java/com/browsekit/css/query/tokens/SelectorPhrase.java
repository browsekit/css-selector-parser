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
	
	// if you add any properties, be sure to update methods below (hashCode, isEmpty, toString)

	public int hashCode(){
		int hashCode = 0;
		
		if(this.element != null){
			hashCode += this.element.hashCode();
		}

		if(this.id != null){
			hashCode += this.id.hashCode();
		}

		if(this.classes.size() > 0){
			hashCode += this.classes.hashCode();
		}

		if(this.pseudos.size() > 0){
			hashCode += this.pseudos.hashCode();
		}

		if(this.attributes.size() > 0){
			hashCode += this.attributes.hashCode();
		}
		
		return hashCode;
	}
	
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

		return true;
	}

	public String toString(){
		String me = "";

		if(this.element != null){
			me += this.element;
		}

		if(this.id != null && this.id.length() > 0){
			me += "#" + this.id;
		}

		if(this.classes.size() > 0){
			for(String className : this.classes){
				if(className != null && className.length() > 0){
					me += "." + className;
				}
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
	
	public boolean equals(Object o){
		if(null == o){
			return false;
		}
		
		if(!(o instanceof SelectorPhrase)){
			return false;
		}
		
		// inspecting individual properties would be duplication of logic
		return this.toString().equals(o.toString());
	}

	public SelectorPhrase createDescendant(){
		if(this.descendent != null){
			return this.descendent;
		}
		return this.descendent = new SelectorPhrase();
	}

	public boolean isLastDescendant(){
		return this.descendent == null;
	}
	
	public SelectorPhrase getLastDescendant(){
		if(this.isLastDescendant()){
			return this;
		}

		return this.descendent.getLastDescendant();
	}

}



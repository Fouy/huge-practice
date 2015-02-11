package com.way361.mongodb.core.query;

/**
 * A Term defines one or multiple words Type.WORD or phrases Type.PHRASE to be
 * used in the context of full text search.
 * 
 * @author xuefeihu
 *
 */
public class Term {
	
	public enum Type {
		WORD, PHRASE;
	}
	
	private final Type type;
	private final String raw;
	private boolean negated;
	
	/**
	 * Creates a new {@link Term} of {@link Type#WORD}.
	 * 
	 * @param raw
	 */
	public Term(String raw) {
		this(raw, Type.WORD);
	}
	
	/**
	 * Creates a new {@link Term} of given {@link Type}.
	 * 
	 * @param raw
	 * @param type defaulted to {@link Type#WORD} if {@literal null}.
	 */
	public Term(String raw, Type type) {
		this.raw = raw;
		this.type = type == null ? Type.WORD : type;
	}
	
	/**
	 * Negates the term.
	 * 
	 * @return
	 */
	public Term negate() {
		this.negated = true;
		return this;
	}
	
	/**
	 * @return return true if term is negated.
	 */
	public boolean isNegated() {
		return negated;
	}
	
	/**
	 * @return type of term. Never {@literal null}.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Get formatted representation of term.
	 * 
	 * @return
	 */
	public String getFormatted() {

		String formatted = Type.PHRASE.equals(type) ? quotePhrase(raw) : raw;
		return negated ? negateRaw(formatted) : formatted;
	}

	@Override
	public String toString() {
		return getFormatted();
	}

	protected String quotePhrase(String raw) {
		return "\"" + raw + "\"";
	}

	protected String negateRaw(String raw) {
		return "-" + raw;
	}
}

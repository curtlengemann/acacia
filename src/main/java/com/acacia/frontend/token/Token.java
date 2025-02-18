package main.java.com.acacia.frontend.token;

/**
 * Represents a single token from the source code.
 */
public class Token {
    
    private String value;
    private TokenType tokenType;
    
    public Token(String value, TokenType type) {
        this.value = value;
        this.tokenType = type;
    }
    
    public Token(TokenType type) {
        this.tokenType = type;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public TokenType getTokenType() {
        return this.tokenType;
    }
    
    @Override
    public String toString() {
        final String value = this.value == null ? "null" : this.value;
        return "{Token: [value:" + value + " type:" + this.tokenType.toString() + "]}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)  return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Token other = (Token) o;

        if (this.tokenType != other.tokenType) return false;
        return this.value == null ? other.value == null : this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        int result = this.value == null ? 0 : this.value.hashCode();
        return 31 * result + this.tokenType.hashCode();
    }

}

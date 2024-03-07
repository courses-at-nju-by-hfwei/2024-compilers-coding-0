package dragon;

public class DragonLexer extends Lexer {
  // the last match position (beyond one)
  private int lastMatchPos = 0;

  // the longest match: position (beyond one) and token type
  int longestPrefixPos = 0;
  TokenType longestPrefixTokenType = null;

  private final KeywordTable kwTable = new KeywordTable();

  public DragonLexer(String input) {
    super(input);
  }

  @Override
  public Token nextToken() {
    if (peek == EOF) {
      return Token.EOF;
    }

    Token token;
    if (Character.isWhitespace(peek)) {
      token = WS();
    } else if (Character.isLetter(peek)) {
      token = ID();
    } else if (Character.isDigit(peek)) {
      token = NUMBER();
    } else if (peek == '=') {
      token = Token.EQ;
      advance();
    } else if (peek == '<') {
      advance();
      if (peek == '=') {
        token = Token.LE;
        advance();
      } else if (peek == '>') {
        token = Token.NE;
        advance();
      } else {
        token = Token.LT;
      }
    } else if (peek == '>') {
      advance();
      if (peek == '=') {
        token = Token.GE;
        advance();
      } else {
        token = Token.GT;
      }
    } else if (peek == '.') {
      token = Token.DOT;
      advance();
    } else if (peek == '+') {
      token = Token.POS;
      advance();
    } else if (peek == '-') {
      token = Token.NEG;
      advance();
    } else {
      token = new Token(TokenType.UNKNOWN, Character.toString(peek));
      advance();
    }

    this.lastMatchPos = pos;
    return token;
  }

  private Token NUMBER() {
    advance();
    int state = 13;
    while (true) {
      switch (state) {
        case 13:
          longestPrefixPos = pos;
          longestPrefixTokenType = TokenType.INT;

          if (Character.isDigit(peek)) {
            advance();
            break;
          } else if (peek == '.') {
            advance();
            state = 14;
            break;
          } else if (peek == 'E') {
            advance();
            state = 16;
            break;
          } else { // an INT
            return backToTheLongestMatch();
          }
        case 14:
          if (Character.isDigit(peek)) {
            advance();
            state = 15;
            break;
          } else {
            return backToTheLongestMatch();
          }
        case 15:
          // the longest match
          longestPrefixPos = pos;
          longestPrefixTokenType = TokenType.REAL;

          if (Character.isDigit(peek)) {
            advance();
            break;
          } else if (peek == 'E') {
            advance();
            state = 16;
            break;
          } else { // a REAL
            return backToTheLongestMatch();
          }
        case 16:
          if (peek == '+' || peek == '-') {
            advance();
            state = 17;
            break;
          } else if (Character.isDigit(peek)) {
            advance();
            state = 18;
            break;
          } else {
            return backToTheLongestMatch();
          }
        case 17:
          if (Character.isDigit(peek)) {
            advance();
            state = 18;
            break;
          } else {
            return backToTheLongestMatch();
          }
        case 18:
          longestPrefixPos = pos;
          longestPrefixTokenType = TokenType.SCI;

          if (Character.isDigit(peek)) {
            advance();
            break;
          } else { // an SCI
            return backToTheLongestMatch();
          }
        default:
          System.err.println("Unreachable");
      }
    }
  }

  private Token backToTheLongestMatch() {
    this.reset(longestPrefixPos);
    return new Token(longestPrefixTokenType,
        this.input.substring(this.lastMatchPos, this.pos));
  }

  private Token WS() {
    while (Character.isWhitespace(peek)) {
      advance();
    }

    return Token.WS;
  }

  private Token ID() {
    // add code below
    StringBuilder sb = new StringBuilder();

    do {
      sb.append(peek);
      advance();
    } while (Character.isLetterOrDigit(peek));

    Token token = this.kwTable.getKeyword(sb.toString());
    if (token == null) {
      return new Token(TokenType.ID, sb.toString());
    }

    return token;
  }

  private Token INT() {
    // add code below
    StringBuilder sb = new StringBuilder();

    do {
      sb.append(peek);
      advance();
    } while (Character.isDigit(peek));

    return new Token(TokenType.INT, sb.toString());
  }
}
package dragon;

/**
 * Types of tokens
 */
public enum TokenType {
  // Group 0
  EOF,
  WS,
  UNKNOWN,

  // Group 1
  // lookhead = 1 (LA(1))
  IF, ELSE,
  ID,
  INT,

  // Group 2
  // =, <>, <, <=, >, >=
  // LA(2)
  EQ, NE, LT, LE, GT, GE,

  // Group 3
  // arbitrary LA
  REAL,
  SCI,

  DOT, POS, NEG,
}
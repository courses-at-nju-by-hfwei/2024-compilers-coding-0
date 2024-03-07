lexer grammar DragonLexerRules;

@header {
package dragon;
}

// group 3: relational operators
EQ : '=' ;
NE : '<>' ;
LT : '<' ;
LE : '<=' ;
GT : '>' ;
GE : '>=' ;

DOT : '.' ;
POS : '+' ;
NEG : '-' ;

// group 1: keywords
IF : 'if' ;
ELSE : 'else' ;

// group 2: ID
ID : LETTER (LETTER | DIGIT)* ;

// group 4: numbers
INT : DIGITS ;
REAL : DIGITS ('.' DIGITS)? ;
SCI : DIGITS ('.' DIGITS)? ([eE] [+-]? DIGITS)? ;

WS : [ \t\r\n]+ -> skip;

fragment DIGIT :  [0-9] ;
fragment DIGITS : [0-9]+ ;
fragment LETTER : [a-zA-Z] ;
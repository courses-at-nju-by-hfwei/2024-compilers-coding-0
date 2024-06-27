// Problem 3 of 2024-exam-A
grammar ExprArray;

@header{
package expr.precedence;
}

stat : expr EOF;

expr : expr'['expr']'
     | '-'expr
     | expr '*' expr
     | expr '+' expr
     | ID
     ;

ID : [a-z];
WS : [ \t\r\n]+ -> skip;
grammar ExprArrayPrec;

@header{
package expr.precedence;
}

stat : expr[0] EOF;

expr[int _p]
    :   (
            '-'expr[4]
          | ID
        )
        (
            {3 >= $_p}? '*' expr[4]
          | {2 >= $_p}? '+' expr[3]
          | {5 >= $_p}? '['expr[0]']'
        )*
    ;

ID : [a-z];
WS : [ \t\r\n]+ -> skip;
// Comma-Separated Values
grammar CSVAG;

@header {
package ag;
}

file : hdr row+ ;
hdr : row ;
row : field (',' field)* ;

field : ID | NUMBER ;

ID : [a-z]+ ;
NUMBER : [0-9]+ ;
WS : [ \t\r\n]+ -> skip;
grammar VarsDeclAG;

@header {
package ag.type;
}

decl : type vars[$type.text] ;
type : 'int'        # IntType
     | 'float'      # FloatType
     ;
// Unfortunately, ANTLR 4 does not support this:
// "rule vars is left recursive but
// doesn't conform to a pattern ANTLR can handle"
// See https://stackoverflow.com/q/76062088/1833118

//vars[String typeStr]
//     : vars[$typeStr] ',' ID
//         { System.out.println($ID.text + " : " + $typeStr); }
//     | ID
//         { System.out.println($ID.text + " : " + $typeStr); }
//     ;

// A dummy rule
vars[String typeStr] : ID ;

ID : [a-z]+ ;
WS : [ \t\r\n]+ -> skip ;
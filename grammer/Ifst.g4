grammar Ifst;

prog : ifSt EOF;
//IFST & ELSEST
ifSt: IF bexp THEN st elseSt;
elseSt: ELSE st | /* epsilon */;

//BOOLEAN EXPRESSION
bexp: bexp OR bterm|bterm;
bterm: bterm AND bfactor|bfactor;
bfactor: NOT bfactor| '('bexp')' |expr relop expr;
relop: '<'|'>'|'<>'|'<='|'>='|'==';

// STATEMENT
st: ID '=' expr | '('expr')';

//EXPRESSION
expr:expr('*'|'/')term | term;
term:term('+'|'-')factor | factor;
factor:'('expr')'|ID|INT;


IF: 'if';
THEN: 'then';
ELSE: 'else';
OR: 'or';
AND: 'and';
NOT: 'not';
NEWLINE : [\r\n]+ ;
INT : [0-9]+ ;
ID: [a-zA-Z]+ ;
WS : [ \t\r\n]+ -> skip;
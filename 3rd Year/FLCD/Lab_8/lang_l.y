%{
#include <stdio.h>
#include <stdlib.h>

#define YYDEBUG 1
%}

%token START
%token END
%token AND
%token ARRAY_NUMBERS
%token ELSE
%token FOR
%token BIGGER_THAN
%token IF
%token NUMBER
%token OR
%token EQUALS
%token GREATER_OR_EQUAL
%token STRING
%token WHILE
%token LET
%token ID
%token CONST
%token NOT_EQUALS
%token PRINT
%token READ
%token SMALLER_OR_EQUAL
%token SMALLER_THAN
%token ATRIB

%left '+' '-' '*' '/'

%token PLUS
%token MINUS
%token DIV
%token MOD
%token MUL

%token OPEN_CURLY_BRACKET
%token CLOSED_CURLY_BRACKET
%token OPEN_ROUND_BRACKET
%token CLOSED_ROUND_BRACKET
%token OPEN_RIGHT_BRACKET
%token CLOSED_RIGHT_BRACKET

%token COMMA
%token SEMI_COLON
%token COLON
%token DOLLAR
%token APOSTROPHE

%start program

%%
program : START compound_statement END
	;
declaration :  LET ID DOLLAR type
	    ;
type :  NUMBER | STRING | typeTemp
	   ;
typeTemp : /*Empty*/ | ARRAY_NUMBERS OPEN_RIGHT_BRACKET CONST CLOSED_RIGHT_BRACKET
	 ;
compound_statement : OPEN_CURLY_BRACKET statement_list CLOSED_CURLY_BRACKET
	 ;
statement_list :  stmt stmtTemp
	 ;
stmtTemp : /*Empty*/ | statement_list
	 ;
stmt :  simple_statement SEMI_COLON | struct_statement
     ;
simple_statement :  assign_statement | input_output_statement | declaration
	 ;
struct_statement :  compound_statement | if_statement | while_statement | for_statement
	   ;
if_statement :  IF OPEN_ROUND_BRACKET boolean_condition CLOSED_ROUND_BRACKET compound_statement tempIf
       ;
tempIf : /*Empty*/ | ELSE compound_statement
       ;
for_statement :  FOR forheader compound_statement
       ;
forheader :  OPEN_ROUND_BRACKET NUMBER assign_statement SEMI_COLON OPEN_ROUND_BRACKET boolean_condition CLOSED_ROUND_BRACKET SEMI_COLON assign_statement CLOSED_ROUND_BRACKET
	  ;
while_statement :  WHILE OPEN_ROUND_BRACKET boolean_condition CLOSED_ROUND_BRACKET compound_statement
	  ;
assign_statement :  ID ATRIB expression
	   ;
expression : arithmetic2 arithmetic1
	   ;
arithmetic1 : PLUS arithmetic2 arithmetic1 | MINUS arithmetic2 arithmetic1 | /*Empty*/
	    ;
arithmetic2 : multiply2 multiply1
	    ;
multiply1 : MUL multiply2 multiply1 | DIV multiply2 multiply1 | MOD multiply2 multiply1 | /*Empty*/
	  ;
multiply2 : OPEN_ROUND_BRACKET expression CLOSED_ROUND_BRACKET | CONST | ID | IndexedIdentifier
	  ;
IndexedIdentifier :  ID OPEN_RIGHT_BRACKET CONST CLOSED_RIGHT_BRACKET
		  ;
input_output_statement :  READ identifiers_list | PRINT ID | PRINT CONST
      ;
identifiers_list : ID | ID COMMA identifiersTemp
      ;
identifiersTemp : /*Empty*/ | identifiers_list
      ;
condition : expression GREATER_OR_EQUAL expression |
	 expression BIGGER_THAN expression |
	 expression SMALLER_THAN expression |
	 expression SMALLER_OR_EQUAL expression |
	 expression EQUALS expression |
	 expression NOT_EQUALS expression
	  ;
boolean_condition : condition boolean_cond_temp
		  ;
boolean_cond_temp : /*Empty*/ | AND boolean_condition | OR boolean_condition
		 ;
%%
yyerror(char *s)
{
	printf("%s\n",s);
}

extern FILE *yyin;

main(int argc, char **argv)
{
	if(argc>1) yyin :  fopen(argv[1],"r");
	if(argc>2 && !strcmp(argv[2],"-d")) yydebug: 1;
	if(!yyparse()) fprintf(stderr, "\tO.K.\n");
}

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
program : START statement_list END
	;
declaration :  LET ID DOLLAR type
	    ;
type :  NUMBER | STRING | ARRAY_NUMBERS
	   ;
array_numbers_assign : OPEN_RIGHT_BRACKET numbers_list CLOSED_RIGHT_BRACKET
    ;
numbers_list :  /*Empty*/ | CONST | CONST COMMA numbers_list
    ;
expression : expression symbol_1 term | term
   ;
term : term symbol_2 factor | factor
     ;
factor: OPEN_ROUND_BRACKET expression CLOSED_ROUND_BRACKET | CONST | ID | array_numbers_assign
     ;
symbol_1 : PLUS | MINUS
    ;
symbol_2: MUL | DIV | MOD
    ;
assign_statement :  ID ATRIB expression
	   ;
input_output_statement :  READ identifiers_list | PRINT ID | PRINT CONST
      ;
identifiers_list : ID | ID COMMA identifiersTemp
      ;
identifiersTemp : /*Empty*/ | identifiers_list
      ;
compound_statement : OPEN_CURLY_BRACKET statement_list CLOSED_CURLY_BRACKET
	 ;
statement_list :  stmt | stmt statement_list
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
while_statement :  WHILE OPEN_ROUND_BRACKET boolean_condition CLOSED_ROUND_BRACKET compound_statement
        ;
for_statement :  FOR forheader compound_statement
       ;
forheader :  OPEN_ROUND_BRACKET assign_statement SEMI_COLON boolean_condition SEMI_COLON assign_statement CLOSED_ROUND_BRACKET
	  ;
condition : expression relation expression
        ;
relation :  GREATER_OR_EQUAL | BIGGER_THAN | SMALLER_THAN | SMALLER_OR_EQUAL | EQUALS | NOT_EQUALS
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

/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     START = 258,
     END = 259,
     AND = 260,
     ARRAY_NUMBERS = 261,
     ELSE = 262,
     FOR = 263,
     BIGGER_THAN = 264,
     IF = 265,
     NUMBER = 266,
     OR = 267,
     EQUALS = 268,
     GREATER_OR_EQUAL = 269,
     STRING = 270,
     WHILE = 271,
     LET = 272,
     ID = 273,
     CONST = 274,
     NOT_EQUALS = 275,
     PRINT = 276,
     READ = 277,
     SMALLER_OR_EQUAL = 278,
     SMALLER_THAN = 279,
     ATRIB = 280,
     PLUS = 281,
     MINUS = 282,
     DIV = 283,
     MOD = 284,
     MUL = 285,
     OPEN_CURLY_BRACKET = 286,
     CLOSED_CURLY_BRACKET = 287,
     OPEN_ROUND_BRACKET = 288,
     CLOSED_ROUND_BRACKET = 289,
     OPEN_RIGHT_BRACKET = 290,
     CLOSED_RIGHT_BRACKET = 291,
     COMMA = 292,
     SEMI_COLON = 293,
     COLON = 294,
     DOLLAR = 295,
     APOSTROPHE = 296
   };
#endif
/* Tokens.  */
#define START 258
#define END 259
#define AND 260
#define ARRAY_NUMBERS 261
#define ELSE 262
#define FOR 263
#define BIGGER_THAN 264
#define IF 265
#define NUMBER 266
#define OR 267
#define EQUALS 268
#define GREATER_OR_EQUAL 269
#define STRING 270
#define WHILE 271
#define LET 272
#define ID 273
#define CONST 274
#define NOT_EQUALS 275
#define PRINT 276
#define READ 277
#define SMALLER_OR_EQUAL 278
#define SMALLER_THAN 279
#define ATRIB 280
#define PLUS 281
#define MINUS 282
#define DIV 283
#define MOD 284
#define MUL 285
#define OPEN_CURLY_BRACKET 286
#define CLOSED_CURLY_BRACKET 287
#define OPEN_ROUND_BRACKET 288
#define CLOSED_ROUND_BRACKET 289
#define OPEN_RIGHT_BRACKET 290
#define CLOSED_RIGHT_BRACKET 291
#define COMMA 292
#define SEMI_COLON 293
#define COLON 294
#define DOLLAR 295
#define APOSTROPHE 296




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;


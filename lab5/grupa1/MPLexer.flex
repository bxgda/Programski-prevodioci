// import sekcija

import java_cup.runtime.*;

%%
// sekcija deklaracija
%class MPLexer

%cup

%line
%column

%eofval{
	return new Symbol( sym.EOF );
%eofval}

%{
   public int getLine()
   {
      return yyline;
   }
%}


// stanja
%xstate KOMENTAR
// macros
slovo = [a-zA-Z]
cifra = [0-9]
real = {cifra}+"."{cifra}+

%%
// rules section
"(*"            { yybegin( KOMENTAR ); }
<KOMENTAR>"*)"  { yybegin( YYINITIAL ); }
<KOMENTAR>.|\n  { /* skip comment content */ }


[\t\r\n ]       { ; }

// operatori
\+              { return new Symbol( sym.PLUS ); }
-               { return new Symbol( sym.MINUS ); }

// separatori
;               { return new Symbol( sym.SEMI ); }
,               { return new Symbol( sym.COMMA ); }
:=              { return new Symbol( sym.ASSIGN ); }
\[              { return new Symbol( sym.LBRACKET ); }
\]              { return new Symbol( sym.RBRACKET ); }

// kljucne reci
"main"          { return new Symbol( sym.MAIN ); }
"exit"          { return new Symbol( sym.EXIT ); }
"int"           { return new Symbol( sym.INT ); }
"float"         { return new Symbol( sym.FLOAT ); }
"bool"          { return new Symbol( sym.BOOL ); }
"for"           { return new Symbol( sym.FOR ); }
"in"            { return new Symbol( sym.IN ); }
"apply"         { return new Symbol( sym.APPLY ); }

// logicke konstante
"true"          { return new Symbol( sym.BOOLCONST, Boolean.TRUE ); }
"false"         { return new Symbol( sym.BOOLCONST, Boolean.FALSE ); }

// identifikatori
{slovo}({slovo}|{cifra})* { return new Symbol( sym.ID, yyline, yytext() ); }

// konstante
{real}           { return new Symbol( sym.FLOATCONST, Double.valueOf( yytext() ) ); }
{cifra}+         { return new Symbol( sym.INTCONST, Integer.valueOf( yytext() ) ); }


// error symbol
.               { System.out.println( "ERROR: " + yytext() ); }


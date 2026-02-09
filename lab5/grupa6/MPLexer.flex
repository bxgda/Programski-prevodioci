import java_cup.runtime.*;

%%

%class MPLexer
%cup
%line
%column

%eofval{
return new Symbol( sym.EOF );
%eofval}

%{
public int getLine() { return yyline; }
public int getColumn() { return yycolumn; }
%}

%xstate KOMENTAR

slovo = [a-zA-Z]
cifra = [0-9]
id = {slovo}({slovo}|{cifra})*
real_konst = {cifra}+\.{cifra}+

%%

<YYINITIAL> "(*" { yybegin( KOMENTAR ); }
<KOMENTAR> "*)"   { yybegin( YYINITIAL ); }
<KOMENTAR>.      { ; }
<KOMENTAR>\n     { ; }

[ \t\r\n\f]       { ; }

"program"        { return new Symbol( sym.PROGRAM );  }
"begin"          { return new Symbol( sym.BEGIN );    }
"end"            { return new Symbol( sym.END );      }
"while"          { return new Symbol( sym.WHILE );    }
"else"           { return new Symbol( sym.ELSE );     }
"integer"        { return new Symbol( sym.INTEGER );  }
"char"           { return new Symbol( sym.CHAR );     }
"real"           { return new Symbol( sym.REAL );     }
"boolean"        { return new Symbol( sym.BOOLEAN );  }
"or"             { return new Symbol( sym.OR );       }
"and"            { return new Symbol( sym.AND );      }

";"              { return new Symbol( sym.SEMI );     }
","              { return new Symbol( sym.COMMA );    }
"."              { return new Symbol( sym.DOT );      }
":"              { return new Symbol( sym.COLON );    }
":="             { return new Symbol( sym.ASSIGN );   }
"("              { return new Symbol( sym.LEFTPAR );  }
")"              { return new Symbol( sym.RIGHTPAR ); }
"<="             { return new Symbol( sym.LE );       }
"=="             { return new Symbol( sym.EQ );       }
">="             { return new Symbol( sym.GE );       }

"true"           { return new Symbol( sym.CONST, yyline, yycolumn, Boolean.TRUE ); }
"false"          { return new Symbol( sym.CONST, yyline, yycolumn, Boolean.FALSE ); }

{real_konst}     { return new Symbol( sym.CONST, yyline, yycolumn, Double.valueOf(yytext()) ); }
{cifra}+         { return new Symbol( sym.CONST, yyline, yycolumn, Integer.valueOf(yytext()) ); }
{id}             { return new Symbol( sym.ID, yyline, yycolumn, yytext() ); }

' . '            { return new Symbol( sym.CONST, yyline, yycolumn, yytext().charAt(1) ); }

.                { System.out.println( "Lexer Error linija " + (yyline+1) + ": Nepoznat simbol '" + yytext() + "'" ); }
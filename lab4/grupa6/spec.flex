import java_cup.runtime.Symbol;
import java.io.*;

%%

%class MPLexer
%cup
%line
%column

%eofval{
    return new Symbol(sym.EOF);
%eofval}

%{
    KWTable kwTable = new KWTable();
    
    // --- DODATO: Metoda koju parser ocekuje ---
    public int getLine() {
        return yyline + 1;
    }
    // ------------------------------------------

    // Pomocna funkcija za kreiranje Symbol objekata
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

%x KOMENTAR

slovo   = [a-zA-Z]
cifra   = [0-9]
id      = ({slovo})({slovo}|{cifra}|\$)*

/* Konstante */
int10   = {cifra}+
int8    = 0[0-7]+
int16   = 0x[0-9a-fA-F]+

real1   = {cifra}+\.{cifra}*([eE][+-]?{cifra}+)?
real2   = \.{cifra}+([eE][+-]?{cifra}+)?

charc   = \'[^\']\'

%%

// KOMENTARI
"**"            { yybegin(KOMENTAR); }
<KOMENTAR>"**"  { yybegin(YYINITIAL); }
<KOMENTAR>.|\n  { /* ignorisi */ }

// WHITESPACE
[ \t\r\n]+      { /* preskoci */ }

// OPERATORI I SEPARATORI
":="            { return symbol(sym.ASSIGN); }
"<="            { return symbol(sym.LE); }
">="            { return symbol(sym.GE); }
"=="            { return symbol(sym.EQ); }
"<>"            { return symbol(sym.NE); }
"<"             { return symbol(sym.LT); }
">"             { return symbol(sym.GT); }
","             { return symbol(sym.COMMA); }
":"             { return symbol(sym.COLON); }
";"             { return symbol(sym.SEMICOLON); }
"("             { return symbol(sym.LEFTPAR); }
")"             { return symbol(sym.RIGHTPAR); }
"."             { return symbol(sym.DOT); }

// KONSTANTE
{int16}         { return symbol(sym.CONST, Integer.decode(yytext())); }
{int8}          { return symbol(sym.CONST, Integer.decode(yytext())); }
{int10}         { return symbol(sym.CONST, Integer.parseInt(yytext())); }

{real1}         { return symbol(sym.CONST, Double.parseDouble(yytext())); }
{real2}         { return symbol(sym.CONST, Double.parseDouble(yytext())); }

{charc}         { return symbol(sym.CONST, yytext().charAt(1)); }

// IDENTIFIKATORI I KLJUCNE RECI
{id} {
    String text = yytext();
    if (text.equals("true"))  return symbol(sym.CONST, true);
    if (text.equals("false")) return symbol(sym.CONST, false);

    int token = kwTable.find(text);
    if (token == sym.ID) {
        return symbol(sym.ID, text);
    } else {
        return symbol(token);
    }
}

// GRESKA
. {
    System.err.println("Lexical error: " + yytext() + " at line " + (yyline+1));
}
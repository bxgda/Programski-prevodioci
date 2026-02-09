import java.io.*;
import AST.*;
import java_cup.runtime.Symbol;

public class Main {
    public static void main(String[] args) {
        try {
            // Prvo samo testiramo Lexer da vidimo sta cita
            FileReader testFile = new FileReader(args[0]);
            MPLexer testScanner = new MPLexer(testFile);
            Symbol s = testScanner.next_token();
            
            // OVO JE KLJUČNO: Ispisuje tip tokena (broj iz sym.java)
            System.out.println("DEBUG: Prvi token u fajlu je tipa: " + s.sym + " (Vrednost: " + s.value + ")");
            testFile.close();

            // Sad stvarno pokrecemo parser
            FileReader file = new FileReader(args[0]);
            MPLexer scanner = new MPLexer(file);
            MPParser parser = new MPParser(scanner);
            
            System.out.println("Pokrecem parser...");
            ASTNode ast = (ASTNode) parser.parse().value;
            
            String outFileName = args[0].substring(0, args[0].indexOf(".") + 1) + "ic";
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName));
            ast.translate(writer);
            writer.close();
            
            System.out.println("Gotovo! Fajl " + outFileName + " je generisan.");
        } catch (Exception e) {
            System.out.println("PARSER JE PUKAO:");
            e.printStackTrace();
        }
    }
}
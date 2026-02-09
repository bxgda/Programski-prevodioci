import java.io.*;
import java_cup.runtime.Symbol;
import SymbolTable.*; // Neophodno ako direktno referenciramo tipove, ali ovde je bitno zbog parsera

public class Main {
    public static void main(String[] args) {
        try {
            // Provera argumenata
            if (args.length == 0) {
                System.out.println("GRESKA: Morate proslediti ime ulaznog fajla kao argument.");
                return;
            }

            String fileName = args[0];
            FileReader fr = new FileReader(fileName);
            
            // Inicijalizacija leksera
            MPLexer lexer = new MPLexer(fr);
            
            // Inicijalizacija parsera
            parser p = new parser(lexer);
            
            System.out.println("Zapocinjem semanticku analizu fajla: " + args[0]);
            System.out.println("------------------------------------------------");
        
            p.parse(); // Pokrece analizu
            p.checkWarnings(); // Ispisuje upozorenja
        
            System.out.println("------------------------------------------------");
            System.out.println("Analiza zavrsena.");
            System.out.println("Ukupan broj gresaka: " + p.errNo);
            System.out.println("Ukupan broj upozorenja: " + p.warnNo);
            
        } catch (FileNotFoundException e) {
            System.err.println("GRESKA: Fajl '" + args[0] + "' nije pronadjen.");
        } catch (Exception e) {
            System.err.println("GRESKA: Doslo je do fatalne greske tokom rada parsera.");
            e.printStackTrace();
        }
    }
}
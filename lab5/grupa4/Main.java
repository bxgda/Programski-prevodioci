import java.io.*;
import java_cup.runtime.Symbol;
import AST.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Provera da li je korisnik prosledio ime fajla kao argument
            if (args.length == 0) {
                System.out.println("GRESKA: Morate proslediti ime ulaznog fajla kao argument.");
                System.out.println("Primer: java -cp \".;jcup_runtime.jar\" Main test_grupa4.txt");
                return;
            }

            // Uzimamo prvi argument iz komandne linije
            String fileName = args[0];
            FileReader fr = new FileReader(fileName);
            
            // Inicijalizacija leksera
            MPLexer lexer = new MPLexer(fr);
            
            // Inicijalizacija parsera - koristi parser_g4
            parser_g4 p = new parser_g4(lexer);
            
            System.out.println("Zapocinjem sintaksnu analizu fajla: " + fileName);
            
            // Pokretanje analize - parse() vraća Symbol
            Symbol result = p.parse();
            
            if (result != null && result.value instanceof Program) {
                Program prog = (Program) result.value;
                
                System.out.println("Sintaksna analiza zavrsena uspesno!");
                System.out.println("Generisujem medukod...");
                
                // Generiši medukod u izlazni fajl
                String outputFileName = "output.code";
                BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName));
                
                prog.translate(out);
                
                out.close();
                
                System.out.println("Medukod generishan u fajl: " + outputFileName);
            } else {
                System.out.println("GRESKA: Parser nije vratio validan AST.");
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("GRESKA: Fajl nije pronadjen.");
        } catch (Exception e) {
            System.err.println("Sintaksna analiza NIJE uspela.");
            System.err.println("Opis greske: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
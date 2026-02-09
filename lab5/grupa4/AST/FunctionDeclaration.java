package AST;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FunctionDeclaration predstavlja deklaraciju funkcije.
 * Primer: f(x:int) => x;
 * 
 * Prevod:
 * - Generiši label sa imenom funkcije
 * - Mapira parametre na memorijske lokacije (param_f_i)
 * - Prevedi telo (Expression)
 * - Upiši rezultat u memoriju return vrednosti
 */
public class FunctionDeclaration extends ASTNode {
    private String name;
    private List<Parameter> parameters;
    private Expression body;
    
    // Mapa iz imena parametra u memorijsku lokaciju
    private static Map<String, String> parameterMap = new HashMap<>();
    
    public FunctionDeclaration(String name, List<Parameter> parameters, Expression body) {
        this.name = name;
        this.parameters = parameters != null ? parameters : new ArrayList<>();
        this.body = body;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Parameter> getParameters() {
        return parameters;
    }
    
    public Expression getBody() {
        return body;
    }
    
    public static String getParameterMemLocation(String paramName) {
        return parameterMap.get(paramName);
    }
    
    @Override
    public void translate(BufferedWriter out) throws IOException {
        // Emituj label sa imenom funkcije
        out.write(name + ":");
        out.newLine();
        
        // Mapira parametre na memorijske lokacije
        parameterMap.clear();
        for (int i = 0; i < parameters.size(); i++) {
            Parameter p = parameters.get(i);
            String memName = "param_" + name + "_" + i;
            parameterMap.put(p.getName(), memName);
        }
        
        // Prevedi telo funkcije
        body.translate(out);
        
        // Učitaj rezultat u Return register (R1)
        body.genLoad("R1", out);
        
        // Upiši rezultat u memorijsku lokaciju ret_<funcname>
        String retMemName = "ret_" + name;
        out.write("\tStore\t\tR1, " + retMemName);
        out.newLine();
        
        // Skok nazad na labelu koja je emitovana nakon Jump-a
        // Za sada nema eksplicitnog skoka - kontrola pada kroz
        // ALI: to znači da će funkcija izvršiti ostatak koda što je pogrešno!
        // Trebam fiksnu lokaciju gde se vraćam
        
        // Rešenje: ima labela lab0 koji je početak main programa
        // Problem: ne znam gde će biti ta labela
        // Najbolje je da svaki FunctionCall emituje LABELU nakon skoka
        // koju će funkcija koristiti da se vrati
    }
}


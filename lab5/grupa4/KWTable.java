import java.util.Hashtable;

public class KWTable {

    // Dodato <String, Integer> da uklonimo warnings
    private Hashtable<String, Integer> mTable;

    public KWTable() {
        // Inicijalizacija hash tabele
        mTable = new Hashtable<String, Integer>();
        mTable.put("program", sym_g4.PROGRAM);
        mTable.put("end", sym_g4.END);
        mTable.put("int", sym_g4.INT);
        mTable.put("float", sym_g4.FLOAT);
        mTable.put("char", sym_g4.CHAR);
    }

    /**
     * Vraca ID kljucne reci ili ID identifikatora
     */
    public int find(String keyword) {
        Object symbol = mTable.get(keyword);
        if (symbol != null)
            return ((Integer) symbol).intValue();

        // Ako nije kljucna rec, onda je identifikator
        return sym_g4.ID;
    }
}
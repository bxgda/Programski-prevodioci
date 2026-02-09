import java.util.List;

enum MyType { CHAR, INT, FLOAT }

enum SymKind { VAR, FUNC }

class VarInfo {
    MyType type;
    boolean initialized;
    VarInfo(MyType t, boolean init) { type = t; initialized = init; }
}

class ParamInfo {
    String name;
    MyType type;
    boolean hasDefault;
    ParamInfo(String n, MyType t, boolean d) { name = n; type = t; hasDefault = d; }
}

class FuncInfo {
    MyType retType;
    List<ParamInfo> params;
    int minArgs;
    FuncInfo(MyType r, List<ParamInfo> p, int min) { retType = r; params = p; minArgs = min; }
}

class SymInfo {
    SymKind kind;
    VarInfo var;
    FuncInfo func;
    static SymInfo var(VarInfo v) {
        SymInfo s = new SymInfo();
        s.kind = SymKind.VAR;
        s.var = v;
        return s;
    }
    static SymInfo func(FuncInfo f) {
        SymInfo s = new SymInfo();
        s.kind = SymKind.FUNC;
        s.func = f;
        return s;
    }
}

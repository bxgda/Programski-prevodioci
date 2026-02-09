# Brza Referenca - Kompajliranje i Pokretanje

## TL;DR - Najkraće Verzije

### Kompajliranje

```bash
# Generiši parser (samo prvi put ili ako se parser_grupa4.cup promeni)
java -classpath "java_cup_v10k/java_cup" java_cup.Main -parser parser_g4 -symbols sym_g4 parser_grupa4.cup

# Kompajliraj sve
javac -encoding UTF-8 -cp "jcup_runtime.jar" sym_g4.java parser_g4.java MPLexer.java KWTable.java EnvManager.java GlobalContext.java Main.java AST/*.java
```

### Pokretanje

```bash
java -cp "jcup_runtime.jar;." Main test_grupa4.txt
```

Output će biti u fajlu `output.code`

---

## Detaljne Instrukcije

### 1. Prvi Put - Kompletan Setup

#### 1.1 Generiši Parser iz CUP specifikacije

```bash
# Windows PowerShell
cd C:\Users\aleks\OneDrive\Desktop\Fakultet\4.\ godina\7.\ Semestar\Programski\ prevodioci\Lab05

# Generiši parser_g4.java i sym_g4.java
java -classpath "java_cup_v10k/java_cup" java_cup.Main -parser parser_g4 -symbols sym_g4 parser_grupa4.cup
```

Očekivani output:
```
CUP v0.10k: Trace/Debug mode doesn't work in modern Java versions.
CUP v0.10k (04 Dec 2010): Verbose Mode ...
...
57 States with 1 Shift/Reduce conflicts
Parser file created successfully.
Symbol file created successfully.
```

Generiše:
- `parser_g4.java` - Parser klasa
- `sym_g4.java` - Token symbols

#### 1.2 Kompajliraj Sve Klase

```bash
# Kompajliraj sa UTF-8 encoding za srpske karaktere
javac -encoding UTF-8 -cp "jcup_runtime.jar" ^
    sym_g4.java ^
    parser_g4.java ^
    MPLexer.java ^
    KWTable.java ^
    EnvManager.java ^
    GlobalContext.java ^
    Main.java ^
    AST\ASTNode.java ^
    AST\Expression.java ^
    AST\Statement.java ^
    AST\Program.java ^
    AST\VariableDeclaration.java ^
    AST\VariableExpression.java ^
    AST\ConstantExpression.java ^
    AST\FunctionDeclaration.java ^
    AST\FunctionCall.java ^
    AST\AssignmentStatement.java ^
    AST\ExpressionStatement.java ^
    AST\Parameter.java
```

Alternativno - koristi wildcard:
```bash
javac -encoding UTF-8 -cp "jcup_runtime.jar" sym_g4.java parser_g4.java MPLexer.java KWTable.java EnvManager.java GlobalContext.java Main.java AST/*.java
```

Bez greške = uspešna kompilacija!

---

### 2. Normalna Upotreba

#### 2.1 Kreiraj Test Fajl (test_grupa4.txt)

```
program 
{
  f(x:int) => x ;
  a:int ;
  a = f(5)
}
end
```

#### 2.2 Pokreni Parser

```bash
java -cp "jcup_runtime.jar;." Main test_grupa4.txt
```

Output na konzoli:
```
Sintaksna analiza zavrsena uspesno!
Medukod generishan u fajl: output.code
```

#### 2.3 Pregledaj Generisani Kod

```bash
# Windows
type output.code

# Linux/Mac
cat output.code
```

---

### 3. Česti Problemi i Rešenja

#### Problem: `error: unmappable character for encoding Cp1252`

**Rešenje:** Dodaj `-encoding UTF-8` u javac komandu:
```bash
javac -encoding UTF-8 -cp "jcup_runtime.jar" ...
```

#### Problem: `cannot find symbol: class sym_g4`

**Rešenje:** Prvo generiši parser:
```bash
java -classpath "java_cup_v10k/java_cup" java_cup.Main -parser parser_g4 -symbols sym_g4 parser_grupa4.cup
```

#### Problem: `Exception in thread "main" java.io.FileNotFoundException`

**Rešenje:** Prosledi ispravan put do test fajla:
```bash
java -cp "jcup_runtime.jar;." Main path/to/test_grupa4.txt
```

#### Problem: Nema output.code fajla

**Rešenje:** Prosledi test fajl koji je parsabilna gramatika:
```
program { a:int } end
```

---

### 4. PowerShell Skripte za Automatizaciju

#### Skripte/kompajliraj.ps1

```powershell
# Generiši parser
Write-Host "Generisem parser..." -ForegroundColor Green
java -classpath "java_cup_v10k/java_cup" java_cup.Main -parser parser_g4 -symbols sym_g4 parser_grupa4.cup

# Kompajliraj
Write-Host "Kompajliram..." -ForegroundColor Green
javac -encoding UTF-8 -cp "jcup_runtime.jar" `
    sym_g4.java `
    parser_g4.java `
    MPLexer.java `
    KWTable.java `
    EnvManager.java `
    GlobalContext.java `
    Main.java `
    AST/*.java

if ($LASTEXITCODE -eq 0) {
    Write-Host "Kompajliranje uspesno!" -ForegroundColor Green
} else {
    Write-Host "Greška pri kompajliranju!" -ForegroundColor Red
}
```

#### Skripte/pokreni.ps1

```powershell
param (
    [string]$TestFile = "test_grupa4.txt"
)

Write-Host "Pokrecujem parser sa $TestFile..." -ForegroundColor Green
java -cp "jcup_runtime.jar;." Main $TestFile

if (Test-Path "output.code") {
    Write-Host "Rezultat u output.code:" -ForegroundColor Green
    Get-Content output.code
} else {
    Write-Host "Greška: output.code nije napravljen" -ForegroundColor Red
}
```

Korišćenje:
```bash
powershell -ExecutionPolicy Bypass -File Skripte/kompajliraj.ps1
powershell -ExecutionPolicy Bypass -File Skripte/pokreni.ps1
```

---

### 5. Struktura Direktorijuma

```
Lab05/
├── parser_grupa4.cup           # Ulazna CUP specifikacija
├── spec.flex                   # Lexer specifikacija
├── Main.java                   # Entry point
├── MPLexer.java               # Generisani lexer (iz spec.flex)
├── KWTable.java               # Keyword table
├── EnvManager.java
├── GlobalContext.java
│
├── parser_g4.java             # Generisan iz parser_grupa4.cup
├── sym_g4.java                # Generisan iz parser_grupa4.cup
│
├── AST/                        # AST čvorovi
│   ├── ASTNode.java
│   ├── Expression.java
│   ├── Statement.java
│   ├── Program.java
│   └── ... (ostali)
│
├── test_grupa4.txt            # Test ulaz
├── output.code                # Generisani medukod
│
├── jcup_runtime.jar           # CUP runtime
├── java_cup/                  # CUP klase
├── java_cup_v10k/             # CUP v10k alati
│
├── README.md                  # Srpska dokumentacija
├── README_EN.md               # English documentation
└── QUICK_START.md             # Ovaj fajl
```

---

### 6. Redosled Koraka - Checklist

- [ ] Otvori PowerShell/Terminal
- [ ] Naviguj u Lab05 direktorijum
- [ ] Prvo kompajliranje: `javac -encoding UTF-8 -cp "jcup_runtime.jar" sym_g4.java parser_g4.java MPLexer.java KWTable.java EnvManager.java GlobalContext.java Main.java AST/*.java`
- [ ] Kreiraj test.txt sa validnom gramatikom (ili koristi test_grupa4.txt)
- [ ] Pokreni: `java -cp "jcup_runtime.jar;." Main test_grupa4.txt`
- [ ] Provjeri `output.code`

---

### 7. Primeri Test Fajlova

#### Primer 1: Jednostavna Funkcija

`test1.txt`:
```
program 
{
  add(a:int, b:int) => a ;
  x:int ;
  x = add(3, 5)
}
end
```

#### Primer 2: Više Funkcija

`test2.txt`:
```
program 
{
  double(x:int) => x ;
  triple(x:int) => x ;
  result:int ;
  result = double(5)
}
end
```

#### Primer 3: Samo Deklaracije

`test3.txt`:
```
program 
{
  a:int ;
  b:float ;
  c:char
}
end
```

#### Primer 4: Samo Funkcija

`test4.txt`:
```
program 
{
  identity(x:int) => x
}
end
```

---

### 8. Verifikacija Rezultata

Ako je `output.code` generishan, trebalo bi:

1. Sadržavati `Jump lab0` na početku
2. Imati bar jednu labelu funkcije: `f:`
3. Imati `Load_Const` za konstante
4. Imati `Load_Mem` za promenljive
5. Imati `Store` za dodelе
6. Imati `Jump` za pozive funkcija

Primer ispravan output:
```
	Jump		lab0
f:
	Load_Mem	R1, param_f_0
	Store		R1, ret_f
lab0:
	Load_Const	R1, 5
	Store		R1, param_f_0
	Jump		f
lab1:
	Load_Mem	R1, ret_f
	Store		R1, a
```

---

### 9. Životni Ciklus Razvoja

Tokom razvoja/testiranja:

```
┌─────────────────────────────────────┐
│ Izmeni parser_grupa4.cup            │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│ Generiši parser (java_cup.Main)    │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│ Kompajliraj sve (javac)             │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│ Pokreni na test fajlu (java Main)  │
└──────────────┬──────────────────────┘
               │
               ▼
┌─────────────────────────────────────┐
│ Provjeri output.code                │
└──────────────┬──────────────────────┘
               │
        ┌──────┴─────────────────────┬─────────────────┐
        ▼                           ▼                  ▼
    USPEŠNO                    Greška                Upozorenje
        │                         │                    │
        ▼                         ▼                    ▼
    NASTAVAK            Popravka Java koda    Optimizacija/Refaktor
```

---

### 10. Brz Pregled Fajlova

| Fajl | Tip | Cilj |
|------|-----|------|
| `parser_grupa4.cup` | Input | CUP specifikacija gramatike |
| `parser_g4.java` | Generated | Parser klasa |
| `sym_g4.java` | Generated | Token definicije |
| `Main.java` | Source | Entry point, orchestration |
| `AST/*.java` | Source | Abstract Syntax Tree klase |
| `MPLexer.java` | Generated | Lexical analyzer |
| `test_grupa4.txt` | Data | Ulazni test program |
| `output.code` | Generated | Izlazni medukod |

---

## Brze Komande za Copy-Paste

### Kompajliranje
```
javac -encoding UTF-8 -cp "jcup_runtime.jar" sym_g4.java parser_g4.java MPLexer.java KWTable.java EnvManager.java GlobalContext.java Main.java AST/*.java
```

### Pokretanje
```
java -cp "jcup_runtime.jar;." Main test_grupa4.txt
```

### Pregled Rezultata
```
type output.code
```

### Ponovna Generisanja Parsera
```
java -classpath "java_cup_v10k/java_cup" java_cup.Main -parser parser_g4 -symbols sym_g4 parser_grupa4.cup
```

---

**Zadnja Ažuriranja:** 2024
**Status:** Ready to Use

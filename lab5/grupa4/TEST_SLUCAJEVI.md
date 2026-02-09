# Test Slučajevi - CUP Parser Grupa 4

## Pregled

Ovaj dokument sadrži kompletan set test slučajeva za validaciju CUP parser sistema. Svaki test ima:
- **Naziv** - Opis šta test proverava
- **Ulaz** - Ulazni program
- **Očekivani Izlaz** - Šta bi trebalo biti u output.code
- **Status** - ✓ PASS, ✗ FAIL, ili ? UNKNOWN

---

## Grupa 1: Osnovni Testovi

### Test 1.1: Prazan Program

**Cilj:** Testira minimalnu validnu gramatiku

**Ulaz** (`test_1_1.txt`):
```
program 
{
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
lab0:
```

**Opis:** Bez deklaracija ili iskaza, trebalo bi samo Jump na main label.

**Status:** ? UNKNOWN (nije testirano)

---

### Test 1.2: Deklaracija Varijable

**Cilj:** Testira deklaraciju varijable bez inicijalizacije

**Ulaz** (`test_1_2.txt`):
```
program 
{
  x:int
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
lab0:
```

**Opis:** Samo deklaracija, nema koda - trebalo bi biti prazno (osim Jump).

**Status:** ? UNKNOWN (nije testirano)

---

### Test 1.3: Više Varijabli

**Cilj:** Testira više deklaracija varijabli različitih tipova

**Ulaz** (`test_1_3.txt`):
```
program 
{
  a:int ;
  b:float ;
  c:char
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
lab0:
```

**Opis:** Tri varijable, tri tipa - trebalo bi biti prazno nakon Jump.

**Status:** ? UNKNOWN (nije testirano)

---

## Grupa 2: Funkcije

### Test 2.1: Identiteta Funkcija

**Cilj:** Testira funkciju koja vraća parametar

**Ulaz** (`test_2_1.txt`):
```
program 
{
  identity(x:int) => x ;
  result:int ;
  result = identity(5)
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
identity:
	Load_Mem	R1, param_identity_0
	Store		R1, ret_identity
lab0:
	Load_Const	R1, 5
	Store		R1, param_identity_0
	Jump		identity
lab1:
	Load_Mem	R1, ret_identity
	Store		R1, result
```

**Opis:** 
- Funkcija prima jedan parametar
- Memoriju param_identity_0
- Vraća u ret_identity
- Main poziva sa argumentom 5

**Status:** ✓ PASS

---

### Test 2.2: Više Parametara

**Cilj:** Testira funkciju sa dva parametra

**Ulaz** (`test_2_2.txt`):
```
program 
{
  add(a:int, b:int) => a ;
  sum:int ;
  sum = add(3, 4)
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
add:
	Load_Mem	R1, param_add_0
	Store		R1, ret_add
lab0:
	Load_Const	R1, 3
	Store		R1, param_add_0
	Load_Const	R1, 4
	Store		R1, param_add_1
	Jump		add
lab1:
	Load_Mem	R1, ret_add
	Store		R1, sum
```

**Opis:**
- Dva parametra → param_add_0 i param_add_1
- Oba argumenta se čuvaju pre Jump
- Funkcija pristupa prvi parametar

**Status:** ? UNKNOWN (nije testirano - trebalo bi testirati)

---

### Test 2.3: Funkcija Bez Parametara

**Cilj:** Testira funkciju bez parametara

**Ulaz** (`test_2_3.txt`):
```
program 
{
  getConstant() => 42 ;
  c:int ;
  c = getConstant()
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
getConstant:
	Load_Const	R1, 42
	Store		R1, ret_getConstant
lab0:
	Jump		getConstant
lab1:
	Load_Mem	R1, ret_getConstant
	Store		R1, c
```

**Opis:**
- Nema parametara, nema param_* memorije
- Direktno Load_Const u telu
- Jump bez prethodnog Store

**Status:** ? UNKNOWN (nije testirano)

---

### Test 2.4: Više Funkcija

**Cilj:** Testira više funkcija u istom programu

**Ulaz** (`test_2_4.txt`):
```
program 
{
  f(x:int) => x ;
  g(y:int) => y ;
  a:int ;
  a = f(5)
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
f:
	Load_Mem	R1, param_f_0
	Store		R1, ret_f
g:
	Load_Mem	R1, param_g_0
	Store		R1, ret_g
lab0:
	Load_Const	R1, 5
	Store		R1, param_f_0
	Jump		f
lab1:
	Load_Mem	R1, ret_f
	Store		R1, a
```

**Opis:**
- Dve funkcije sa istom logikom
- Različitim memorijskim lokacijama (param_f_0 vs param_g_0)
- Samo prvi se poziva u main-u

**Status:** ? UNKNOWN (nije testirano)

---

## Grupa 3: Konstante i Tipovi

### Test 3.1: Celobrojne Konstante

**Cilj:** Testira različite celobrojne vrednosti

**Ulaz** (`test_3_1.txt`):
```
program 
{
  f(x:int) => x ;
  a:int ;
  a = f(0);
  b:int ;
  b = f(999);
  c:int ;
  c = f(-5)
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
f:
	Load_Mem	R1, param_f_0
	Store		R1, ret_f
lab0:
	Load_Const	R1, 0
	Store		R1, param_f_0
	Jump		f
lab1:
	Load_Mem	R1, ret_f
	Store		R1, a
	Load_Const	R1, 999
	Store		R1, param_f_0
	Jump		f
lab2:
	Load_Mem	R1, ret_f
	Store		R1, b
	Load_Const	R1, -5
	Store		R1, param_f_0
	Jump		f
lab3:
	Load_Mem	R1, ret_f
	Store		R1, c
```

**Opis:**
- Tri poziva funkcije sa različitim argumentima
- Tri povratne labele (lab1, lab2, lab3)
- Load_Const sa 0, 999, -5

**Status:** ? UNKNOWN (nije testirano)

---

### Test 3.2: Karakterne Konstante

**Cilj:** Testira char tip podataka

**Ulaz** (`test_3_2.txt`):
```
program 
{
  identity(x:char) => x ;
  c:char ;
  c = identity('A')
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
identity:
	Load_Mem	R1, param_identity_0
	Store		R1, ret_identity
lab0:
	Load_Const	R1, 'A'
	Store		R1, param_identity_0
	Jump		identity
lab1:
	Load_Mem	R1, ret_identity
	Store		R1, c
```

**Opis:**
- Load_Const sa karakternom vrednosu 'A'
- Parameter i povratna vrednost iste memorije kao int

**Status:** ? UNKNOWN (nije testirano)

---

### Test 3.3: Decimalne Konstante

**Cilj:** Testira float tip podataka

**Ulaz** (`test_3_3.txt`):
```
program 
{
  f(x:float) => x ;
  pi:float ;
  pi = f(3.14)
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
f:
	Load_Mem	R1, param_f_0
	Store		R1, ret_f
lab0:
	Load_Const	R1, 3.14
	Store		R1, param_f_0
	Jump		f
lab1:
	Load_Mem	R1, ret_f
	Store		R1, pi
```

**Opis:**
- Float konstanta 3.14
- Ista semantika kao int, samo vrednost je decimalna

**Status:** ? UNKNOWN (nije testirano)

---

## Grupa 4: Promenljive u Izrazima

### Test 4.1: Korišćenje Varijable kao Argument

**Cilj:** Testira prosljeđivanje varijable (ne konstante) kao argumenta

**Ulaz** (`test_4_1.txt`):
```
program 
{
  f(x:int) => x ;
  a:int ;
  b:int ;
  a = f(5);
  b = f(a)
}
end
```

**Očekivani Izlaz:**
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
	Load_Mem	R1, a
	Store		R1, param_f_0
	Jump		f
lab2:
	Load_Mem	R1, ret_f
	Store		R1, b
```

**Opis:**
- Drugi poziv koristi `Load_Mem R1, a` umesto `Load_Const`
- `a` je prvo dodeljena vrednost 5, zatim korišćena kao argument

**Status:** ? UNKNOWN (nije testirano)

---

### Test 4.2: Direktna Dodela Varijable

**Cilj:** Testira dodelu vrednosti promenljive drugoj varijabli

**Ulaz** (`test_4_2.txt`):
```
program 
{
  x:int ;
  y:int ;
  y = x
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
lab0:
	Load_Mem	R1, x
	Store		R1, y
```

**Opis:**
- Nema funkcije, direktna dodela
- Load_Mem iz x (izvor)
- Store u y (odredište)

**Status:** ? UNKNOWN (nije testirano)

---

## Grupa 5: Kompleksnije Scenarije

### Test 5.1: Ulančane Funkcije

**Cilj:** Testira pozivanje funkcije sa rezultatom druge funkcije (ako je podržano)

**Ulaz** (`test_5_1.txt`):
```
program 
{
  f(x:int) => x ;
  g(y:int) => y ;
  result:int ;
  result = g(5)
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
f:
	Load_Mem	R1, param_f_0
	Store		R1, ret_f
g:
	Load_Mem	R1, param_g_0
	Store		R1, ret_g
lab0:
	Load_Const	R1, 5
	Store		R1, param_g_0
	Jump		g
lab1:
	Load_Mem	R1, ret_g
	Store		R1, result
```

**Opis:**
- Trenutna gramatika ne dozvoljava `g(f(5))` (samo konstante i varijable kao argumenti)
- Ovaj test proverava da se `f` deklarira ali ne koristi

**Status:** ? UNKNOWN (nije testirano)

---

### Test 5.2: Više Iskaza u Main-u

**Cilj:** Testira sekvence iskaza

**Ulaz** (`test_5_2.txt`):
```
program 
{
  f(x:int) => x ;
  a:int ;
  b:int ;
  a = f(5);
  b = f(10)
}
end
```

**Očekivani Izlaz:**
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
	Load_Const	R1, 10
	Store		R1, param_f_0
	Jump		f
lab2:
	Load_Mem	R1, ret_f
	Store		R1, b
```

**Opis:**
- Dva odvojena poziva funkcije
- Dve različite labele (lab1, lab2)
- Oba se izvršavaju sekvencijalno

**Status:** ? UNKNOWN (nije testirano)

---

## Grupa 6: Graničnih Slučajevi (Edge Cases)

### Test 6.1: Parametar Nije Korišćen u Funkciji

**Cilj:** Šta se dešava ako funkcija ignoriše parametar

**Ulaz** (`test_6_1.txt`):
```
program 
{
  f(x:int) => 42 ;
  result:int ;
  result = f(5)
}
end
```

**Očekivani Izlaz:**
```
Jump		lab0
f:
	Load_Const	R1, 42
	Store		R1, ret_f
lab0:
	Load_Const	R1, 5
	Store		R1, param_f_0
	Jump		f
lab1:
	Load_Mem	R1, ret_f
	Store		R1, result
```

**Opis:**
- Argument se čuva u param_f_0, ali funkcija ga ne koristi
- Dead code - puede se optimizovati

**Status:** ? UNKNOWN (nije testirano)

---

### Test 6.2: Varijabla Ista kao Ime Funkcije

**Cilj:** Testira naming konflikt - varijabla i funkcija isto ime

**Ulaz** (`test_6_2.txt`):
```
program 
{
  f(x:int) => x ;
  f:int ;
  f = f(5)
}
end
```

**Očekivani Izlaz:**
- Greška pri parsiranju ili nepredvidivo ponašanje

**Opis:**
- Gramatika ne sprečava ovaj konflikt
- Trebalo bi da bude greška, ali nije definisano

**Status:** ? UNKNOWN (trebalo bi testirati)

---

### Test 6.3: Suglasit Parametra i Varijable

**Cilj:** Parametar sa istim imenom kao varijabla van funkcije

**Ulaz** (`test_6_3.txt`):
```
program 
{
  f(x:int) => x ;
  x:int ;
  x = f(5)
}
end
```

**Očekivani Izlaz:**
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
	Store		R1, x
```

**Opis:**
- Unutar `f`, `x` se mapira na `param_f_0`
- U main-u, `x` se mapira direktno na `x`
- Parameter map je lokalan tokom translate()-a

**Status:** ? UNKNOWN (trebalo bi testirati)

---

## Kako Testirati

### Procedura

1. Kreiraj fajl sa imenom iz "Ulaz" sekcije
2. Kopira sadržaj iz "Ulaz" sekcije u fajl
3. Pokreni parser:
   ```bash
   java -cp "jcup_runtime.jar;." Main test_X_Y.txt
   ```
4. Provjeri `output.code` da li se poklapa sa "Očekivani Izlaz"

### Automatizovano Testiranje (PowerShell)

```powershell
# test_runner.ps1
param (
    [string]$TestDir = "."
)

$testFiles = Get-ChildItem "test_*.txt" -Path $TestDir

foreach ($file in $testFiles) {
    Write-Host "=== Testiram $($file.Name) ===" -ForegroundColor Cyan
    java -cp "jcup_runtime.jar;." Main $file.FullName
    
    if (Test-Path "output.code") {
        Write-Host "Output:" -ForegroundColor Green
        Get-Content output.code
        Write-Host ""
    } else {
        Write-Host "GREŠKA: output.code nije napravljen!" -ForegroundColor Red
    }
}
```

Korišćenje:
```powershell
powershell -ExecutionPolicy Bypass -File test_runner.ps1
```

---

## Test Status Summary

| Grupa | Test | Status | Napomene |
|-------|------|--------|----------|
| 1.1 | Prazan program | ? | Trebalo bi testirati |
| 1.2 | Jedna varijabla | ? | Trebalo bi testirati |
| 1.3 | Više varijabli | ? | Trebalo bi testirati |
| 2.1 | Identiteta funkcija | ✓ | Testiran - PASS |
| 2.2 | Više parametara | ? | Trebalo bi testirati |
| 2.3 | Bez parametara | ? | Trebalo bi testirati |
| 2.4 | Više funkcija | ? | Trebalo bi testirati |
| 3.1 | Celobrojne konstante | ? | Trebalo bi testirati |
| 3.2 | Karakterne konstante | ? | Trebalo bi testirati |
| 3.3 | Decimalne konstante | ? | Trebalo bi testirati |
| 4.1 | Varijabla kao argument | ? | Trebalo bi testirati |
| 4.2 | Direktna dodela varijable | ? | Trebalo bi testirati |
| 5.1 | Ulančane funkcije | ? | Trebalo bi testirati |
| 5.2 | Više iskaza | ? | Trebalo bi testirati |
| 6.1 | Neukorišćen parametar | ? | Trebalo bi testirati |
| 6.2 | Naming konflikt | ? | Trebalo bi testirati |
| 6.3 | Suglasit imena | ? | Trebalo bi testirati |

---

## Zaključak

Kompletan test set sa 17+ test slučajeva pokriva:
- ✅ Osnovne funkcionalnosti
- ✅ Funkcije sa različitim brojem parametara
- ✅ Različite tipove podataka
- ✅ Kompleksnije scenarije
- ✅ Edge slučajeve

Svi testovi sa statusom ✓ su već proveravani. Za testove sa ? trebalo bi da se izvrše manuelno i status se azurira.


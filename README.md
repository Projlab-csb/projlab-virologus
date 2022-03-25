# Virologus Enterprise

2022 Szoftver Projekt Laboratórium - Világtalan Virológusok Világa

## Fejlesztés

### Prettier beállítása

1. Telepítsd a prettiert, és a hozzá tartozó java plugin-t.

```
npm install -g prettier prettier-plugin-java
```

Ezután futtatható a `prettier --write "**/*.java"` parancs, ami minden .java fájlban elvégzi a formázást.

2. A prettier beállítás, hogy minden mentésnél automatikusan formázza a fájlokat.

   2.1. Az IntelliJ-ben nyitsd meg a `Preferences -> Tools -> File Watcher` ablakot. 
   2.2. Hozz létre egy új fileWatcher-t a + gombbal.
      - Name: **Prettier format**
      - File type: **Java**
      - Scope: **Project Files**
      - Program: **prettier**
      - Arguments: **--write "$FileName$"**
   
   2.3 Mentsd el a fileWatcher-t, és kész is vagy.

### SonarLint beállítása

   1. Telepítsd a SonarLint-et mint egy InteliJ plugin.
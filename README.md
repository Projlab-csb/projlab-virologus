~~# Virologus Enterprise

2022 Szoftver Projekt Laboratórium - Világtalan Virológusok Világa

## Fejlesztés

### Prettier beállítása

1. Telepítsd a prettiert, és a hozzá tartozó java plugin-t.

```
npm install -g prettier prettier-plugin-java
```

Ezután futtatható a `prettier --write "**/*.java"` parancs, ami minden .java fájlban elvégzi a formázást.

2. Telepítsd a `File Watchers` plugin-t az IntelliJ-ben.

3. A prettier beállítás, hogy minden mentésnél automatikusan formázza a fájlokat.

   3.1. Az IntelliJ-ben nyitsd meg a `Preferences -> Tools -> File Watcher` ablakot. 
   3.2. Hozz létre egy új fileWatcher-t a + gombbal.
      - Name: **Prettier format**
      - File type: **Java**
      - Scope: **Project Files**
      - Program: **prettier**
      - Arguments: **--write "$FileName$"**
      - Advanced Options: **Semmi ne legyen bepipálva**
   
   3.3 Mentsd el a fileWatcher-t, és kész is vagy.

### Statistic beállítása

   1. Telepítsd a Statistic plugin-t az InteliJ-ben.
   2. Az alsó fülek között megjelenik egy Statistic mező, az alatt megtekinthető a kommentek százalékos aránya, fileonként, valamint összesítve is.
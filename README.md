# p7m
Applicazione Spring Boot, con collegamento a DB mySql.

All'interno del progetto, dentro la cartella "resources" il file "application.properties" contiene le informazioni di collegamento a DB e l'application root context assegnato: "/check-files".
Il progetto possiede la cartella DB_dump_Script_SQL che contiene gli script SQL per la crazione dello schema e delle sue tabelle.

Il database contiene la password criptata per l'utente:
username = andrea
password = a9010

L'applicazione consente di:
- accedere alla homepage previo login;
- nella homepage è presente un bottone che invoca un servizio rest che va a fornire la validazione dei file presenti nella cartella MyFiles.
(questa cartella è presente all'interno del progetto)

Non è stato implementato il componente per il test dell'applicazione.

# road-management-system-backend

## Postawienie bazy danych

W katalogu db/

```
docker build -t postgres ./
docker run -p 5432:5432 --name postgres postgres
```

## Wygenerowanie klas modelu i uruchomienie aplikacji

W IntelliJ:

Maven -> Execute Maven Goal lub 2x nacisnąć ctrl

```
mvn clean compile
```

uruchomić MainApplication.main()

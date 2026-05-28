# Template MVP + DAO + Exceptions personnalisées

## Structure des packages

```
src/
├── models/
│   ├── daos/
│   │   ├── Dao.java              ← interface CRUD générique (ne pas modifier)
│   │   ├── MemoirDao.java        ← implémentation HashMap (ne pas modifier)
│   │   ├── MonEntiteDao.java     ← interface spécifique à l'entité
│   │   └── MonEntiteDaoImpl.java ← implémentation en mémoire
│   ├── entities/
│   │   ├── AbstractEntity.java   ← classe de base avec ID (ne pas modifier)
│   │   ├── MonEntite.java        ← entité du domaine
│   │   └── MonEntiteFactory.java ← construction + validation
│   ├── exception/
│   │   └── MonEntiteException.java ← exception personnalisée
│   └── facades/
│       ├── IModel.java           ← contrat de la couche Modèle
│       └── ModelImpl.java        ← implémentation du modèle
├── presenteur/
│   └── Presenteur.java           ← chef d'orchestre MVP
├── views/
│   ├── facades/
│   │   ├── IView.java            ← contrat de la couche Vue
│   │   └── ViewConsoleImpl.java  ← implémentation console
│   └── utils/
│       ├── AffichageConsole.java ← sorties System.out
│       └── LectureConsole.java   ← entrées Scanner
└── start/
    └── Main.java                 ← point d'entrée
```

## Pour ajouter une nouvelle entité

1. **Entité** → dupliquer `MonEntite.java`, renommer, ajouter les attributs
2. **Factory** → dupliquer `MonEntiteFactory.java`, ajouter les validations
3. **Exception** → dupliquer `MonEntiteException.java`, renommer
4. **DAO** → dupliquer `MonEntiteDao.java` + `MonEntiteDaoImpl.java`, renommer
5. **IModel** → ajouter les méthodes (ajouter, recuperer, supprimer…)
6. **ModelImpl** → instancier le nouveau DAO, implémenter les méthodes
7. **IView** → ajouter les méthodes de saisie et d'affichage
8. **ViewConsoleImpl** → implémenter les nouvelles méthodes
9. **Presenteur** → ajouter les cases dans MENU_PRINCIPAL et gestionMenu()

## Règles à respecter

### Exceptions
- Toujours utiliser des exceptions personnalisées (`XxxException extends Exception`)
- Jamais `IllegalArgumentException` ou `RuntimeException` dans le code métier
- Les méthodes qui peuvent échouer déclarent `throws XxxException`
- Le `catch` central est dans `gestionMenu()` — il affiche via `view.afficherMessage()`
- `start()` ne déclare pas `throws` — tout est géré en interne
- `main()` n'a pas de try/catch

### DAO et IDs
- Chaque entité **doit** passer par `dao.create()` pour recevoir un ID
- Sans ID, `listeProduit.contains(null)` crée de faux doublons
- Les listes de référence (ex: ingrédients d'un Prepare) ne remplacent pas le DAO

### Saisies
- `saisirChoixMenu(n)` → autorise 0 à n (0 = sortir du menu principal)
- `saisirChoixListe(n)` → autorise 1 à n (jamais 0, pas d'option sortir dans une liste)
- Dans le Présenteur : toujours `saisirChoixListe()` pour les listes d'entités

### Couches
- Le Présenteur ne connaît que `IModel` et `IView` (jamais les implémentations)
- La Vue ne connaît pas le Modèle
- Le Modèle ne connaît pas la Vue ni le Présenteur
- `Main` est le seul endroit où les trois couches sont instanciées ensemble
"# mediatheque" 

## Bienvenue sur le projet logiciel INF353

Cette page est une ébauche de fichier README pour présenter le projet logiciel INF3 du groupe.

Vos enseignants vous demandent d'utiliser cette page pour tenir le journal de groupe de votre activité de développement logiciel. 
Un journal doit être tenu **au fur et à mesure** de l'avancement du travail. Il doit comporter a minima :

* les tâches envisagées lors de chaques séances, 
* la répartition des tâches entre les membres du groupe, 
* les difficultés rencontrées lors de l'avancement du projet,
* les modalités mises en oeuvre au sein du groupe pour résoudre les difficultés

Ne vous censurez pas, vous ne serez pas évalués sur vos difficultés, mais plutôt sur votre capacité à avoir consciences de ces difficultés et votre initiative pour mettre en oeuvre des solutions. Un groupe qui n'a pas de difficultés, cela n'existe pas, cela veut juste dire qu'il n'a pas forcément conscience des difficultés auquel il fait face.

La suite est un exemple de mise en forme, pour vous donner des éléments pour rédiger votre journal en [langage MarkDown](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet).

## Première semaine
1. Petit recapitulatif de comment on  utilise gitlab 
2. Maintenant on s'amuse en modifiant le readme 
3. un dernier push ... 
 



### Objectifs de la semaine et répartition

Vous pouvez utiliser :

1. des listes numérotées, 
    * des sous listes gitlab apprendre 
    * avec différents points
1. le numéro étant géré par MarkDown
2. il suffit que ça commence par un chiffre...

### faits marquants

Le lien ci dessus donne accès à une "cheat sheet", une anti-sèche du format MarkDown.

-------




  ####deusiemesemaine 
    élaborons un plan pour le projet et divisons les tâches entre nous                                                                                                                     ####Objectifs
    creation de dictionnaire naif
    dictionnaire naif test a faire
    lecteur documents naif à faire 
    tester lecteur document naif
    Matrice index naive à faire
    tester matriceindex naive
### teste:
    
    Une partie (deux membres) du groupe prend en charge les tests.

### Troisieme semaine:

    création de lecteurDocumentNaif
    création de matriceIndexNaive
    test pour matrice indexnaive
    test pour lecteurdocumentNaif
### réunion pour traiter l'indexation 
    *résultat de la réunion:
        on à pris la décision de prendre un fichier et lui créer son propre dictionnaire (un mini dictionnaire) ,une fois tous les mini dictionnaire crées on procéde à la création d'un grand dictionnaire qui contient tous les mots de tous les ficheir ,ce grand dictionnaire sera notre repére pour la création de la matrice d'occurences
        *difficultés rencontrées:
            -comment sauvegarder le grand dictionnaire de maniére rapide et efficace
            -comment traiter les majuscules 
            -comment traiter les mots récurrents pour ne pas les avoir dans le grand dictionnair
            -Est-ce qu'on va utiliser des minidictionnaires ou bien on va juste créer un seul dictionnaire (un grand dictionnaire)
        *solutions trouvées:
            -utilisation du format binaire pour sauvegarder la matrice d'occurences qui est moins lourds et plus rapide
            -utilisation de la méthode toLowerCase() pour traiter les miniscules
            -comparaison des mots entres eux pour enlever les récurrences
            -utilisation d'un seul dictionnaire au lieu de créer plusieurs

### réunion pour traiter la recherche:
    *résultat de la réunion :
        on à décider de prendre la requête et la tranformer en matrice en utlisant matriceIndexNaive,aprés on fait un produit vectoriel pour chaque ligne de la matrice d'occurence avec même indice de ligne dans la matrice de la requête ,ça va nous permettre d'avoir un résultat qui est la pertinence de chaque document à la requête ,on trie le résultat par ordre croissant ,ça va permettre de trier la pertinence de tous les documents avec la requête.
        *difficultés rencontrées:
        -
$ java -cp $USERPROFILE/.m2/repository/fr/u_ga/inf353/1.0-SNAPSHOT/inf353-1.0-SNAPSHOT.jar inf353.Indexation  "P://Pedagogie/VALENCE/DSDA/Science/INF/353_projet/echantillon_100" "H://dos/dic.txt/" "H://dos/mat.txt/"

### quatrieme semaine et cinquieme semaine:
indexation
   -nous entamons lindexation:
   creeation dune classe cellules
   creeation de dictionnaire creux
   creeation de matrice indexcreuse
difficultés :
    temps de traitement très long   
    Il est essentiel de bien gérer le temps de traitement, en utilisant des structures creuses pour éviter de garder   en mémoire des valeurs nulles ou inutiles. Toutefois, si le corpus de documents est très volumineux, des problèmes de performance peuvent apparaître ainsi que le temps de traitement, Particulièrement pendant l'étape d'indexation.
Justification :
    Le choix de DictionnaireCreux (basé sur une table de hachage) permet d'optimiser les recherches des mots tout en maintenant une structure creuse pour économiser le temps de traitement. 

### réunion pour traiter la recherche:
difficultés:
  Calcul des pertinences : Le programme calcule la pertinence pour chaque mot de la requête dans tous les documents, ce qui peut être très coûteux en termes de temps



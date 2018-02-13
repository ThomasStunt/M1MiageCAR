Composition du groupe :

- Nicolas DESSENNE
- Thomas PERRIER

Avancement :

  L'intégralité des classes ont été réalisées. Une classe de test notamment pour le compteur séquentiel a été réalisée. Sachant que le
compteur multi thread utilise un compteur séquentiel, et que le serveur utilise un compteur multi thread pour traiter les demandes des
clients, nous n'avons pas jugé nécessaire de créer une autre classe de test.

Lancement des .jars :

- compteurSequentiel.jar :

  java -jar compteurSequentiel [phrase à analyser]
  
- compteurMultiThread.jar :

  java -jar compteurMultiThread [nombre de threads à allouer] [fichier à analyser]
  
- Client / Serveur :

  - compteurServeur.jar :
  
    java -jar compteurServeur (par défaut sur le port 7777)
    
  - fileClient.jar :
  
    java -jar fileClient 7777 [fichier à analyser]
    
  - writeClient.jar :
  
    java -jar writeClient 7777

# UML

_Durée du laboratoire: 4 périodes_

## 1.  Exercices courts

Dessiner les diagrammes de classes simplifiés (sans spécifier les
propriétés des classes mais en indiquant les héritages, les
associations et leurs cardinalités) correspondant aux situations
suivantes:

1. Une application gère les emprunts de plusieurs médiathèques. Une
   médiathèque possède des médias (livres, bandes dessinées, cassettes
   vidéo, DVDs ou CDs) empruntables par les abonnés de celle-ci.

2. Un pays est frontalier d’un certain nombre de pays.

3. Une application gère les réparations pour plusieurs garages. Un
   client demande la réparation d’un véhicule (voiture ou moto) à un
   garage. Chaque réparation est effectuée par un mécanicien et peut
   impliquer le remplacement d’une ou plusieurs pièces.

4. Les cabines d’un paquebot d’une croisière sont occupées par des
   passagers, des guides, des animateurs ou du personnel
   naviguant. Les guides et les animateurs organisent des activités
   pour les passagers (visites de sites et animations sur le
   paquebot).

    NB: Modéliser les activités avec une classe.

5. Application d'arbre généalogique. Pour chaque individu on désire
   connaître: si c’est un homme ou une femme, ses parents, ses enfants
   et la liste de ses mariages éventuels.

## 2.  Modélisations

Définir les diagrammes de classes correspondant aux situations
décrites ci-dessous.

Spécifier en français les contraintes d’intégrité qui ne peuvent pas
être exprimées sur le diagramme.

Déterminer, pour chacune des associations définies dans ces diagrammes
comment elles seraient implémentées dans un langage de programmation
OO (classes particulières et/ou attributs références, tableaux ou
listes de références dans les classes liées).

Remarques:

* Ne pas représenter au moyen d’une classe l’application elle-même
  (l’éditeur ou l’école).

* Les types suivants sont supposés exister: `boolean`, `string`, `integer`,
  `float`, `date`.

* Attention à bien factoriser les informations (pas de redondances).

### 2.1. Éditeur

Un éditeur souhaite réaliser une application pour traiter les
informations suivantes:

* un livre possède un titre et est écrit par un ou plusieurs auteurs.

* chaque livre est publié dans une ou plusieurs éditions, datées,
  possédant un prix de vente, identifiées par leur ordre (première
  édition, seconde édition, etc.) et tirées à un certain nombre
  d'exemplaires.

* les auteurs sont identifiés par leur nom et prénom. 

* les droits d’auteur perçus par un auteur lors de la vente d’un livre
  sont définis comme un pourcentage du prix de vente et sont négociés
  entre l’auteur et l’éditeur pour chaque édition.

* les librairies (caractérisées par leur nom et adresse complète)
  passent des commandes (caractérisées par leur numéro et date
  d’émission) auprès de l’éditeur d'un ou plusieurs livres.

### 2.2. École

Une école souhaite réaliser une application permettant de gérer ses
étudiants, son personnel et ses départements. Les informations
suivantes sont récoltées:

* Les étudiants, les professeurs et le personnel administratif, sont
  caractérisés par leurs nom, prénoms, date de naissance et adresse.

* Les professeurs sont rattachés à un département, caractérisé par son nom.

* Chaque département est dirigé par un doyen, choisi parmi l’ensemble
  des professeurs qui le composent.

* Les étudiants possèdent un numéro de matricule et sont inscrits dans
  une des orientations d’un département et dans un trimestre d’études
  donné (de 1 à 20).

* Les étudiants d’une même orientation et trimestre d’études sont
  rassemblés en différents groupes, caractérisés par leurs numéro.

* Les professeurs sont responsables de différentes leçons.

* Une leçon est caractérisée par sa matière, les groupes qui la
  suivent, son professeur responsable, sa salle et son horaire (jour
  de la semaine, période de début, nombre de périodes consécutives).

* Il doit être possible d’afficher les informations de toutes les
  entités de l’application.

* Il doit être possible d’obtenir le nom d’un groupe sous la forme
  <_orientation_><_trimestre d’études_>-<_n° groupe_> (par exemple,
  IL6-12).

* Il doit être possible d’obtenir l’emploi du temps d’un groupe sous
  la forme d’une chaîne de caractères.

* Il doit être possible d’obtenir l’emploi du temps d’un ensemble de
  leçons sous la forme d’une chaîne de caractères.
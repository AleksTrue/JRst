====
Todo
====

Voici la liste des t�ches qui reste � faire. Si quelqu'un souhaite aider, ou
a une id�e � proposer il ne faut pas h�siter.

- Utiliser pour le Lexer ces propres TAG et non pas ceux de ReStructuredText.java
- Mettre les noms d'attributs en CONSTANTE
- implanter la lecture des tableaux simple
- implanter la lecture des images, reference interne, 
- implanter la lecture des footnotes, citations, ...
- implanter la lecture des notes, dangers, cautions, ...
- implanter la lecture des options
- faire une feuille de style de conversion de RST en xhtml (plus rapide que dn2dbk -> dbk2xhtml)
- faire une feuille de style de conversion de xhtml -> xdoc

- La documentation utilisateur
- La documentation d�veloppeur
- faire une regle ant pour aider � la g�n�ration
  <jrst include="..." exclude="..." destination="" extension="...."/>
  destination est le r�pertoire destination
  extension est la modification de l'extension des fichiers sources avec
  cette extension

- un export vers pretion, magic, ou autre outil de presentation.

- finir l'export en rst (tableau)
- permettre l'import de d'autre format que rst, par exemple .sxw
  ce qui permettra de convertir simple du .sxw en rst.

- Faire une doclet qui permette d'ecrire les Javadocs en RST

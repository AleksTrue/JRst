===============
JRst User Guide
===============
:Author: Bucas Jf

.. Note::

   Ce logiciel est libre. R�alis� par `Code Lutin`_.

.. _Code Lutin: http://www.codelutin.com

.. contents::


Pr�sentation
============

JRst signifie Java Rst. C'est un parser en Java pour les documents 
en 'plain-text' de type RST_. Le but est de facilit� la documentation
des d�veloppements.


Ce logiciel a �t� r�alis� dans le cadre d'un stage au sein de la SSLL `Code Lutin`_.

.. image:: lutin.jpg
   :comment: lutinant !

.. _RST : http://docutils.sourceforge.net/rst.html

Installation et Ex�cution
=========================

- D�pendances

  Actuellement, seule la version Java 1.4.2 de Sun a �t� test�e.

  L'outil Maven_ de apache est utilis� pour la compilation.

  La biblioth�que java JRegex_ est n�cessaire. 

  .. _Maven : http://maven.apache.org
  .. _JRegex : http://jregex.sourceforge.net

- Compilation

  Pour lancer la compilation, tapez ``maven``. Le fichier ``jrst.jar`` est 
  construit.

- Ex�cution (exemple)::
    java -jar jrst.jar mydoc.rst --xdoc -o mydoc.xdoc

- Ligne de commande::

    usage :  jrst [--html|--xdoc|--xml|--rst] [-o outfile] document.rst

     -h, --help     this help
     --html         generate html document(default)
     --xdoc         generate xdoc document
     --xml          generate xml document
     --rst          generate with the selected format
     -o file        output file (--output)
     -v #           verbosity # in [0-3]
     document.rst   the document to parse


Fonctionnalit�s
===============

- Fonctionnalit�s impl�ment�es dans jrst :
  
  + BulletList: **Ok**.
  + Directive: Note & Contents & Image
  + FieldList: RCS � fignoler
  + GridTable: **Ok** (sauf pour deux paragraphes dans une m�me case)
  + HyperLink: **Ok**
  + Title: **Ok**.
  + OptionList: **Ok**.
  + inlineMarkup:  emphase, litteral, URL, courriel

- Ne sont pas termin�es :
  
  + Litteral: pose encore des probl�mes dans les parties indent�es
  + Comments ( � tester )
  + EnumerationList: pas de v�rification de l'ordre des Items et de la valeur
  + BlockQuote ( provoque des erreurs ) 

- Non impl�ment�es :
  
  + Annonymous Hyperlink
  + Synonymous Hyperlink
  + ...
  + Plein de d�tails � corriger
  
- Formats de sortie :
  
  + RST: fonctionne en partie, les paragraphes ne sont pas red�coup�s...
  + HTML: correct.
  + XDOC: correct.
  + XML: pas encore mis � jour
  + DOCBOOK: � faire
  + PDF: � faire
  + PS: � faire
  + OpenOffice: � faire


====
Jrst
====

But
===

JRst est un parser RestructuredText en Java.

Il permet d'ajouter des extenstions tr�s facilement en construisant deux
classe. Une pour parser l'�l�ment, l'autre qui repr�sente l'�l�ment pars�.

Principe
========

Le parseur fonctionne en deux phase, la premi�re phase construit la liste des
�l�ments pr�sents dans le document, sans tenir compte de la position des
�l�ments, c'est � dire qu'on ne s'attache pas � la structure du document. Un
titre est un titre et non pas le titre d'une section ou le titre du document.
Cette phase peut-etre assimil� au lexer.

Dans la seconde les �l�ments sont structur�s pour donner le document r�elle.
Le premier titre devient le titre du document, les titres suivant selon la
fa�on de les souligner devient des titres de section, sous-section, ...

Le lexer
--------

Un �l�ment est constitu� de deux classe, une classe qui repr�sente l'�l�ment,
et une classe qui permet de construire cette �l�ment. Le constructeur (ou
factory) contient deux m�thodes principales **accept** et **parse**. La
premi�re permet de savoir si les caract�res pourrait convenir � cette �l�ment
ou non. Si les caract�res conviennent la factory est alors utilis� pour lire
cette sous partie du document.

Soit la factory indique que l'�l�ment est termin� et donc que l'�l�ment
n'accepte plus de caract�re, soit le p�re de cette factory lui demande
l'�l�ment dans l'�tat ou il est. Par exemple lorsque l'on parse une liste,
l'�l�ment liste contient d'autre �l�ment, mais c'est l'�l�ment liste qui force
le dernier des fils d'un item � s'arr�ter. Car c'est la liste qui d�tecte le
nouveau symbole d'item et non pas le fils.

Implantation
============

Plusieurs �l�ments concret:

- title
- subtitle
- para
- directive
- bullet list (le text est un block)
- ordered list (le text est un block)
- field list (le text est un block)
- preformatting text
- table (une cellule est un block)
- definition (la definition est un block)

et des �l�ments de regroupement:

- document
- section
- block

document
--------

- commence par un titre optionnel
- des fields lists permettant de d�finir le document (auteur, date, ...)
- un block optionnel
- des sections optionnelles

section
-------

- commence par un titre
- un sous titre optionnel
- un block

block
-----

une suite d'�l�ment concret

Parsing
=======

Lorsqu'un �l�ment est pars� il peut �tre dans plusieurs �tats

IN_PROGRESS
  lorsqu'il faut d'autre caract�re pour que l'�l�ment soit valid

FINISHED
  lorsque l'�l�ment est termin� et n'accepte plus de caract�re

FAILED
  lorsque l'�l�ment a re�u un caract�re qui le rend invalide

Les �l�ments contenant d'autres �l�ments peuvent manger des caract�res au lieu
de les envoyer au sous �l�ment, ce cas arrive avec les espaces devant un
�l�ment liste qui tient sur plusieurs lignes, le block enfant de l'�l�ment
liste ne recevra pas ces espaces.

Les �l�ments contenant d'autres �l�ments peuvent bufferiser quelques caract�res
avant de prendre la d�cision de les envoyer ou non � ces sous �l�ment. Ce cas
arrive dans un block pour savoir si un �l�ment para continu ou si un �l�ment
Preformatting commence lorsque le caract�re re�u est :



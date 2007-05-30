==================================
Parseur reStructuredText : le JRst
==================================

Documentation utilisateur
=========================

.. contents:: Sommaire


Pr�sentation
------------

Le format reStructuredText est un format de description de documents. A l'image
d'autres LaTeX ou DocBook, il peut �tre d�clin� en une multitude de formats. Ces
formats souffrent habituellement d'une syntaxe envahissante qui, si elle est
n�cessaire pour des documents tr�s sp�cifiques, devient g�nante quand il s'agit
de cr�er rapidement un document pas trop complexe. RST dispose quant � lui d'une
syntaxe tellement simple qu'elle en devient presque invisible.

JRST est un parseur RST en Java permettant de cr�er une repr�sentation en arbre
d'un document. Il devient alors facile de g�n�rer une repr�sentation du document
vers diff�rents formats.


Usage
-----

Le parser JRST prend un fichier reStructuredText en entr� et g�n�re un fichier XML qui pourra ensuite servir � produire divers formats de fichiers gr�ce � des fichiers XSL de g�n�rations. Les formats de sortie disponibles sont le html, le xhtml, le rst, le pdf, le docbook, le odt (Open-Office), le rtf, ou encore le XML.

::

   JRST myfile.rst

Cette commande aura pour effet de convertir le fichier myfile.rst en XML qui sera affich� sur la sortie standard (console).
Plusieurs options sont disponibles :

-o file, --outFile=file          pour rediriger la sortie vers un fichier.
-t format, --outType format      pour pr�ciser un format de sortie, donc utiliser un fichier XSL de g�n�ration. Plusieurs formats sont disponibles xhtml, docbook, xml, html, xdoc, rst, pdf, odt, rtf.
-x xslFile, --xslFile xslFile    sert � pr�ciser le fichier xsl de g�n�ration � utiliser.
--force                          forcer l'�criture d'un fichier, si le fichier de sortie existe, il sera remplac�.
--help                           pour afficher les options disponibles :


::

   Usage: [options] FILE
      [--force] : overwrite existing out file
      [--help] : display this help and exit
      [--outFile -o value] : Output file
      [--outType -t /xhtml|docbook|xml|html|xdoc|rst/] : Output type
      [--xslFile -x value] : XSL file list to apply, comma separated


ex :

::

   JRST --force -t html -o myfile.html myfile.rst

Cette commande produira un fichier html (myfile.html) � partir du fichier reStructuredText (myfile.rst) m�me si myfile.html existe d�j�.


Plugin maven
------------

Un plugin maven est disponnible � l'adresse suivante 
http://jrst.labs.libre-entreprise.org/maven-jrst-plugin. Il permet l'utilisation 
depuis maven de JRst.

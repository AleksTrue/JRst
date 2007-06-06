===================================
Parseur reStructuredText_ : le JRst
===================================

Documentation utilisateur
=========================

.. contents:: Sommaire


Pr�sentation
------------

Le format reStructuredText_ est un format de description de documents. A l'image
d'autres LaTeX_ ou DocBook_, il peut �tre d�clin� en une multitude de formats. Ces
formats souffrent habituellement d'une syntaxe envahissante qui, si elle est
n�cessaire pour des documents tr�s sp�cifiques, devient g�nante quand il s'agit
de cr�er rapidement un document pas trop complexe. RST_ dispose quant � lui d'une
syntaxe tellement simple qu'elle en devient presque invisible.

JRST est un parseur RST_ en Java permettant de cr�er une repr�sentation en arbre
d'un document. Il devient alors facile de g�n�rer une repr�sentation du document
vers diff�rents formats.


Usage
-----

Le parser JRST prend un fichier reStructuredText_ en entr� et g�n�re un fichier XML
qui pourra ensuite servir � produire divers formats de fichiers gr�ce � des fichiers
XSL de g�n�rations. Les formats de sortie disponibles sont le html, le xhtml, le rst,
le pdf, le docbook_, le odt (Open-Office), le rtf, ou encore le XML [1]_.

::

   JRST myfile.rst

Cette commande aura pour effet de convertir le fichier myfile.rst en XML qui sera affich� sur la sortie standard (console).
Plusieurs options sont disponibles :

-o file,--outFile=file          pour rediriger la sortie vers un fichier.
-t format,--outType format      pour pr�ciser un format de sortie, donc utiliser un ou des fichiers XSL_ de g�n�ration. Plusieurs formats sont disponibles xhtml, docbook, xml, html, xdoc, rst, pdf, odt, rtf.
-x xslFile,--xslFile xslFile    sert � pr�ciser le fichier xsl de g�n�ration � utiliser.
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

Cette commande produira un fichier html (myfile.html) � partir du fichier reStructuredText_ (myfile.rst)
m�me si myfile.html existe d�j�.


Plugin Maven_
-------------

Un plugin Maven_ est disponible � l'adresse suivante 
http://jrst.labs.libre-entreprise.org/maven-jrst-plugin. Il permet l'utilisation 
depuis Maven_ de JRst.

.. [1] Seul les formats html, xhtml, DocBook_, xdoc et pdf sont disponible pour le moment.

.. _reStructuredText: presentationRST.html
.. _Maven: http://maven.apache.org/
.. _XSL: http://jrst.labs.libre-entreprise.org/fr/devel/presentationXSL.rst
.. _DocBook: http://www.docbook.org/
.. _LaTex: http://www.latex-project.org/
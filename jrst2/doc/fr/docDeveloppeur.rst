=========================
Documentation d�veloppeur
=========================

.. contents:: Sommaire

Le diagramme de Class
=====================

|classDiagramme|

La Class **AdvancedReader** a pour fonction de faciliter la lecture du fichier RST gr�ce � diff�rentes m�thodes :
  - String readLine() : renvoie une ligne
  - String[] readLines(int nombresLigne) : renvoie un certain nombre de lignes
  - Stringn[] readWhile(Pattern p) : renvoie les lignes tant qu'elles correspondent au pattern

...

La Class **JRSTLexer** utilise **AdvancedReader** pour construire un fichier XML, il va parcourir l'ensemble du document pour isoler les types de donn�es, leurs param�tres et leurs contenus, donc rassembler toutes les informations utiles � la mise en forme du XML final. Il va commencer par l'ent�te du document (peekHeader(), peekDocInfo()) pour ensuite s'int�resser au corps (peekBody()).

La Class **JRSTReader** utilise **JRSTLexer**, il interpr�te le XML qui lui est renvoy� pour construire le XML final. Celui-ci est conforme � la DTD d�finie par DocUtils_. Cette Class a parfois besoin de s'appeler elle m�me lorsque une partie du document doit �tre interpr�t�e ind�pendamment du reste. Par exemple, s'il y a une liste dans une case d'un tableau, l'on extrait les informations de la case et on les interpr�tes, le contenu d'une admoniton (une note) doit lui aussi �tre consid�r� comme un document ind�pendant. Lorsque la g�n�ration est termin�e, la Class compose le sommaire (composeContent()) puis s'occupe de toutes les sp�cificit�s � inline � (inline()), comme par exemple les mots en italique ou gras, les r�f�rences, les footnotes... Tout ce qui peut appara�tre � l'int�rieur d'une ligne.

La Class **reStructuredText** r�f�rence toutes les variables n�cessaires � la g�n�ration du XML final.

La Class **JRST** contient la m�thode main(), elle g�re les options, la lecture et l'�criture des fichiers. Elle lit le document, le parse gr�ce � la class **JRSTReader** puis applique le XSL d�sir� (si besoin) gr�ce � la class **JRSTGenerator**.

Exemple d'utilisation
=====================

L'on souhaite convertir le document rst (text.rst) suivant en html (text.html) :

::

   =====
   Titre
   =====

   :Author: Letellier Sylvain

   .. Attention:: texte � �tre r�interpr�t� comme un fichier rst ind�pendant

On utilise donc la commande suivante :

::

   JRST -t html -o text.html text.rst

Ce diagramme de s�quence d�crit le fonctionnement du parseur tout au long de la g�n�ration :

|sequanceDiagramme|

La Classe JRSTGenerator, grace au fichier XSL rst2xhtml.xsl, renvoie le fichier html suivant::

   <?xml version="1.0" encoding="UTF-8"?>
   <html xmlns="http://www.w3.org/TR/xhtml1/strict">
     <head>
       <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-15"/>
       <meta name="generator" content="JRST http://jrst.labs.libre-entreprise.org/"/>
       <title>Titre</title>
     </head>
     <body>
       <h1>Titre</h1>
       <table class="docinfo" frame="void" rules="none">
         <col class="docinfo-name"/>
         <col class="docinfo-content"/>
         <tbody valign="top">
           <tr>
             <th class="docpatterninfo-name">author :</th>
             <td class="docinfo-content">Letellier Sylvain</td>
           </tr>
         </tbody>
       </table>
       <div class="attention">
         <p class="title">attention :</p>
         <p class="body">
           <p>texte � �tre r�interpr�t� comme un fichier rst ind�pendant</p>
         </p>
       </div>
     </body>
   </html>

Qui affiche la page (un CSS � �t� ajout� pour la mise en forme) :

.. topic:: Titre

   :Author: Letellier Sylvain
   .. Attention:: texte � �tre r�interpr�t� comme un fichier rst ind�pendant


.. _DocUtils: http://docutils.sourceforge.net/docs/ref/doctree.html
.. |classDiagramme| image:: DiagrammeClass.png 
.. |sequanceDiagramme| image:: DiagrammeSequance.png
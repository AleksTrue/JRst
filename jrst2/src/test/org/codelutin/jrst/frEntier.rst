==================================
Parseur reStructuredText : le JRst
==================================

:author: Sylvain LETELLIER <letellier@codelutin>
:organization: Code Lutin
:date: $date: 01/06/2007$
:copyright: � 2007. JRST - Code Lutin - GPL

----------------------

.. sectnum::

.. contents:: Sommaire

Documentation utilisateur
=========================


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

Le parser JRST prend un fichier reStructuredText en entr� et g�n�re un fichier XML
qui pourra ensuite servir � produire divers formats de fichiers gr�ce � des fichiers
XSL de g�n�rations. Les formats de sortie disponibles sont le html, le xhtml, le rst,
le pdf, le docbook, le odt (Open-Office), le rtf, ou encore le XML [1]_.

::

   JRST myfile.rst

Cette commande aura pour effet de convertir le fichier myfile.rst en XML qui sera affich� sur la sortie standard (console).
Plusieurs options sont disponibles :

-o file,--outFile=file           pour rediriger la sortie vers un fichier.
-t format,--outType format       pour pr�ciser un format de sortie, donc utiliser un ou des fichiers XSL de g�n�ration. Plusieurs formats sont disponibles xhtml, docbook, xml, html, xdoc, rst, pdf, odt, rtf.
-x xslFile,--xslFile xslFile     sert � pr�ciser le fichier xsl de g�n�ration � utiliser.
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

Cette commande produira un fichier html (myfile.html) � partir du fichier reStructuredText (myfile.rst)
m�me si myfile.html existe d�j�.


Plugin Maven
------------

Un plugin Maven est disponible � l'adresse suivante 
http://jrst.labs.libre-entreprise.org/maven-jrst-plugin. Il permet l'utilisation 
depuis Maven de JRst.

.. [1] Seul les formats html, xhtml, DocBook, xdoc et pdf sont disponible pour le moment.

Maven : http://maven.apache.org/
XSL : http://jrst.labs.libre-entreprise.org/fr/devel/presentationXSL.rst
DocBook : http://www.docbook.org/
LaTex : http://www.latex-project.org/

Introduction � ReStructuredText
===============================

Document adapt�e du document de Richard Jones : http://docutils.sourceforge.net/sandbox/wilk/french/quickstart-fr.html


Ce texte contient des liens de la forme.  Ils sont
relatifs au manuel de r�f�rence utilisateur Quick reStructuredText.
S'ils ne fonctionnent pas, r�f�rez vous au document master quick
reference.

Quick reStructuredText : http://docutils.sourceforge.net/docs/rst/quickref.html
master quick reference : http://docutils.sourceforge.net/docs/rst/quickref.html


Structure
---------

Pour commencer, il me semble que "Structured Text" n'est pas tout � fait la
bonne appellation. Nous devrions plut�t le nommer "Relaxed Text" qui contient
quelques sch�mas logiques. Ces sch�mas sont interpr�t�s par un convertisseur
HTML pour produire "Very Structured Text" (un texte tr�s structur�) qui pourra
�tre utilis� par un navigateur web.

Le sch�ma le plus simple est le **paragraphe**.
C'est un bloc de texte s�par� par des lignes vides (une seule suffit).
Les paragraphes doivent avoir le m�me d�calage -- c'est � dire des espaces
� gauche. Ces paragraphes produiront un texte d�cal�. Par exemple::

  Ceci est un paragraphe.
  Tr�s court.

     Le texte de ce paragraphe sera d�cal�,
     g�n�ralement utilis� pour des citations.

  En voil� un autre

Le r�sultat donne :

  Ceci est un paragraphe.
  Tr�s court.

     Le texte de ce paragraphe sera d�cal�,
     g�n�ralement utilis� pour des citations.

  En voil� un autre
  

Styles de texte
---------------


Dans les paragraphes et le corps du texte, nous pouvons utiliser
des marqueurs pour *italique* avec "`` *italique* ``" ou **gras**
avec "`` **gras** ``".

Notez qu'aucun traitement suppl�mentaire n'est apport� entre deux
doubles apostrophes invers�es -- les ast�risques, comme dans "`` * ``",
sont donc conserv�es en l'�tat.

Si vous souhaitez utiliser un de ces caract�res "sp�ciaux" dans
le texte, il n'y a g�n�ralement pas de probl�me -- reStructuredText
est assez malin.
Par exemple, cet ast�risque ``*`` est trait� correctement. Si vous
souhaitez par contre ``*``entourer un texte par des ast�risques``*`` 
**sans** qu'il soit en italique, il est n�cessaire d'indiquer que
l'ast�risque ne doit pas �tre interpr�t�. Pour cela il suffit de placer
une barre oblique invers�e juste avant lui, comme �a "``\*``", ou
en l'entourant de doubles apostrophes invers�es (litteral), comme cela ::

  ``\*``

(``\*`` n'est pas implant� dans le JRST seul les `` fonctionnent)



Listes
------

Il y a trois types de listes: **num�rot�es**, **avec puces** et
de **d�finitions**. Dans chaque cas, nous pouvons avoir autant
de paragraphes, sous-listes, etc. que l'on souhaite, tant que
le d�calage � gauche est align� sur la premi�re ligne.

Les listes doivent toujours d�marrer un nouveau paragraphe
-- c'est � dire qu'il doit y avoir un saut de ligne juste avant.

Listes **num�rot�es** (par des nombres, lettres, chiffres romains;)


En d�marrant une ligne avec un num�ro ou une lettre suivie d'un
point ".", une parenth�se droite ")" ou entour� par des parenth�ses
-- comme vous pr�f�rez. Toutes ces formes sont reconnues::

    1. nombres

    A. Lettres en majuscule
       qui continue sur plusieurs ligne

       avec deux paragraphes et tout !

    a. lettres minuscules

       3. avec une sous-liste qui d�marre � un nombre diff�rent
       4. faites attention � garder une s�quence de nombre correcte !

    I. majuscules en chiffres romains

    i. minuscules en chiffres romains

    (1) des nombres � nouveau

    1) et encore

Le r�sultat (note : Tous les styles de listes ne sont pas toujours
support�s par tous les navigateurs, vous ne verrez donc pas forc�ment
les effets complets) :

1. nombres

A. Lettres en majuscule
   qui continue sur plusieurs ligne

   avec deux paragraphes et tout !

a. lettres minuscules

   3. avec une sous-liste qui d�marre � un nombre diff�rent
   4. faites attention � garder une s�quence de nombre correcte !

I. majuscules en chiffres romains

i. minuscules en chiffres romains

(1) des nombres � nouveau

1) et encore

Listes **� puces** 


De la m�me mani�re que pour les listes num�rot�es, il faut d�marrer
la premi�re ligne avec une puce -- soit "-", "+" ou "*"::

    * une puce "*"

      - une sous-liste avec "-"

         + � nouveau une sous-liste

      - une autre option

Le r�sultat:

    * une puce "*"

      - une sous-liste avec "-"

         + � nouveau une sous-liste

      - une autre option

Les listes de **d�finitions** 


Comme les deux autres, les listes de d�finitions consistent en un
terme et la d�finition de ce terme. Le format est le suivant::

    Quoi
      Les listes de d�finitions associent un terme avec une d�finition.

    *Comment*
      Le terme est une phrase d'une ligne, et la d�finition est d'un
      ou plusieurs paragraphes ou �l�ments, d�cal�s par rapport au terme.
      Les lignes vides ne sont pas autoris�es entre le terme et la d�finition.

Le r�sultat:

Quoi
  Les listes de d�finitions associent un terme avec une d�finition.

*Comment*
  Le terme est une phrase d'une ligne, et la d�finition est d'un
  ou plusieurs paragraphes ou �l�ments, d�cal�s par rapport au terme.
  Les lignes vides ne sont pas autoris�es entre le terme et la d�finition.

Pr�formatage
------------

Pour inclure un texte pr�format� sans traitement
il suffit de terminer le paragraphe par "``::``". Le texte pr�format� est
termin� lorsqu'une ligne retombe au niveau du d�calage pr�c�dent. Par exemple::

  Un exemple::

      Espaces, nouvelles lignes, lignes vides, et toutes sortes de marqueurs
         (comme *ceci* ou \cela) sont pr�serv�s dans les bloc pr�format�s.

  Fin de l'exemple

Le r�sultat:

  Un exemple::

      Espaces, nouvelles lignes, lignes vides, et toutes sortes de marqueurs
         (comme *ceci* ou \cela) sont pr�serv�s dans les bloc pr�format�s.

  Fin de l'exemple

Notez que si le paragraphe contient seulement "``::``", il est ignor�.

  ::

     Ceci est un texte pr�format�,
     le paragraphe "::" est ignor�.

Sections
--------

Pour diviser un texte en plusieurs sections, nous utilisons des
**en-t�tes de section**. C'est � dire une seule ligne de texte (d'un
ou plusieurs mots) avec un ornement : juste en dessous et �ventuellement
dessus aussi, avec des tirets "``-----``", �gal "``=====``", tildes
"``~~~~~``" ou n'importe quel de ces caract�res ``= - ` : ' " ~ ^ _ * + # < >``
qui vous semble convenir. Un ornement simplement en dessous n'a pas la
m�me signification qu'un ornement dessus-dessous avec le m�me caract�re.
Les ornements doivent avoir au moins la taille du texte. Soyez coh�rent,
les ornements identiques sont cens�s �tre du m�me niveau::

  Chapitre 1
  ==========

  Section 1.1
  -----------

  Sous-section 1.1.1
  ~~~~~~~~~~~~~~~~~~

  Section 1.2
  -----------

  Chapitre 2
  ==========

Le r�sultat de cette structure, sous la forme pseudo-XML::

    <section>
        <title>
            Chapitre 1
        <section>
            <title>
                Section 1.1
            <section>
                <title>
                    Sous-section 1.1.1
        <section>
            <title>
                Section 1.2
    <section>
        <title>
            Chapitre 2
  
(Pseudo-XML utilise une indentation et n'as pas de balises finale. Il
n'est pas possible de montrer le r�sultat, comme dans les autres exemples,
du fait que les sections ne peuvent �tre utilis�es � l'int�rieur d'un
paragraphe d�cal�. Pour un exemple concret, comparez la structure de
ce document avec le r�sultat.)

Notez que les en-t�tes de section sont utilisable comme cible de liens,
simplement en utilisant leur nom. Pour cr�er un lien sur la section Listes,
j'�cris "``Listes_``". Si le titre comporte des espaces, il est n�cessaire
d'utiliser les doubles apostrophes invers�es "```Styles de texte```".

Pour indiquer le titre du document, utilisez un style d'ornement unique
en d�but de document. Pour indiquer un sous-titre de document, utilisez
un autre ornement unique juste apr�s le titre.
Par exemple::

    =================
    Titre du document
    =================
    ----------
    Sous-titre
    ----------

    Titre de la section
    ===================

    ...

Notez que "Titre du document" et "Titre de la section" utilisent le signe
�gal, mais sont diff�rents et sans relation. Le texte et l'ornement peuvent
�tre de la m�me taille pour des questions d'esth�tisme.


Images
------

Pour inclure une image dans votre document, vous devez utiliser la directive
``image``.
Par exemple::

    .. image:: /home/letellier/PROJET/jrst2/src/site/fr/rst/user/images/biohazard.png

Le r�sultat:

.. image:: /home/letellier/PROJET/jrst2/src/site/fr/rst/user/images/biohazard.png

La partie ``images/biohazard.png`` indique le chemin d'acc�s au fichier
de l'image qui doit appara�tre. Il n'y a pas de restriction sur l'image
(format, taille etc). Si l'image doit appara�tre en HTML et que vous
souhaitez lui ajouter des informations::

  .. image:: /home/letellier/PROJET/jrst2/src/site/fr/rst/user/image/biohazard.png
     :height: 100
     :width: 200
     :scale: 50
     :alt: texte alternatif

Consultez la documentation compl�te de la directive image pour plus d'informations.

Documentation images : http://docutils.sourceforge.net/spec/rst/directives.html#images


Et ensuite ?
------------

Cette introduction montre les possibilit�s les plus courantes de reStructuredText,
mais il y en a bien d'autres � explorer. Le manuel de r�f�rence utilisateur
Quick reStructuredText est recommand� pour aller plus loin. Pour les d�tails complets
consultez reStructuredText Markup Specification [#]_.


.. [#] Si ce lien relatif ne fonctionne pas, consultez le document principal:
   http://docutils.sourceforge.net/spec/rst/reStructuredText.html.

reStructuredText Markup Specification : http://docutils.sourceforge.net/spec/rst/reStructuredText.html
Docutils-Users mailing list : http://lists.sourceforge.net/lists/listinfo/docutils-users
Docutils project web site : http://docutils.sourceforge.net/

Fonctionnalit�s propos�es
=========================

La DTD de Docutils : http://docutils.sourceforge.net/docs/ref/doctree.html


Fonctionnalit�s implant�es
--------------------------

Element racine
~~~~~~~~~~~~~~

-  document

Elements titre
~~~~~~~~~~~~~~

-  subtitle
-  title

Elements bibliographiques
~~~~~~~~~~~~~~~~~~~~~~~~~

-  docinfo
-  author
-  authors
-  organization
-  address
-  contact
-  version
-  revision
-  status
-  date
-  copyright

Elements de d�coration
~~~~~~~~~~~~~~~~~~~~~~

-  decoration
-  footer
-  header

Elements structurels
~~~~~~~~~~~~~~~~~~~~

-  section
-  topic
-  sidebar
-  transition

Elements du corps
~~~~~~~~~~~~~~~~~

-  admonition
-  attention
-  block_quote
-  bullet_list
-  caution
-  classifier
-  danger
-  definition
-  definition_list
-  definition_list_item
-  description
-  doctest_block
-  enumerated_list
-  error
-  field
-  field_body
-  field_list
-  field_name
-  footnote
-  hint
-  image
-  important
-  line
-  line_block
-  list_item
-  literal_block
-  note
-  option
-  option_argument
-  option_group
-  option_list
-  option_list_item
-  option_string
-  paragraph
-  term
-  tip
-  warning

Elements des tableaux
~~~~~~~~~~~~~~~~~~~~~

-  table
-  tbody
-  entry
-  row
-  colspec
-  thead
-  tgroup

Elements de la ligne
~~~~~~~~~~~~~~~~~~~~

-  emphasis
-  strong
-  literal
-  reference
-  footnote_reference

Fonctionnalit�s non-implant�es
------------------------------

-  abbreviation
-  acronym
-  attribution
-  caption
-  citation
-  citation_reference
-  comment
-  compound
-  container
-  figure
-  generated
-  inline
-  label
-  legend
-  pending
-  problematic
-  raw
-  rubric
-  subscript
-  substitution_definition
-  substitution_reference
-  superscript
-  system_message
-  target
-  title_reference


Documentation d�veloppeur
=========================

Le diagramme de Class
---------------------

|classDiagramme|

La Class **AdvancedReader** � pour fonction de faciliter la lecture du fichier RST gr�ce � diff�rentes m�thodes :
  - String readLine() : renvoie une ligne
  - String[] readLines(int nombresLigne) : renvoie un certain nombre de lignes
  - Stringn[] readWhile(Pattern p) : renvoie les lignes tant qu'elles correspondent au pattern

...

La Class **JRSTLexer** utilise **AdvancedReader** pour construire un fichier XML, il parcours
l'ensemble du document pour isoler les types de donn�es, leurs param�tres et leurs contenus,
donc rassembler toutes les informations utiles � la mise en forme du XML final. Il va commencer par
l'ent�te du document (peekHeader(), peekDocInfo()) pour ensuite s'int�resser au corps (peekBody()).

La Class **JRSTReader** utilise **JRSTLexer**, il interpr�te le XML qui lui est renvoy� pour construire
le XML final. Celui-ci est conforme � la DTD d�finie par DocUtils. Cette Class � parfois besoin de
s'appeler elle m�me lorsque une partie du document doit �tre interpr�t�e ind�pendamment du reste.
Par exemple, s'il y a une liste dans une case d'un tableau, l'on extrait les informations de la case
et on les interpr�tes, le contenu d'une admoniton (une note) doit lui aussi �tre consid�r� comme un
document ind�pendant. Lorsque la g�n�ration est termin�e, la Class compose le sommaire (composeContent())
puis s'occupe de toutes les sp�cificit�s � inline � (inline()), comme par exemple les mots en italique ou
gras, les r�f�rences, les footnotes... Tout ce qui peut appara�tre � l'int�rieur d'une ligne.

La Class **reStructuredText** r�f�rence toutes les variables n�cessaires � la g�n�ration du XML final.

La Class **JRST** contient la m�thode main(), elle g�re les options, la lecture et l'�criture des fichiers.
Elle lit le document, le parse gr�ce � la class **JRSTReader** puis applique le XSL d�sir� (si besoin)
gr�ce � la class **JRSTGenerator**.

La g�n�ration
-------------

|diagrammegeneration|

R�f�rence :

- xml2rst.xsl (convertion de xml de docutils vers rst) : http://www.merten-home.de/FreeSoftware/xml2rst
- dn2dbk.xsl (convertion de xml de docutils vers docbook) : http://membres.lycos.fr/ebellot/dn2dbk
- les xsl de nwalsh (convertion de docbook vers FO et xhtml) : http://nwalsh.com

- XMLmind (convertion de FO vers ODT et RTF) : http://www.xmlmind.com/foconverter/what_is_xfc.html
- FOP (convertion de FO vers PDF) : http://xmlgraphics.apache.org/fop

Exemple d'utilisation
---------------------

L'on souhaite convertir le document rst (text.rst) suivant en html (text.html) :

::

   =====
   Titre
   =====

   :Author: Letellier Sylvain

   .. Attention:: texte � �tre r�interpr�t� comme un fichier rst ind�pendant
      ceci est consid�r� comme un **paragraphe**

On utilise donc la commande suivante::

   JRST -t html -o text.html text.rst

Ce diagramme de s�quence d�crit le fonctionnement du parseur tout au long de la g�n�ration :

|sequanceDiagramme|

La Classe **JRSTGenerator**, gr�ce au fichier XSL rst2xhtml.xsl, renvoie le fichier html suivant::

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
           <p>texte � �tre r�interpr�t� comme un fichier rst ind�pendant
              ceci est consid�r� comme un <strong>paragraphe</strong></p>
         </p>
       </div>
     </body>
   </html>

Qui affiche la page (un CSS [2]_ � �t� ajout� pour la mise en forme) :

.. topic:: Titre

   :Author: Letellier Sylvain
   
   .. Attention:: texte � �tre r�interpr�t� comme un fichier rst ind�pendant
      ceci est consid�r� comme un **paragraphe**

Utilisation de XSL externe
--------------------------

JRST propose de transformer le XML de docutils avec des fichiers XSL externe.
Pour cela, il faut utiliser la commande::

  JRST -x fichierXSL, fichierXSL2 fichierRST
ou::

  JRST --xslFile fichierXSL, fichierXSL2 fichierRST

JRST traitera le fichierRST, le XML de DocUtils qui est retourn� sera transform� par la Class JRSTgenerator
en commen�ant par le fichierXSL puis par le fichierXSL2...

.. [2] Cascading Style Sheets : http://fr.wikipedia.org/wiki/Feuilles_de_style_en_cascade
.. |diagrammegeneration| image:: /home/letellier/PROJET/jrst2/src/site/fr/rst/devel/images/diagrammeGeneration.png
.. |classDiagramme| image:: /home/letellier/PROJET/jrst2/src/site/fr/rst/devel/images/diagrammeClass.png
.. |sequanceDiagramme| image:: /home/letellier/PROJET/jrst2/src/site/fr/rst/devel/images/diagrammeSequance.png

Le XSL (Extensible Stylesheets Language)
========================================

Introduction
------------

XML est un langage de structuration des donn�es, et non de repr�sentation des donn�es. Ainsi XSL
(eXtensible StyleSheet Language) est un langage recommand� par le W3C pour effectuer la repr�sentation
des donn�es de documents XML. XSL est lui-m�me d�fini avec le formalisme XML, cela signifie qu'une
feuille de style XSL est un document XML bien form�.

XSL est un langage permettant de d�finir des feuilles de style pour les documents XML au m�me titre que
les CSS (Cascading StyleSheets) pour le langage HTML ou bien DSSSL (Document Style Semantics and
Specification Language) pour le SGML. XSL est d'ailleurs inspir� de DSSSL dont il reprend beaucoup
de fonctionnalit�s et est compatible avec les CSS (il s'agit d'un sur-ensemble des CSS).

Toutefois, contrairement aux CSS, XSL permet aussi de retraiter un document XML afin d'en modifier totalement
sa structure, ce qui permet � partir d'un document XML d'�tre capable de g�n�rer d'autres types de documents
(PostScript, HTML, Tex, RTF, ...) ou bien un fichier XML de structure diff�rente.

Ainsi la structuration des donn�es (d�finie par XML) et leur repr�sentation (d�finie par un langage tel que
XSL) sont s�par�es. Cela signifie qu'il est possible � partir d'un document XML de cr�er des documents
utilisant diff�rentes repr�sentations (HTML pour cr�er des pages web, WML pour les mobiles WAP, ...).

|presentationxsl|


Structure d'un document XSL
---------------------------

Un document XSL �tant un document XML, il commence obligatoirement par la balise suivante::

   <?xml version="1.0" encoding="ISO-8859-1"?>


D'autre part, toute feuille de style XSL est comprise entre les balises <xsl:stylesheet ...> et </xsl:stylesheet>.

La balise xsl:stylesheet encapsule des balises xsl:template d�finissant les transformations � faire subir � certains
�l�ments du document XML.

::

   <?xml version="1.0" encoding="ISO-8859-1"?>
   <xsl:stylesheet
   xmlns:xsl="http://www.w3.org/TR/WD-xsl"
   xmlns="http://www.w3.org/TR/REC-html40"
   result-ns="">
      <xsl:template ... >
          <!-- traitements � effectuer -->
      </xsl:template >
   </xsl:stylesheet>


Association d'une feuille XSL � un document XML
-----------------------------------------------

Une feuille de style XSL (enregistr� dans un fichier dont l'extension est .xsl) peut �tre li�e � un document
XML (de telle mani�re � ce que le document XML utilise la feuille XSL) en ins�rant la balise suivante au d�but
du document XML::

   <?xml version="1.0" encoding="ISO-8859-1"?>
   <?xml-stylesheet href="fichier.xsl" type="text/xsl"?>


Les template rules (r�gles de gabarit)
--------------------------------------

Les template rules sont des balises XSL permettant de d�finir des op�rations � r�aliser sur certains �l�ments
du document XML utilisant la page XSL, c'est-�-dire g�n�ralement de transformer un tag XML en au moins un tag
HTML (g�n�ralement plusieurs).

Ainsi le tag XML suivant::

   <personne>
      <nom>Pillou</nom>
      <prenom>Jean-Fran�ois</prenom>
   </personne>
   <personne>
      <nom>VanHaute</nom>
      <prenom>Nico</prenom>
   </personne>
   <personne>
      <nom>Andrieu</nom>
      <prenom>Seb</prenom>
   </personne>

pourra �tre transform� en les tags HTML suivants::

   <ul>
      <li>Pillou - Jean-Fran�ois</li>
      <li>VanHaute - Nico</li>
      <li>Andrieu - Seb</li>
   </ul>

L'attribut "match" de la balise <xsl:template> permet de d�finir (gr�ce � la notation XPath) le ou les �l�ments
du document XML sur lesquels s'applique la transformation.

La notation Xpath permet de d�finir des patterns, c'est-�-dire des cha�nes de caract�res permettant de rep�rer
un noeud dans le document XML. Les principaux patterns sont :

=======  =============  =================================================================
Pattern  Exemple        Signification   
=======  =============  =================================================================
``|``    Gauche|Milieu  Indique une alternative (un noeud ou bien l'autre (ou les deux))
/        personne/nom   Chemin d'acc�s aux �l�ments (personne/bras/gauche)
*        *              Motif "joker" d�signant n'importe quel �l�ment
//       //personne     Indique tous les descendants d'un noeud
.        .              Caract�rise le noeud courant
``..``   ``..``             D�signe le noeud parent
@        @valeur        Indique un attribut caract�ristique
=======  =============  =================================================================

La transformation peut �tre r�alis�e :

- soit par ajout de texte,
- soit en d�finissant des �l�ments de transformation, c'est-�-dire des �l�ments permettant de d�finir
  des r�gles de transformation � appliquer aux �l�ments s�lectionn�s par l'attribut match 

Voici un exemple de feuille XSL permettant d'effectuer la transformation XML vers HTML d�crite ci-dessus::

   <?xml version="1.0" encoding="ISO-8859-1"?>
   <xsl:stylesheet
   xmlns:xsl="http://www.w3.org/TR/WD-xsl"
   xmlns="http://www.w3.org/TR/REC-html40"
   result-ns="">
      <xsl:template match="/">
         <HTML>
             <HEAD>
               <TITLE>Titre de la page</TITLE>
             </HEAD>
                 <BODY BGCOLOR="#FFFFFF">
                <xsl:apply-templates/>
             </BODY>
         </HTML>
      </xsl:template >
      <xsl:template match="personne" >
         <ul>
            <li>
               <xsl:value-of select="nom"/>
                 -
               <xsl:value-of select="prenom"/>
            </li>
         </ul>
      </xsl:template >
   </xsl:stylesheet>


Voici la signification de cette feuille XSL :

* Le premier tag <xsl:template> permet d'appliquer une transformation � l'ensemble du document (la valeur
  "/" de l'attribut match indique l'�l�ment racine du document XML). Ce tag contient des balises HTML qui
  seront transmises dans l'arbre r�sultat.
* L'�l�ment <xsl:apply-templates/> indique le traitement de tous les enfants directs de la racine.
* La balise <xsl:template match="personne"> permet d'aller traiter les �l�ments de type personne.
* Enfin les deux �l�ments <xsl:value-of select="nom"/> et <xsl:value-of select="prenom"/> permettent de
  recuperer les valeurs des balises nom et prenom.

R�f�rences
----------

- Article : http://www.commentcamarche.net/xml/xmlxsl.php3
- Sch�ma  : http://fr.wikipedia.org/wiki/Extended_stylesheet_language_transformations

.. |presentationxsl| image:: images/presentationXSL.png 

Les diff�rentes librairies utilis�es
====================================

dom4j
-----

Dom4j est une API Open Source Java permettant de travailler avec XML, XPath et XSLT. Cette biblioth�que
est compatible avec les standards DOM, SAX et JAXP.

javax.xml.transform et Xalan
----------------------------

javax.xml.transform et Xalan sont deux librairies permettant la transformation XSL.

SDoc
----

SDoc fournit des composants Swing qui inclut la coloration syntaxique pour de nombreux langages.

Xmlunit
-------

Xmlunit permet de comparer deux fichiers XML pour mettre en �vidences les diff�rences.

dom4j : http://www.dom4j.org

javax.xml.transform : http://java.sun.com/j2se/1.4.2/docs/api/javax/xml/transform/package-summary.html

Xalan : http://xml.apache.org/xalan-j/

SDoc : http://sdoc.sourceforge.net/wiki/pmwiki.php

Xmlunit : http://xmlunit.sourceforge.net/


Documentation externe
=====================

Le site de docUtils : http://docutils.sourceforge.net/rst.html

La DTD reStructuredText : http://docutils.sourceforge.net/docs/ref/doctree.html

Un XSL permettant de convertir le XML en RST : http://www.merten-home.de/FreeSoftware/xml2rst/

Pour la g�n�ration de la javaDoc en RST : http://java.sun.com/j2se/1.3/docs/tooldocs/javadoc/overview.html



========================================
Le XSL (Extensible Stylesheets Language)
========================================

.. contents:: Sommaire

Pr�sentation
============

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
===========================

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
===============================================

Une feuille de style XSL (enregistr� dans un fichier dont l'extension est .xsl) peut �tre li�e � un document
XML (de telle mani�re � ce que le document XML utilise la feuille XSL) en ins�rant la balise suivante au d�but
du document XML::

   <?xml version="1.0" encoding="ISO-8859-1"?>
   <?xml-stylesheet href="fichier.xsl" type="text/xsl"?>


Les template rules (r�gles de gabarit)
======================================

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
==========

- Article : http://www.commentcamarche.net/xml/xmlxsl.php3
- Sch�ma  : http://fr.wikipedia.org/wiki/Extended_stylesheet_language_transformations

.. |presentationxsl| image:: images/presentationXSL.png 
Introduction à ReStructuredText
===============================

Document adaptée du document de Richard Jones : http://docutils.sourceforge.net/sandbox/wilk/french/quickstart-fr.html

.. contents:: Sommaire


Ce texte contient des liens de la forme "(quickref__)".  Ils sont
relatifs au manuel de référence utilisateur `Quick reStructuredText`_.
S'ils ne fonctionnent pas, référez vous au document `master quick
reference`_.

__ http://docutils.sourceforge.net/docs/rst/quickref.html
.. _Quick reStructuredText: http://docutils.sourceforge.net/docs/rst/quickref.html
.. _master quick reference: http://docutils.sourceforge.net/docs/rst/quickref.html


Structure
---------

Pour commencer, il me semble que "Structured Text" n'est pas tout à fait la
bonne appellation. Nous devrions plutôt le nommer "Relaxed Text" qui contient
quelques schémas logiques. Ces schémas sont interprétés par un convertisseur
HTML pour produire "Very Structured Text" (un texte très structuré) qui pourra
être utilisé par un navigateur web.

Le schéma le plus simple est le **paragraphe** (quickref__).
C'est un bloc de texte séparé par des lignes vides (une seule suffit).
Les paragraphes doivent avoir le même décalage -- c'est à dire des espaces
à gauche. Ces paragraphes produiront un texte décalé. Par exemple::

  Ceci est un paragraphe.
  Très court.

     Le texte de ce paragraphe sera décalé,
     généralement utilisé pour des citations.

  En voilà un autre

Le résultat donne :

  Ceci est un paragraphe.
  Très court.

     Le texte de ce paragraphe sera décalé,
     généralement utilisé pour des citations.

  En voilà un autre
  
__ http://docutils.sourceforge.net/docs/rst/quickref.html#paragraphs

Styles de texte
---------------

(quickref__)

__ http://docutils.sourceforge.net/docs/rst/quickref.html#inline-markup

Dans les paragraphes et le corps du texte, nous pouvons utiliser
des marqueurs pour *italique* avec "`` *italique* ``" ou **gras**
avec "`` **gras** ``".

Notez qu'aucun traitement supplémentaire n'est apporté entre deux
doubles apostrophes inversées -- les astérisques, comme dans "`` * ``",
sont donc conservées en l'état.

Si vous souhaitez utiliser un de ces caractères "spéciaux" dans
le texte, il n'y a généralement pas de problème -- reStructuredText
est assez malin.
Par exemple, cet astérisque ``*`` est traité correctement. Si vous
souhaitez par contre ``*``entourer un texte par des astérisques``*`` 
**sans** qu'il soit en italique, il est nécessaire d'indiquer que
l'astérisque ne doit pas être interprété. Pour cela il suffit de placer
une barre oblique inversée juste avant lui, comme ça "``\*``" (quickref__), ou
en l'entourant de doubles apostrophes inversées (litteral), comme cela ::

  ``\*``

(``\*`` n'est pas implanté dans le JRST seul les `` fonctionnent)


__ http://docutils.sourceforge.net/docs/rst/quickref.html#escaping

Listes
------

Il y a trois types de listes: **numérotées**, **avec puces** et
de **définitions**. Dans chaque cas, nous pouvons avoir autant
de paragraphes, sous-listes, etc. que l'on souhaite, tant que
le décalage à gauche est aligné sur la première ligne.

Les listes doivent toujours démarrer un nouveau paragraphe
-- c'est à dire qu'il doit y avoir un saut de ligne juste avant.

Listes **numérotées** (par des nombres, lettres, chiffres romains;
quickref__)

__ http://docutils.sourceforge.net/docs/rst/quickref.html#enumerated-lists

  En démarrant une ligne avec un numéro ou une lettre suivie d'un
  point ".", une parenthèse droite ")" ou entouré par des parenthèses
  -- comme vous préférez. Toutes ces formes sont reconnues::

    1. nombres

    A. Lettres en majuscule
       qui continue sur plusieurs ligne

       avec deux paragraphes et tout !

    a. lettres minuscules

       3. avec une sous-liste qui démarre à un nombre différent
       4. faites attention à garder une séquence de nombre correcte !

    I. majuscules en chiffres romains

    i. minuscules en chiffres romains

    (1) des nombres à nouveau

    1) et encore

  Le résultat (note : Tous les styles de listes ne sont pas toujours
  supportés par tous les navigateurs, vous ne verrez donc pas forcément
  les effets complets) :

    1. nombres

    A. Lettres en majuscule
       qui continue sur plusieurs ligne

       avec deux paragraphes et tout !

    a. lettres minuscules

       3. avec une sous-liste qui démarre à un nombre différent
       4. faites attention à garder une séquence de nombre correcte !

    I. majuscules en chiffres romains

    i. minuscules en chiffres romains

    (1) des nombres à nouveau

    1) et encore

Listes **à puces** (quickref__)

__ http://docutils.sourceforge.net/docs/rst/quickref.html#bullet-lists

  De la même manière que pour les listes numérotées, il faut démarrer
  la première ligne avec une puce -- soit "-", "+" ou "*"::

    * une puce "*"

      - une sous-liste avec "-"

         + à nouveau une sous-liste

      - une autre option

  Le résultat:

    * une puce "*"

      - une sous-liste avec "-"

         + à nouveau une sous-liste

      - une autre option

Les listes de **définitions** (quickref__)

__ http://docutils.sourceforge.net/docs/rst/quickref.html#definition-lists

  Comme les deux autres, les listes de définitions consistent en un
  terme et la définition de ce terme. Le format est le suivant::

    Quoi
      Les listes de définitions associent un terme avec une définition.

    *Comment*
      Le terme est une phrase d'une ligne, et la définition est d'un
      ou plusieurs paragraphes ou éléments, décalés par rapport au terme.
      Les lignes vides ne sont pas autorisées entre le terme et la définition.

  Le résultat:

    Quoi
      Les listes de définitions associent un terme avec une définition.

    *Comment*
      Le terme est une phrase d'une ligne, et la définition est d'un
      ou plusieurs paragraphes ou éléments, décalés par rapport au terme.
      Les lignes vides ne sont pas autorisées entre le terme et la définition.

Préformatage
------------
(quickref__)

__ http://docutils.sourceforge.net/docs/rst/quickref.html#literal-blocks

Pour inclure un texte préformaté sans traitement
il suffit de terminer le paragraphe par "``::``". Le texte préformaté est
terminé lorsqu'une ligne retombe au niveau du décalage précédent. Par exemple::

  Un exemple::

      Espaces, nouvelles lignes, lignes vides, et toutes sortes de marqueurs
         (comme *ceci* ou \cela) sont préservés dans les bloc préformatés.

   Regardez ici, je suis descendu d'un niveau.
   (mais pas assez)

  Fin de l'exemple

Le résultat:

  Un exemple::

      Espaces, nouvelles lignes, lignes vides, et toutes sortes de marqueurs
         (comme *ceci* ou \cela) sont préservés dans les bloc préformatés.

   Regardez ici, je suis descendu d'un niveau.
   (mais pas assez)

  Fin de l'exemple

Notez que si le paragraphe contient seulement "``::``", il est ignoré.

  ::

     Ceci est un texte préformaté,
     le paragraphe "::" est ignoré.

Sections
--------
(quickref__)

__ http://docutils.sourceforge.net/docs/rst/quickref.html#section-structure

Pour diviser un texte en plusieurs sections, nous utilisons des
**en-têtes de section**. C'est à dire une seule ligne de texte (d'un
ou plusieurs mots) avec un ornement : juste en dessous et éventuellement
dessus aussi, avec des tirets "``-----``", égal "``=====``", tildes
"``~~~~~``" ou n'importe quel de ces caractères ``= - ` : ' " ~ ^ _ * + # < >``
qui vous semble convenir. Un ornement simplement en dessous n'a pas la
même signification qu'un ornement dessus-dessous avec le même caractère.
Les ornements doivent avoir au moins la taille du texte. Soyez cohérent,
les ornements identiques sont censés être du même niveau::

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

Le résultat de cette structure, sous la forme pseudo-XML::

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
n'est pas possible de montrer le résultat, comme dans les autres exemples,
du fait que les sections ne peuvent être utilisées à l'intérieur d'un
paragraphe décalé. Pour un exemple concret, comparez la structure de
ce document avec le résultat.)

Notez que les en-têtes de section sont utilisable comme cible de liens,
simplement en utilisant leur nom. Pour créer un lien sur la section Listes_,
j'écris "``Listes_``". Si le titre comporte des espaces, il est nécessaire
d'utiliser les doubles apostrophes inversées "```Styles de texte`_``".

Pour indiquer le titre du document, utilisez un style d'ornement unique
en début de document. Pour indiquer un sous-titre de document, utilisez
un autre ornement unique juste après le titre.
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
égal, mais sont différents et sans relation. Le texte et l'ornement peuvent
être de la même taille pour des questions d'esthétisme.


Images
------
(quickref__)

__ http://docutils.sourceforge.net/docs/rst/quickref.html#directives

Pour inclure une image dans votre document, vous devez utiliser la directive__
``image``.
Par exemple::

    .. image:: images/biohazard.png

Le résultat:

.. image:: images/biohazard.png

La partie ``images/biohazard.png`` indique le chemin d'accès au fichier
de l'image qui doit apparaître. Il n'y a pas de restriction sur l'image
(format, taille etc). Si l'image doit apparaître en HTML et que vous
souhaitez lui ajouter des informations::

  .. image:: images/biohazard.png
     :height: 100
     :width: 200
     :scale: 50
     :alt: texte alternatif

Consultez la documentation__ complète de la directive image pour plus d'informations.

__ http://docutils.sourceforge.net/spec/rst/directives.html
__ http://docutils.sourceforge.net/spec/rst/directives.html#images


Et ensuite ?
------------

Cette introduction montre les possibilités les plus courantes de reStructuredText,
mais il y en a bien d'autres à explorer. Le manuel de référence utilisateur
'Quick reStructuredText`_ est recommandé pour aller plus loin. Pour les détails complets
consultez `reStructuredText Markup Specification`_ [#]_.


.. [#] Si ce lien relatif ne fonctionne pas, consultez le document principal:
   http://docutils.sourceforge.net/spec/rst/reStructuredText.html.

.. _reStructuredText Markup Specification: http://docutils.sourceforge.net/spec/rst/reStructuredText.html
.. _poster un message: mailto:docutils-users@lists.sourceforge.net
.. _Docutils-Users mailing list: http://lists.sourceforge.net/lists/listinfo/docutils-users
.. _Docutils project web site: http://docutils.sourceforge.net/

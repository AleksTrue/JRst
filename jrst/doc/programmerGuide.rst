=======================
JRst Programmer's guide
=======================
:Author: Bucas Jf

.. Note::
   Ce logiciel est libre. R�alis� par `Code Lutin`_.

.. _Code Lutin: http://www.codelutin.com

.. contents::

Pr�sentation
============

JRst signifie Java Rst. C'est un parser en Java pour les documents 
en 'plain-text' de type RST_. Le but est de faciliter la documentation
des d�veloppements.

.. _RST : http://docutils.sourceforge.net/rst.html

Ce logiciel a �t� r�alis� dans le cadre d'un stage au sein de la SSLL `Code Lutin`_.

.. image:: lutin.jpg
   :comment: Lutinant !


Organisation G�n�rale
=====================

Hi�rarchie des classes
----------------------

Le mod�le est constitu� d'``Element`` qui peuvent chacun �tre reconnus gr�ce � leur ``ElementFactory``.
Tout les �l�ments ont en commun ``AbstractElement`` ainsi que ``AbstractFactory``. 
Certains de ces �l�ments sont sp�cialis�s dans l'indentation avec l'abstraction ``IndentedAbstractFactory``.

M�thodes communes
-----------------

Dans ``AbstractElement`` sont d�finies les m�thodes qui permettent de lancer le parsage des fils d'un �l�ment.
Les fils sont d�finis dans le fichier ``jrst.xml``.

``searchChild()`` recherche parmis les fils celui qui est capable d'``ACCEPT`` les caract�res stock�s dans
le buffer.

``delegate(int c)`` se charge de remplir et de vider le buffer ainsi que d'appeler ``searchChild`` si besoin.

Les m�thodes abstraites d�finies dans chaque �l�ment sont :

- accept : qui permet de savoir si l'�l�ment correspond (``ACCEPT``), peut correspondre (``IN_PROGRESS``), 
  ou n'est pas celui que l'on recherche (``FAILED``)
- parse : prend les caract�res les uns � la suite des autres et d�coupe chacun des �lements suivant
  leur besoin respectifs. Le parsage peut simplement s'occuper de d�l�guer aux fils.
- parseEnd : lorsque l'�l�ment se termine ou qu'un parent se termine, cette m�thode doit �tre appel�e.

Pour les �l�ments sp�cialis�s (pour l'instant, seuls les indent�s), deux m�thodes sont rajout�es :

- parseHead : identifie la t�te du bloc indent� (2 m�thodes diff�rentes : Regex ou automate)
- parseBody : le plus souvent effectue juste ``delegate`` mais peut �tre red�finie

Classes particuli�res
---------------------

Racine
******

``Document`` est la classe racine du mod�le m�moire contenant l'ensemble des donn�es du fichier pars�.
Il se contente de ``delegate`` pour la phase de parsage, mais � la fin du parsage du document il r�alise
un certain nombre de recherche dans le mod�le m�moire pour retrouver le titre, la structure (Contents),
et d'autres informations utiles.

G�n�riques
**********

- AndElement :
  Cet �l�ment sert � englober des �l�ments qui doivent �tre pars�s de s�quentiellement, c'est � dire,
  les uns apr�s les autres.
  
- OrElement :
  Cet autre �l�ment contient les enfants possibles d'un �l�ment. Chacun des enfants est potentiellement
  le bon. Il y a cependant un ordre de parcours des enfants.
  
- Para :
  le paragraphe est l'�l�ment bouffe-tout. Il est le dernier sur la liste des enfants du ``BodyElement``
  d�fini dans ``jrst.xml``. Il convient dans quasiment tout les cas, sauf quand le parsage commence par
  un espace ou un retour chariot.
  
- Term :
  un terme est l'�l�ment texte de base pour stoquer juste un mot ou un groupement de mot.

M�canismes de communication
===========================

parent vers enfants
*******************

Les parents dans le mod�le m�moire vont demander un certain nombre d'op�rations � leur enfants lors
du parsage du fichier.

- ``accept(int c)`` le parent cherche � savoir si le fils accepte le caract�re "c"
- ``parse(int c)`` le parent, apr�s acceptation, demande au fils courant de parser le caract�re "c"
- ``parseEnd(int c)`` le parent demande au fils de se terminer (le caract�re "c" est inutile)

enfant vers parents
*******************

Chaque m�thode appel�e par un parent renvoie une valeur de retour. Les possibilit�s sont contenues
dans la classe ``ParseResult`` :

- ``ACCEPT`` : sp�cifie que la m�thode ``accept(int c)`` a fonctionner et que l'�l�ment peut �tre pars�.
- ``FINISHED`` : � la fin du parsage, cette valeur est accompagn�e par le nombre de caract�res lus::
          result = ParseResult.FINISHED.setConsumedCharCount(nb_char_read);
- ``FAILED`` : si l'�l�ment ne peut accepter ou ne peut parser, une erreur est renvoy�e ainsi qu'un message::
          result = ParseResult.FAILED.setError("Mauvais caract�res, attendu x");
- ``IN_PROGRESS`` : quand tout se passe bien, que le moteur est bien huil�, c'est "en progr�s"

Parseur
=======

Param�trage
-----------

Structure du mod�le m�moire
~~~~~~~~~~~~~~~~~~~~~~~~~~~

La classe ``FactoryParser`` permet de lire le fichier ``jrst.xml`` qui contient une partie de la configuration
du parseur. Il s'agit en fait de la description du mod�le m�moire::

  <factory class="org.codelutin.jrst.DocumentFactory">
      <factory class="org.codelutin.jrst.TitleFactory" cardinality="0..1"/>
          <factory class="org.codelutin.jrst.AndElementFactory" name="StructureModel">
              <factory class="org.codelutin.jrst.OrElementFactory" name="Section" cardinality="1..*">
                  <factory class="org.codelutin.jrst.TitleFactory" cardinality="0..1"/>
                  <factory class="org.codelutin.jrst.ParaFactory"/>
              </factory>
          </factory>
      </factory>
  </factory>


Chaque factory peut d�finir des enfants qui seront contenus dans l'�l�ment XML. La cardinalit� �tablit
comment traiter l'�l�ment (0, 1, plusieurs fois). Un nom est d�finissable sur les �l�ments ``And`` et ``Or``.


D�buggage
~~~~~~~~~

Le parseur d�fini 4 niveau de d�buggage/verbosit�.
- 0 : rien, niet, nada, quedalle
- 1 : le document est affich� avec les �l�ments reconnus en vert
- 2 : les �l�ments non reconnus sont en rouge
- 3 : � d�finir...

Il est possible de sp�cifier sur la ligne de commande le niveau avec l'option ``-v``

Rajout d'�l�ment
----------------

Pour faciliter le rajout d'�l�ment dans le mod�le, des Factories-Templates sont disponibles et permettent
de se faire une id�e sur comment fonctionne grosso modo le m�canisme.

- ``Template.java`` : pour l'�l�ment � rajouter
- ``TemplateFactory.java`` : pour la factory si l'�l�ment n'est pas indent� ou
- ``TemplateIndentedFactory.java`` : pour la factory si l'�l�ment est *indent�*
- ``jrst.xml`` : il faut rajouter dans ce fichier la factory que vous d�sirer rajouter pour que 
  la `Structure du mod�le m�moire`_ puisse comporter votre �l�ment.

Il y a deux fa�ons de parser un �l�ment. La premi�re, plus fastidieuse mais plus pr�cise consiste � �crire
soit m�me son automate � �tat et ainsi r�cup�rer les informations dans l'�l�ment � parser.
La deuxi�me, plus rapide � �crire et plus concise utilise les expressions r�guli�res pour identifier puis
parser un �l�ment. Il est recommand� de d'abord regarder comment sont impl�ment�s les diff�rents �l�ments
d�j� existants. Les deux mani�res de faire sont parfois utilis�es dans un m�me �l�ment...

G�n�rateurs
===========

La g�n�ration est ce que le logiciel va nous rendre. La classe ``AbstractGenerator`` d�finit les m�thodes
de base des g�n�rateurs. La principale est ``generate(Element e)`` qui va redistribuer sur toutes 
les m�thodes ``generate(Xxxx e)`` en regardant de quelle instance est le param�tre 'e'.

- HTML : format de sortie principal, celui qui est le plus d�velopp�.

- XDOC : bas� sur HTML, presque aussi complet

- RST : le format d'entr�e peut aussi ressortir, avec quelques diff�rences, notamment les paragraphes qui
  ne sont pas d�coup�s.

- XML : reste � mettre � jour.

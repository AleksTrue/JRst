/*##%
 * Copyright (C) 2002, 2003 Code Lutin
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *##%*/

/*
 * Parser.java
 *
 * Created: 7 oct. 2003
 *
 * @author Benjamin Poussin <poussin@codelutin.com>
 * Copyright Code Lutin
 * @version $Revision$
 *
 * Mise a jour: $Date$
 * par : $Author$
 */

package org.codelutin.jrst;

import java.util.ArrayList;
import java.io.Reader;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.io.FileReader;

public abstract class Parser { // Parser

    static String TEXT = "======\ncoucou\n======\n\nun\n\ndeux\n";

    static public void main(String [] args) throws Exception {
        Reader in;
        if(args.length > 0){
            String filename = args[0];
            in = new LineNumberReader(new FileReader(filename));
            System.out.println("Lecture du fichier " + filename);
        }else{
            in = new LineNumberReader(new StringReader(TEXT));
        }

        /* D�finition de la structure d'un document */
        DocumentFactory document = new DocumentFactory();
        ElementFactory structureModel1 = new OrElementFactory("StructureModel(partie1)");
        ElementFactory structureModel = new AndElementFactory("StructureModel");
        ElementFactory topic = new AndElementFactory("topic");
        ElementFactory bodyElement = new OrElementFactory("BodyElement");
        ElementFactory title = new TitleFactory();
        ElementFactory bulletList = new BulletListFactory();
        ElementFactory fieldList = new FieldListFactory();
        ElementFactory litteral = new LitteralFactory();
        ElementFactory para = new ParaFactory();
        ElementFactory comment = new CommentFactory();
        ElementFactory hyperlink = new HyperlinkFactory();
        ElementFactory directive = new DirectiveFactory();

        document.addChild(title.getZero_Un());
        document.addChild(structureModel);

        structureModel1.addChild(topic);
        structureModel1.addChild(bodyElement);

        structureModel.addChild(structureModel1.getPlus());

        bodyElement.addChild(directive);
        bodyElement.addChild(hyperlink);
        bodyElement.addChild(comment);
        bodyElement.addChild(litteral);
        bodyElement.addChild(bulletList);
        bodyElement.addChild(fieldList);
        // mettre le paragraphe a la fin car il mange tout
        bodyElement.addChild(para);

        topic.addChild(title.getZero_Un());
        topic.addChild(title.getZero_Un());
        topic.addChild(bodyElement.getPlus());

        bulletList.addChild(bodyElement.getPlus());
        fieldList.addChild(bodyElement.getPlus());

        directive.addChild(bodyElement.getPlus());
        comment.addChild(bodyElement.getPlus());
        // fin de la d�finition


        ParseResult result = ParseResult.IN_PROGRESS;

        // on consid�re qu'avant le document il y a une ligne blanche
        int c = (int)'\n';
        while(c != -1 && ((result = document.parse(c)) == ParseResult.IN_PROGRESS)){
            c = in.read();
        }
        // apr�s le document il y a une ligne blanche
        if (c == -1) {
            result = document.parse((int)'\n');
        }

        Element e = document.getElement();
        Generator gen = new RstGenerator();
        gen.visit(e);

        if(result == ParseResult.FAILED){
            System.out.println(result.getError());
            System.out.println("buffer was:'''");
            System.out.println(document.getBuffer().toString());
            System.out.println("'''");
        }

    }

} // Parser


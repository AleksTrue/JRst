/*##%
 * Copyright (C) 2002, 2004 Code Lutin
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
 *##%**/

/*
 * OrElement.java
 *
 * Created: 18 Juin 2004
 *
 * @author Bucas Jean-Fran�ois <bucas@codelutin.com>
 * Copyright Code Lutin
 * @version $Revision$
 *
 * Mise a jour: $Date$
 * par : $Author$
 */

package org.codelutin.jrst;

public class OrElement extends AbstractElement {

    static int numbered = 0;

    String name = null;
    int id = 0;

    public OrElement() {
        id = numbered;
        numbered++;
    }

    public OrElement(String nom) {
        this();
        name = nom;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

} // OrElement


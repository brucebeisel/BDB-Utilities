/* 
 * Copyright (C) 2015 Bruce Beisel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.bdb.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JList;
import javax.swing.ListModel;


@SuppressWarnings("LeakingThisInConstructor")
public class BBList<T> extends JList<T> implements MouseListener {
    private static final long serialVersionUID = -1235537049946590222L;
    private String m_actionCommand = "";

    public BBList() {
        super();
        addMouseListener(this);
    }

    public BBList(ListModel<T> m) {
        super(m);
        addMouseListener(this);
    }

    public BBList(T[] listData) {
        super(listData);
        addMouseListener(this);
    }

    /**
     * Add an action listener
     * 
     * @param l The action listener to add
     */
    public void addActionListener(ActionListener l) {
        listenerList.add(ActionListener.class, l);
    }

    public void removeActionListener(ActionListener l) {
        listenerList.remove(ActionListener.class, l);
    }

    public void setActionCommand(String cmd) {
        m_actionCommand = cmd;
    }

    public String getActionCommand() {
        return m_actionCommand;
    }

    protected void fireActionCommand(ActionEvent e) {
        //
        // Guaranteed to return a non-null array
        //
        EventListener[] listeners = listenerList.getListeners(ActionListener.class);

        //
        // Process the listeners last to first, notifying
        // those that are interested in this event
        //
        for (EventListener listener : listeners) {
            if (e == null)
                e = new ActionEvent(this, 0, m_actionCommand);
            ((ActionListener)listener).actionPerformed(e);
        }
    }

    //
    // MouseListener Interface
    //
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            ActionEvent evt = new ActionEvent(this, 0, m_actionCommand);
            fireActionCommand(evt);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    //
    // End MouseListener Interface
    //
}
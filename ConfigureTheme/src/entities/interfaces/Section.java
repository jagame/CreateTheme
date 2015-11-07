/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.interfaces;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Javier
 */
public abstract class Section {
    protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    abstract public String getSectionName();
    
    public void addPropertyChangeSupport( PropertyChangeListener pcl ){
        pcs.addPropertyChangeListener(pcl);
    }
    
    public void removePropertyChangeSupport( PropertyChangeListener pcl ){
        pcs.removePropertyChangeListener(pcl);
    }
}

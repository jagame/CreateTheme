/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.utils;

import entities.interfaces.Section;

/**
 *
 * @author jagam
 */
public class FolderWithIconPath extends Section{
    private Folder folder;
    private String path;

    public final static String PROP_FOLDER="folder";
    public final static String PROP_PATH="path";
    
    public FolderWithIconPath() {
    }

    public FolderWithIconPath(Folder folder, String path) {
        this.folder = folder;
        this.path = path;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        Folder old = this.folder;
        this.folder = folder;
        pcs.firePropertyChange(PROP_FOLDER, old, folder);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        String old = this.path;
        this.path = path;
        pcs.firePropertyChange(PROP_PATH, old, path);
    }

    @Override
    public String getSectionName() {
        return "[Icons]";
    }
    
    @Override
    public String toString(){
        return folder.getValue()+"\n"+
                "DefaultValue="+getPath()+"\n";
    }
    
}

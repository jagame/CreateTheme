/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.sections;

import entities.interfaces.Section;
import entities.utils.Folder;
import entities.utils.FolderWithIconPath;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Javier
 */
public class Theme extends Section{
    
    private final static String sectionName = "[Theme]";
    
    private String displayName;
    private String brandImage;
    ////////////////////////////////////////////////////////////////////
    private FolderWithIconPath computer;
    private FolderWithIconPath documents;
    private FolderWithIconPath network;
    private FolderWithIconPath recyclebin;
    /*
    Es necesario cambiar el Map de iconos por variables separadas para poder hacer el binding
    (faltan getter y setter e implementar firePropertychange() en los setter)
    NOTA: despues de cambiar esto puede que distintas partes del programa dejen de funcionar, deben ser investigadas para evitar
    excepciones evitables.
    */
    
    public final static String PROP_DISPLAYNAME="displayName";
    public final static String PROP_BRANDIMAGE="brandImage";
    public final static String PROP_COMPUTER="computer";
    public final static String PROP_DOCUMENTS="documents";
    public final static String PROP_NETWORK="network";
    public final static String PROP_RECYCLEBIN="recyclebin";
    
    public Theme(){
        this.displayName = "Default";
    }
    
    public Theme(String displayName, String brandImagePath, Map<Folder,String> iconos){
        this.displayName = displayName;
        this.brandImage = brandImagePath;
        setIcons(iconos);
    }

    /**
     * Get the theme name that will show up in the Personalization Control Panel
     * @return 
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Set the theme name that will show up in the Personalization Control Panel
     * @param displayName 
     */
    public void setDisplayName(String displayName) {
        String old = this.displayName;
        this.displayName = displayName;
        pcs.firePropertyChange(PROP_DISPLAYNAME, old, displayName);
    }

    /**
     * Get the path to a branded graphic file that is incorporated in the theme preview
     * @return 
     */
    public String getBrandImage() {
        return brandImage;
    }
    
    /**
     * Set the path to a branded graphic file that is incorporated in the theme preview
     * @param brandImage 
     */
    public void setBrandImage(String brandImage) {
        String old = this.brandImage;
        this.brandImage = brandImage;
        pcs.firePropertyChange(PROP_BRANDIMAGE, old, brandImage);
    }
    
    public final void setIcons(Map<Folder,String> iconos){
        setComputer( new FolderWithIconPath(Folder.COMPUTER, iconos.get(Folder.COMPUTER)) );
        setDocuments( new FolderWithIconPath(Folder.DOCUMENTS, iconos.get(Folder.DOCUMENTS)) );
        setNetwork( new FolderWithIconPath(Folder.NETWORK, iconos.get(Folder.NETWORK)) );
        setRecyclebin( new FolderWithIconPath(Folder.RECYCLEBIN, iconos.get(Folder.RECYCLEBIN)) );
    }

    public FolderWithIconPath getComputer() {
        return computer;
    }

    public void setComputer(FolderWithIconPath computer) {
        FolderWithIconPath old = this.computer;
        this.computer = computer;
        pcs.firePropertyChange(PROP_COMPUTER, old, computer);
    }

    public FolderWithIconPath getDocuments() {
        return documents;
    }

    public void setDocuments(FolderWithIconPath documents) {
        FolderWithIconPath old = this.documents;
        this.documents = documents;
        pcs.firePropertyChange(PROP_DOCUMENTS, old, documents);
    }

    public FolderWithIconPath getNetwork() {
        return network;
    }

    public void setNetwork(FolderWithIconPath network) {
        FolderWithIconPath old = this.network;
        this.network = network;
        pcs.firePropertyChange(PROP_NETWORK, old, network);
    }

    public FolderWithIconPath getRecyclebin() {
        return recyclebin;
    }

    public void setRecyclebin(FolderWithIconPath recyclebin) {
        FolderWithIconPath old = this.recyclebin;
        this.recyclebin = recyclebin;
        pcs.firePropertyChange(PROP_RECYCLEBIN, old, recyclebin);
    }
    
    @Override
    public String getSectionName() {
        return sectionName;
    }
    
    @Override
    public String toString(){
        String res = getSectionName()+"\n"+
                (getBrandImage()==null?"":"BrandImage="+getBrandImage()+"\n")+
                (getDisplayName()==null?"":"DisplayName="+getDisplayName()+"\n")+
                (getComputer().getPath()==null?"":getComputer()+"\n")+
                (getDocuments().getPath()==null?"":getDocuments()+"\n")+
                (getNetwork().getPath()==null?"":getNetwork()+"\n")+
                (getRecyclebin().getPath()==null?"":getRecyclebin()+"\n")
                ;
        
        return res;
    }
}

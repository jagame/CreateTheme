/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.sections;

import entities.interfaces.Section;
import entities.utils.Folder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Javier
 */
public class Theme extends Section{
    
    private final static String sectionName = "[Theme]";
    
    private String displayName;
    private String brandImage;
    private Map<Folder,String> iconos;
    
    public final static String PROP_DISPLAYNAME="displayName";
    public final static String PROP_BRANDIMAGE="brandImage";
    public final static String PROP_ICONOS="iconos";
    
    public Theme(){
        this.displayName = "Default";
        this.iconos = new HashMap<>();
    }
    
    public Theme(String displayName, String brandImagePath, Map<Folder,String> iconos){
        this.displayName = displayName;
        this.brandImage = brandImagePath;
        this.iconos = iconos;
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
    
    /**
     * Get the specifies custom icons for desktop features
     * @return 
     */
    public Map<Folder, String> getIconos() {
        return iconos;
    }

    /**
     * Set the specifies custom icons for desktop features
     * @param iconos 
     */
    public void setIconos(Map<Folder, String> iconos) {
        this.iconos = iconos;
    }
    
    public void addIcono(Folder folder, String rutaIcono){
        iconos.put(folder, rutaIcono);
        pcs.firePropertyChange(PROP_ICONOS, null, iconos);
    }
    
    public void removeIcono(Folder folder){
        iconos.remove(folder);
        pcs.firePropertyChange(PROP_ICONOS, null, iconos);
    }

    @Override
    public String getSectionName() {
        return sectionName;
    }
    
    @Override
    public String toString(){
        String res = getSectionName()+"\n"+
                (getBrandImage()==null?"":"BrandImage="+getBrandImage()+"\n")+
                (getDisplayName()==null?"":"DisplayName="+getDisplayName()+"\n")
                ;
        
        res = getIconos().keySet().stream().map(
                (a) -> 
                        a.getValue()+"\n"+
                        "DefaultValue="+getIconos().get(a)+"\n"
                
                ).reduce(res, String::concat);
        
        return res;
    }
}

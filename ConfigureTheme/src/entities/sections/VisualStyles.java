/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.sections;

import entities.interfaces.Section;
import java.awt.Color;

/**
 *
 * @author Javier
 */
public class VisualStyles extends Section {
    private final static String sectionName = "[VisualStyles]";
    @Override
    public String getSectionName() {
        return sectionName;
    }
    
    private String path = "C:\\Windows\\Resources\\Themes\\aero\\aero.msstyles";
    private final String colorStyle = "NormalColor";
    private final String size = "NormalSize";
    private Color colorizationColor;
    private Boolean composition;
    private Boolean transparency;
    
    public final static String PROP_PATH="path";
    public final static String PROP_COLORSTYLE="colorStyle";
    public final static String PROP_SIZE="size";
    public final static String PROP_COLORIZATIONCOLOR="colorizationColor";
    public final static String PROP_COMPOSITION="composition";
    public final static String PROP_TRANSPARENCY="transparency";
    
    public VisualStyles(){
        
    }
    
    public VisualStyles( String path ){
        this(path, null, null, null);
    }
    
    public VisualStyles( String path, Color color, Boolean composition, Boolean transparency ){
        this.path = path;
        this.colorizationColor = color;
        this.composition = composition;
        this.transparency = transparency;
    }
    /**
     * Get the msstyles path
     * @return 
     */
    public String getPath() {
        return path;
    }
    /**
     * Set a new msstyles path
     * @param path 
     */
    public void setPath(String path) {
        String old = this.path;
        this.path = path;
        pcs.firePropertyChange(PROP_PATH, old, path);
    }
    /**
     * Get the ColorizationColor
     * @return 
     */
    public Color getColorizationColor() {
        return colorizationColor;
    }
    /**
     * Set the ColorizationColor
     * @param colorizationColor 
     */
    public void setColorizationColor(Color colorizationColor) {
        Color old = this.colorizationColor;
        this.colorizationColor = colorizationColor;
        pcs.firePropertyChange(PROP_COLORIZATIONCOLOR, old, colorizationColor);
    }
    /**
     * Return if have transparency
     * @return 
     */
    public Boolean getTransparency() {
        return transparency;
    }
    /**
     * Set if have transparency
     * @param transparency 
     */
    public void setTransparency(Boolean transparency) {
        Boolean old = this.transparency;
        this.transparency = transparency;
        pcs.firePropertyChange(PROP_TRANSPARENCY, old, transparency);
    }
    /**
     * Return composition
     * @return 
     */
    public Boolean getComposition() {
        return composition;
    }
    /**
     * Set composition
     * @param composition 
     */
    public void setComposition(Boolean composition) {
        Boolean old = this.composition;
        this.composition = composition;
        pcs.firePropertyChange(PROP_COMPOSITION, old, composition);
    }
    
    @Override
    public String toString(){
        String res = getSectionName()+"\n"+
                "Path="+getPath()+"\n"+
                "ColorStyle="+colorStyle+"\n"+
                "Size="+size+"\n"+
                (getColorizationColor()==null?"":"ColorizationColor="+getColorizationColor()+"\n")+
                (getComposition()==null?"":"Composition="+getComposition()+"\n")+
                (getTransparency()==null?"":"Transparency="+getTransparency()+"\n" );
        
        return res;
    }
    
}

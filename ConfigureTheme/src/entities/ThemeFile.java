/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.interfaces.Section;
import entities.sections.VisualStyles;
import entities.sections.ControlPanelDesktop;
import entities.sections.Slideshow;
import entities.sections.Theme;

/**
 *
 * @author Javier
 */
public class ThemeFile extends Section{
    
    private String path;
    private Theme theme;
    private ControlPanelDesktop controlPanelDesktop;
    private Slideshow slideshow;
    private VisualStyles visualStyles;
    private final static String MTSM =
            "[MasterTheSelector]\n" +
            "MTSM=DABJDKT";
    
    public final static String PROP_PATH = "path";
    public final static String PROP_THEME = "theme";
    public final static String PROP_CONTROLPANELDESKTOP = "controlPanelDesktop";
    public final static String PROP_SLIDESHOW = "slideshow";
    public final static String PROP_VISUALSTYLES = "visualStyles";

    public ThemeFile( VisualStyles vs, ControlPanelDesktop cpd ){
        this(null, null, cpd, null, vs);
    }
    
    public ThemeFile( String path, Theme theme, ControlPanelDesktop controlPanelDesktop, Slideshow slideshow, VisualStyles visualStyles ){
        this.path = path;
        this.theme = theme;
        this.controlPanelDesktop = controlPanelDesktop;
        this.slideshow = slideshow;
        this.visualStyles = visualStyles;
    }
    /**
     * Get the path of this ThemeFile
     * @return 
     */
    public String getPath(){
        return path;
    }
    /**
     * Set a new path for this ThemeFile
     * @param path 
     */
    public void setPath(String path){
        String old = this.path;
        this.path = path;
        pcs.firePropertyChange(PROP_PATH, old, path);
    }
    
    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        Theme old = this.theme;
        this.theme = theme;
        pcs.firePropertyChange(PROP_THEME, old, theme);
    }

    public ControlPanelDesktop getControlPanelDesktop() {
        return controlPanelDesktop;
    }

    public void setControlPanelDesktop(ControlPanelDesktop controlPanelDesktop) {
        ControlPanelDesktop old = this.controlPanelDesktop;
        this.controlPanelDesktop = controlPanelDesktop;
        pcs.firePropertyChange(PROP_CONTROLPANELDESKTOP, old, controlPanelDesktop);
    }

    public Slideshow getSlideshow() {
        return slideshow;
    }

    public void setSlideshow(Slideshow slideshow) {
        Slideshow old = this.slideshow;
        this.slideshow = slideshow;
        pcs.firePropertyChange(PROP_SLIDESHOW, old, slideshow);
    }

    public VisualStyles getVisualStyles() {
        return visualStyles;
    }

    public void setVisualStyles(VisualStyles visualStyles) {
        VisualStyles old = this.visualStyles;
        this.visualStyles = visualStyles;
        pcs.firePropertyChange(PROP_VISUALSTYLES, old, visualStyles);
    }
    
    @Override
    public String toString(){
        if( getControlPanelDesktop() == null || getVisualStyles()==null )
            throw new NullPointerException("ControlPanelDesktop and VisualStyles mustn't be null");
        return
                (getTheme()==null?"":getTheme()+"\n")+
                (getSlideshow()==null?"":getSlideshow()+"\n")+
                getControlPanelDesktop()+"\n"+
                getVisualStyles()+"\n"+
                MTSM;
    }

    @Override
    public String getSectionName() {
        return "";
    }
}

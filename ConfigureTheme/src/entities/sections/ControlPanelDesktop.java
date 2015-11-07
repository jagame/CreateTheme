/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.sections;

import entities.interfaces.Section;

/**
 *
 * @author Javier
 */
public class ControlPanelDesktop extends Section{

    private final static String sectionName = "[Control Panel\\Desktop]";
    
    private String wallpaper;
    private boolean tileWallpaper;
    private WallpaperStyle wallpaperStyle;
    
    
    public final static String PROP_WALLPAPER = "wallpaper";
    public final static String PROP_TILEWALLPAPER = "tileWallpaper";
    public final static String PROP_WALLPAPERSTYLE = "wallpaperStyle";
    
    /**
     * @param wallpaper The wallpaper path
     * @param tileWallpaper If the wallpaper should be tiled
     * @param wallpaperStyle The wallpaper style
     */
    public ControlPanelDesktop( String wallpaper, boolean tileWallpaper, WallpaperStyle wallpaperStyle ){
        
        if( wallpaper == null || wallpaperStyle == null )
            throw new NullPointerException("ControlPanelDesktop shouldn't have null values");
        this.tileWallpaper = tileWallpaper;
        this.wallpaper = wallpaper;
        this.wallpaperStyle = wallpaperStyle;
    }
    
    @Override
    public String getSectionName() {
        return sectionName;
    }
    
    /**
     * Get the wallpaper path
     * @return 
     */
    public String getWallpaper() {
        return wallpaper;
    }
    /**
     * Set the wallpaper path
     * @param wallpaper 
     */
    public void setWallpaper(String wallpaper) {
        String old = this.wallpaper;
        this.wallpaper = wallpaper;
        pcs.firePropertyChange(PROP_WALLPAPER, old, wallpaper);
    }
    /**
     * return if the wallpaper picture should be tiled
     * @return 
     */
    public boolean isTileWallpaper() {
        return tileWallpaper;
    }
    /**
     * Set if the wallpaper picture should be tiled
     * @param tileWallpaper 
     */
    public void setTileWallpaper(boolean tileWallpaper) {
        boolean old = this.tileWallpaper;
        this.tileWallpaper = tileWallpaper;
        pcs.firePropertyChange(PROP_TILEWALLPAPER, old, tileWallpaper);
    }
    /**
     * Get the wallpaper style
     * @return 
     */
    public WallpaperStyle getWallpaperStyle() {
        return wallpaperStyle;
    }
    /**
     * Set the wallpaper style
     * @param wallpaperStyle 
     */
    public void setWallpaperStyle(WallpaperStyle wallpaperStyle) {
        WallpaperStyle old = this.wallpaperStyle;
        this.wallpaperStyle = wallpaperStyle;
        pcs.firePropertyChange(PROP_WALLPAPERSTYLE, old, wallpaperStyle);
    }
        
    public enum WallpaperStyle{
        CENTERED(0),
        TILED(1),
        STRETCHED(2),
        RESIZED_FIT(6),
        RESIZED_CROPPED(10);
        
        private final int value;
        
        WallpaperStyle(int n){
            this.value = n;
        }
        public int getValue(){
            return this.value;
        }
        
        public static WallpaperStyle intToWallpaperStyle( int n ){
            switch(n){
                default:
                case 0:
                    return CENTERED;
                case 1:
                    return TILED;
                case 2:
                    return STRETCHED;
                case 6:
                    return RESIZED_FIT;
                case 10:
                    return RESIZED_CROPPED;                    
            }
        }
    }
    
    @Override
    public String toString(){
        String res = getSectionName()+"\n"+
                "Wallpaper="+getWallpaper()+"\n"+
                "WallpaperStyle="+getWallpaperStyle().getValue()+"\n"+
                "TileWallpaper="+(isTileWallpaper()?1:0)+"\n";
        
        return res;
    }
}

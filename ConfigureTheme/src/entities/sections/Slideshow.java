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
public class Slideshow extends Section {

    private final static String sectionName = "[Slideshow]";
    @Override
    public String getSectionName() {
        return sectionName;
    }
    
    private long interval;
    private boolean shuffle;
    private String imagesRootPath;
    
    public final static String PROP_INTERVAL="interval";
    public final static String PROP_SHUFFLE="shuffle";
    public final static String PROP_IMAGESROOTPATH="imagesRootPath";
    
    public Slideshow( long interval, boolean shuffle, String imagesRootPath ){
        this.interval = interval;
        this.shuffle = shuffle;
        if( imagesRootPath == null )
            throw new NullPointerException();
        this.imagesRootPath = imagesRootPath;
    }
    /**
     * Get a number that determines how often the background changes in milliseconds
     * @return 
     */
    public long getInterval() {
        return interval;
    }
    /**
     * Set a number that determines how often the background changes in milliseconds
     * @param interval 
     */
    public void setInterval(long interval) {
        long old = this.interval;
        this.interval = interval;
        pcs.firePropertyChange(PROP_INTERVAL, old, interval);
    }
    /**
     * Return if the background shuffles
     * @return 
     */
    public boolean isShuffle() {
        return shuffle;
    }
    /**
     * Set if the background shuffles
     * @param shuffle 
     */
    public void setShuffle(boolean shuffle) {
        boolean old = this.shuffle;
        this.shuffle = shuffle;
        pcs.firePropertyChange(PROP_SHUFFLE, old, shuffle);
    }
    /**
     * Get a path that specifies the set of images that you want to use as the background slide show
     * @return 
     */
    public String getImagesRootPath() {
        return imagesRootPath;
    }
    /**
     * Set a path that specifies the set of images that you want to use as the background slide show
     * @param imagesRootPath 
     */
    public void setImagesRootPath(String imagesRootPath) {
        String old = this.imagesRootPath;
        this.imagesRootPath = imagesRootPath;
        pcs.firePropertyChange(PROP_IMAGESROOTPATH, old, imagesRootPath);
    }
    
    @Override
    public String toString(){
        String res = getSectionName()+"\n"+
                "Interval="+getInterval()+"\n"+
                "Shuffle="+(isShuffle()?1:0)+"\n"+
                "ImagesRootPath="+getImagesRootPath()+"\n"
                ;
        
        return res;
    }
}

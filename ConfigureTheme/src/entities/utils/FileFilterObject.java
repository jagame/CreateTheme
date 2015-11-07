/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileFilter;


/**
 *
 * @author Javier
 */
public class FileFilterObject extends FileFilter {
    
    public enum Defaults {
        IMAGES(new FileFilterObject("Images","gif","jpg","png")),
        TEXT(new FileFilterObject("Text","txt")),
        SOUND(new FileFilterObject("Sound","mp3","wmp")),
        ICON(new FileFilterObject("Icons","ico")),
        VIDEO(new FileFilterObject("Video","mp4","mkv"));

        private final FileFilterObject ffo;

        Defaults( FileFilterObject ffo ){
            this.ffo = ffo;
        }

        public FileFilterObject getValue(){
            return ffo;
        }
    }
    
    private String descripción;
    private List<String> extensiones;
    
    public FileFilterObject(){
        this( "default" );
    }
    
    public FileFilterObject( String descripción, String... extensiones ){
        this.descripción = descripción;
        this.extensiones = new ArrayList<>();
        for( String s : extensiones ) {
            this.extensiones.add(s);
        }
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public List<String> getExtensiones() {
        return extensiones;
    }

    public void setExtensiones(List<String> extensiones) {
        this.extensiones = extensiones;
    }
    
    public void addExtension( String extensión ){
        this.extensiones.add(extensión);
    }

    @Override
    public boolean accept(File file) {
        String reduce = extensiones.stream().map(
                (a) -> "(\\."+a+")"
        ).reduce(
                (a,b)->(a+"|").concat(b)
        ).orElse("");
        
        final String matchFinal = reduce.isEmpty()?"":".+(" + reduce + ")$";
        
        boolean res = false;
        if( file.isDirectory() || ( file.getName().matches( matchFinal ) ) )
            res = true;
        return res;
    }

    @Override
    public String getDescription() {
        return descripción;
    }
}

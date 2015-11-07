/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;

import entities.utils.FileFilterObject;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author Javier
 */
public class ExtendedFileChooser extends JFileChooser{
    
    public ExtendedFileChooser(){
        this(null);
    }
    
    public ExtendedFileChooser( List<FileFilterObject> ffo ){
        setFileFilterList(ffo);
    }
    
    public void setFileFilterList( List<FileFilterObject> extensiones ){
        ExtendedFileChooser fc = this;
        if( extensiones != null && !extensiones.isEmpty() ) {
            if( !extensiones.isEmpty() ){
                setFileFilter( extensiones.get(0) );
                extensiones.stream().skip(1).forEach(
                        (e)->addChoosableFileFilter(e)
                );
            }
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entities.sections.ControlPanelDesktop;
import entities.sections.Slideshow;
import entities.sections.Theme;
import entities.ThemeFile;
import entities.sections.VisualStyles;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import ventana.Ventana;
import ventana.Ventana;

/**
 *
 * @author Javier
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        Theme theme = new Theme();
        Slideshow slideshow = new Slideshow(1200, true, "ruta1");
        VisualStyles vs = new VisualStyles("ruta2");
        ControlPanelDesktop cpd = new ControlPanelDesktop("ruta3", false, ControlPanelDesktop.WallpaperStyle.RESIZED_FIT);
        
        ThemeFile tf = new ThemeFile(vs,cpd);
        tf.setTheme(theme);
        tf.setSlideshow(slideshow);
        
        System.out.println(tf);
        
        try{
            OutputStream os = new FileOutputStream("C:\\Windows\\Resources\\Themes\\prueba.theme");
            OutputStreamWriter osw = new OutputStreamWriter(os);
            osw.write("");
            
            for( String str : tf.toString().split("\n") )
                osw.append(str + System.getProperty("line.separator") );
            
            osw.close();
            
        }catch(Exception ex ){
            ex.printStackTrace();
        }
        */
        new Ventana().setVisible(true);
    }
    
}

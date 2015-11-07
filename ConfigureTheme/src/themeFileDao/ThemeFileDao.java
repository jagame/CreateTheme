/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package themeFileDao;

import entities.ThemeFile;
import entities.sections.ControlPanelDesktop;
import entities.sections.Slideshow;
import entities.sections.Theme;
import entities.sections.VisualStyles;
import entities.utils.Folder;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase debe implementar los métodos necesarios para cargar y guardar objetos ThemeFile
 * @author jagam
 */
public class ThemeFileDao {
    // SOLO FALTA LA PARTE DE ESCRITURA DEL THEMEFILE
    private ThemeFileDao(){}
    
    public static void writeThemeFile( ThemeFile tf ) throws FileNotFoundException{
        try( PrintWriter pw = new PrintWriter( new File( tf.getPath() ) ) ){
            pw.print(tf);
        }
    }
    
    
    /**
     * Lee un ThemeFile a partir de una ruta dada
     * @param path
     * @return
     * @throws IOException 
     */
    public static ThemeFile readThemeFile( String path ) throws IOException{
        ThemeFile res;
        try(
                FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader( fr )
                ){
            String line;
            Theme theme = null;
            ControlPanelDesktop cpd = null;
            VisualStyles visualStyle = null;
            Slideshow slideshow = null;
            while(  (line = br.readLine()) != null ){
                // hay que repensarse esta parte, este primer while puede ser erroneo
//                while( ! line.matches("\\[.+\\]")  )
//                    line = br.readLine();
                if( line.equalsIgnoreCase("[Theme]") )
                    theme = getTheme(br);
                else if( line.equalsIgnoreCase("[Control Panel\\Desktop]") )
                    cpd = getControlPanelDesktop(br);
                else if( line.equalsIgnoreCase("[VisualStyles]") )
                    visualStyle = getVisualStyle(br);
                else if( line.equalsIgnoreCase("[Slideshow]") )
                    slideshow = getSlideshow(br);
            }
            
            res = new ThemeFile(path, theme, cpd, slideshow, visualStyle);
            
        }
        
        return res;
    }
    
    private static Slideshow getSlideshow(BufferedReader br)throws IOException{
        String line = br.readLine();
        long interval = 0;
        boolean shuffle = false;
        String imagesRootPath = null;
        while( ! line.matches("\\[.+\\]") ){
            if( line.startsWith("Interval") )
                interval = Long.parseLong( line.split("=")[1] );
            
            else if( line.startsWith("Shuffle") ){
                shuffle = Boolean.parseBoolean(line.split("=")[1]);
            }
            
            else if( line.startsWith("ImagesRootPath") )
                imagesRootPath = line.split("=")[1];
            
            
            line = br.readLine();
        }
        
        return new Slideshow(interval, shuffle, imagesRootPath);
    }
    
    private static VisualStyles getVisualStyle(BufferedReader br)throws IOException{
        String line = br.readLine();
        String path = null;
        Color colorizationColor = null;
        Boolean composition = null;
        Boolean transparency = null;
        
        while( ! line.matches("\\[.+\\]") ){
            if( line.startsWith("Path") )
                path = line.split("=")[1];
            
            else if( line.startsWith("ColorizationColor") ){
                String tmp = line.split("=")[1];
                colorizationColor = new Color(
                        Byte.parseByte(tmp.substring(4, 6)),
                        Byte.parseByte(tmp.substring(6, 8)),
                        Byte.parseByte(tmp.substring(8, 10)),
                        Byte.parseByte(tmp.substring(2, 4) ));
            }
            
            else if( line.startsWith("Composition") )
                composition = Boolean.parseBoolean( line.split("=")[1] );
            
            else if( line.startsWith("Transparency") )
                transparency = Boolean.parseBoolean( line.split("=")[1] );
            
            
            line = br.readLine();
        }
        
        return new VisualStyles(path, colorizationColor, composition, transparency);
    }
    
    private static ControlPanelDesktop getControlPanelDesktop( BufferedReader br )throws IOException{
        String line = br.readLine();
        String wallpaper = null;
        boolean tileWallpaper = false;
        ControlPanelDesktop.WallpaperStyle wallpaperStyle = null;
        while( ! line.matches("\\[.+\\]") ){
            if( line.startsWith("WallpaperStyle") )
                wallpaperStyle =
                        ControlPanelDesktop.WallpaperStyle
                        .intToWallpaperStyle(
                                Integer.parseInt( line.split("=")[1] )
                        );
            
            else if( line.startsWith("TileWallpaper") )
                tileWallpaper = Boolean.getBoolean( line.split("=")[1] );
            
            else if( line.startsWith("Wallpaper") )
                wallpaper = line.split("=")[1];            
            
            line = br.readLine();
        }
        return new ControlPanelDesktop(wallpaper, tileWallpaper, wallpaperStyle);
    }
    
    private static Theme getTheme( BufferedReader br ) throws IOException{
        String line = br.readLine();
        String displayName = null;
        String brandImage = null;
        Map<Folder,String> icons = new HashMap();
        while( ! line.matches("\\[.+\\]") || line.matches("\\[CLSID.+") ){
            if( line.startsWith("DisplayName") ){
                displayName = line.split("=")[1];
                line = br.readLine();
            }    
            else if( line.startsWith("BrandImage") ){
                brandImage = line.split("=")[1];
                line = br.readLine();
            }
            else if( line.matches("\\[CLSID\\.+") )
                line = readIconSection(icons, line, br);
            else
                line = br.readLine();
        }
        
        return new Theme(displayName, brandImage, icons);
    }
    
    /**
     * Añade a un map la entrada correspondiente de la sección recorrida
     * @param folder
     * @param icons
     * @param line
     * @param br
     * @throws IOException 
     */
    private static String readIconSection( Map<Folder,String> icons, String line, BufferedReader br ) throws IOException{
        Folder folder = Folder.stringToFolder(line);
        br.readLine();
        while( ! line.matches("\\[.+\\]") ){
            if( line.startsWith("DefaultValue") )
                icons.put( folder, line.split("=")[1] );
            line = br.readLine();
        }
        return line;
    }
}

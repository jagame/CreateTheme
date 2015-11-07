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
            String line = br.readLine();
            Theme theme = null;
            ControlPanelDesktop cpd = new ControlPanelDesktop("", false, ControlPanelDesktop.WallpaperStyle.CENTERED);
            VisualStyles visualStyle = new VisualStyles();
            Slideshow slideshow = null;
            while( line != null ){
                // El problema radica en que todo método que avance en las lineas de br deve devolver la línea por donde se ha quedado,
                // aun cuando este diseñado para devolver otro tipo de objeto.
                if( line.equalsIgnoreCase("[Theme]") )
                    line = getTheme(br, (theme=new Theme()));
                else if( line.equalsIgnoreCase("[Control Panel\\Desktop]") )
                    line = getControlPanelDesktop(br, cpd);
                else if( line.equalsIgnoreCase("[VisualStyles]") )
                    line = getVisualStyle(br, visualStyle);
                else if( line.equalsIgnoreCase("[Slideshow]") )
                    line = getSlideshow(br, (slideshow=new Slideshow(0, false, "")));
                else
                    line = br.readLine();
            }
            
            res = new ThemeFile(path, theme, cpd, slideshow, visualStyle);
            
        }
        
        return res;
    }
    
    private static String getSlideshow(BufferedReader br, Slideshow sli)throws IOException{
        String line = br.readLine();
        long interval = 0;
        boolean shuffle = false;
        String imagesRootPath = null;
        while( line!=null && ! line.matches("\\[.+\\]") ){
            if( line.startsWith("Interval") )
                interval = Long.parseLong( line.split("=")[1] );
            
            else if( line.startsWith("Shuffle") ){
                shuffle = Boolean.parseBoolean(line.split("=")[1]);
            }
            
            else if( line.startsWith("ImagesRootPath") )
                imagesRootPath = line.split("=")[1];
            
            
            line = br.readLine();
        }
        sli.setInterval(interval);
        sli.setShuffle(shuffle);
        sli.setImagesRootPath(imagesRootPath);
        
        return line;
    }
    
    private static String getVisualStyle(BufferedReader br, VisualStyles vs)throws IOException{
        String line = br.readLine();
        String path = null;
        Color colorizationColor = null;
        boolean composition = false;
        boolean transparency = false;
        
        while( ! line.matches("\\[.+\\]") ){
            if( line.startsWith("Path") )
                path = line.split("=")[1];
            
            else if( line.startsWith("ColorizationColor") ){
                String tmp = line.split("=")[1];
                colorizationColor = new Color(
                        // En el fichero de texto es hexadecimal, por lo que habrá que convertirlo a decimal
                        Integer.parseInt(tmp.substring(4, 6),16),
                        Integer.parseInt(tmp.substring(6, 8),16),
                        Integer.parseInt(tmp.substring(8, 10),16),
                        Integer.parseInt(tmp.substring(2, 4),16));
            }
            
            else if( line.startsWith("Composition") )
                composition = Boolean.parseBoolean( line.split("=")[1] );
            
            else if( line.startsWith("Transparency") )
                transparency = Boolean.parseBoolean( line.split("=")[1] );
            
            
            line = br.readLine();
        }
        vs.setPath(path);
        vs.setColorizationColor(colorizationColor);
        vs.setComposition(composition);
        vs.setTransparency(transparency);
        return line;
    }
    
    private static String getControlPanelDesktop( BufferedReader br, ControlPanelDesktop cpd )throws IOException{
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
        cpd.setWallpaper(wallpaper);
        cpd.setTileWallpaper(tileWallpaper);
        cpd.setWallpaperStyle(wallpaperStyle);
        return line;
    }
    
    private static String getTheme( BufferedReader br, Theme theme ) throws IOException{
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
            else if( line.matches("\\[CLSID.+") )
                line = readIconSection(icons, line, br);
            else
                line = br.readLine();
        }
        theme.setDisplayName(displayName);
        theme.setBrandImage(brandImage);
        theme.setIcons(icons);
        return line;
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
        line = br.readLine();
        while( ! line.matches("\\[.+\\]") ){
            if( line.startsWith("DefaultValue") )
                icons.put( folder, line.split("=")[1] );
            line = br.readLine();
        }
        return line;
    }
}

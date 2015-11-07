/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.utils;

/**
 *
 * @author Javier
 */
public enum Folder{
    COMPUTER("{20D04FE0-3AEA-1069-A2D8-08002B30309D}"),
    DOCUMENTS("{59031A47-3F72-44A7-89C5-5595FE6B30EE}"),
    NETWORK("{F02C1A0D-BE21-4350-88B0-7367FC96EF3C}"),
    RECYCLEBIN("{645FF040-5081-101B-9F08-00AA002F954E}");

    private final String value;
        
    Folder(String value){
        this.value = value;
    }
        
    public String getValue(){
        return "[CLSID\\"+value+"\\DefaultIcon]";
    }
    
    public static Folder stringToFolder(String str){
        switch(str){
            case "{20D04FE0-3AEA-1069-A2D8-08002B30309D}":
                return COMPUTER;
            case "{59031A47-3F72-44A7-89C5-5595FE6B30EE}":
                return DOCUMENTS;
            case "{F02C1A0D-BE21-4350-88B0-7367FC96EF3C}":
                return NETWORK;
            case "{645FF040-5081-101B-9F08-00AA002F954E}":
                return RECYCLEBIN;
            default:
                return null;
        }
    }
    
}

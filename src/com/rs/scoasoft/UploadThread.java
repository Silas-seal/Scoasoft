/*
 * The class to upload photos to the application
 */
package com.rs.scoasoft;

/**
 *
 * @author Remote Station
 */
public class UploadThread extends Thread {
    
    private String fro,to;
    
    /**
     * Sets the data to the valuables
     * @param fro
     * @param to 
     */
    public void setData(String fro, String to) {
        
        this.fro=fro;
        this.to=to;
        
    }
    
    @Override
    public void run() {
        
        Factory factory=new Factory();
        
        factory.copyFile(fro, to);
        
    }
    
}

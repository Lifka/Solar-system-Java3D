/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaSolar;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.media.j3d.Material;

/**
 *
 * @author jgallardo
 */
public class Planeta extends Astro{
    Estrella estrella;
    HashMap<String,Satelite> satelites;
    
    public Planeta(String nombre, double radio, double distancia, String archivo_textura, Material material, Color c, double rotacion, double traslacion, Estrella estrella, HashMap<String,Satelite> satelites) {
        super(nombre, radio, distancia, archivo_textura, material, c, rotacion, traslacion );
        this.estrella = estrella;
        this.satelites = satelites;
    }
    public Planeta(String nombre, double radio, double distancia, Estrella estrella, HashMap<String,Satelite> satelites){
        super(nombre, radio, distancia);
        this.estrella = estrella;
        this.satelites = satelites;
    }
    public Planeta(String nombre, double radio, double distancia){
        super(nombre, radio, distancia);
        this.estrella = null;
        this.satelites = new HashMap<String,Satelite>();
    }
    
    public void addSatelite(Satelite s){
        satelites.put(s.getNombre(),s);
        s.setPlaneta(this);
    }
    
    public boolean hasSatelites(){
        return satelites.size() > 0;
    }
    
    public int getNumSatelites(){
        return satelites.size();
    }
    
    public HashMap<String, Satelite> getSatelites(){
        return satelites;
    }
    
    public ArrayList<Satelite> getSatelitesArray(){
        ArrayList<Satelite> sats = new ArrayList<Satelite>();
        
        for (Satelite value : satelites.values()) {
            sats.add(value);
        }
        
        return sats;
    }
    
    public Estrella getEstrella(){
        return estrella;
    }
    
    public void setEstrella(Estrella estrella){
        this.estrella = estrella;
    }
   
    
}

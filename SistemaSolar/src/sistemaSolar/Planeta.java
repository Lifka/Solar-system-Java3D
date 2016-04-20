/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaSolar;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.TransformGroup;

/**
 *
 * @author jgallardo
 */
public class Planeta extends Astro{
    Estrella estrella;
    HashMap<String,Satelite> satelites = new HashMap();
    ArrayList<Anillo> anillos = new ArrayList();
    
    public Planeta(String nombre, float radio, float distancia, String archivo_textura, 
            Material material, double rotacion, double traslacion, 
            Estrella estrella, HashMap<String,Satelite> satelites, float radio_false,
            float distancia_false, float rotacion_false, float traslacion_false) {
        
        super(nombre, radio, distancia, archivo_textura, material, rotacion, traslacion,
                radio_false, distancia_false, rotacion_false, traslacion_false);
        
        this.estrella = estrella;
        if (satelites != null)
            this.satelites = satelites;            
    }
    public Planeta(String nombre, float radio, float distancia, Estrella estrella, 
            HashMap<String,Satelite> satelites){
        super(nombre, radio, distancia);
        this.estrella = estrella;
        if (satelites != null)
            this.satelites = satelites;      
    }
    public Planeta(String nombre, float radio, float distancia){
        super(nombre, radio, distancia);
        this.estrella = null;
        this.satelites = new HashMap();
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
    
    public void addAnillo(Anillo a){
        anillos.add(a);
    }
    
    public boolean hasAnillos(){
        return (anillos.size() > 0);
    }
    
    public Estrella getEstrella(){
        return estrella;
    }
    
    public void setEstrella(Estrella estrella){
        this.estrella = estrella;
    }
    
    
    @Override
    public void makeTransform(Canvas3D canvas){
        TransformGroup inclinacion = getInclinationTransform();
        TransformGroup rota = getRotartransform(rotacion_false);
        TransformGroup distance = getDistanceTransform();
        TransformGroup traslada = getRotartransform(traslacion_false);
        putOrbit();
        rota.addChild(esfera);
        
        for (Anillo a : anillos){
            inclinacion.addChild(a);
        }
        
        for (Satelite value : satelites.values()) {
            value.makeTransform(canvas);
            rota.addChild(value);
        }
                
        distance.addChild(rota);
        distance.addChild(inclinacion);
        traslada.addChild(distance);
        addChild(traslada);
    }
    
    
    public void putOrbit(){
        OrbitCircle orbita = new OrbitCircle(nombre, getDistancia(), material, Color.white);
        addChild(orbita);
    }
   
    
}

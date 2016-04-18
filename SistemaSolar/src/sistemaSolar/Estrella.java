/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import java.awt.Color;
import java.util.ArrayList;
import javax.media.j3d.Material;

public class Estrella extends Astro{
    
    private double luminosidad;
    private ArrayList<Planeta> planetas;
    
    public Estrella(String nombre, float radio, String archivo_textura, Material material, Color c, double rotacion, double luminosidad) {
        super(nombre, radio, 0, archivo_textura, material, c, rotacion, 0.0);
        this.luminosidad = luminosidad;
    }
    
    public Estrella (String nombre, float radio, double luminosidad){
        super(nombre,radio,0);
        this.luminosidad = luminosidad;
    }
  
    public void setLuminosidad(double luninosidad){
        this.luminosidad = luminosidad;
    }
    
    public void addPlaneta(Planeta p){
        planetas.add(p);
    }
    
    public void removePlaneta(Planeta p){
        planetas.remove(p);
    }
    
    public int getNumPlanetas(){
        return planetas.size();
    }
    
}

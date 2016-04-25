/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import java.util.ArrayList;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.TransformGroup;

public class Estrella extends Astro{
    
    private ArrayList<Planeta> planetas;
    
    public Estrella(String nombre, float radio, String archivo_textura, Material material, 
            double rotacion, float radio_false,
            float distancia_false, float rotacion_false, float traslacion_false) {
        
        super(nombre, radio, 0, archivo_textura, material, rotacion, 0.0, 
                radio_false, distancia_false, rotacion_false, traslacion_false);
    }
    
    public Estrella (String nombre, float radio){
        super(nombre,radio,0);
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
    
    @Override
    public void makeTransform(Canvas3D canvas){
        setTimerRot((long)rotacion_false);
        TransformGroup rota = getRotartransform(this.timer_rotacion,1);
        
        rota.addChild(esfera);
        
        addChild(rota);
    }
    
}

/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import java.awt.Color;
import java.util.ArrayList;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.TransformGroup;

public class Estrella extends Astro{
    
    private double luminosidad;
    private ArrayList<Planeta> planetas;
    
    public Estrella(String nombre, float radio, String archivo_textura, Material material, 
            double rotacion, double luminosidad, float radio_false,
            float distancia_false, float rotacion_false, float traslacion_false) {
        
        super(nombre, radio, 0, archivo_textura, material, rotacion, 0.0, 
                radio_false, distancia_false, rotacion_false, traslacion_false);
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
    
    
    
    @Override
    public void makeTransform(Canvas3D canvas){
        TransformGroup rota = getRotartransform(rotacion_false, 1);
        TransformGroup distance = getDistanceTransform();
        TransformGroup traslada = getRotartransform(traslacion_false, 1);
        TransformGroup pick = new TransformGroup();
        
        rota.addChild(esfera);
        
        // PICK
        PickForStop ps = new PickForStop(canvas);
        ps.stopTransform(this);
        pick.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        pick.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        pick.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
        pick.setCapability(Node.ENABLE_PICK_REPORTING);
        pick.addChild(ps);
        // END PICK
        
        distance.addChild(rota);
        traslada.addChild(distance);
        pick.addChild(traslada);
        addChild(pick);
    }
    
}

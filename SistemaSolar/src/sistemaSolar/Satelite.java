/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.TransformGroup;


public class Satelite extends Astro {
    private Planeta planeta;
    
    public Satelite(String nombre, float radio, float distancia, 
            String archivo_textura, Material material, 
            double t_rotacion, double t_traslacion, float radio_false,
            float distancia_false, float rotacion_false, float traslacion_false) {
        
        super(nombre, radio, distancia, archivo_textura, material, t_rotacion, 
                t_traslacion, radio_false, distancia_false, rotacion_false, traslacion_false);
        this.planeta = null;
    }
    public Satelite(String nombre, float radio, float distancia){
        super(nombre, radio, distancia);
        this.planeta = null;
    }
    
    public void setPlaneta(Planeta p){
        this.planeta = p;
    }
    
    
    @Override
    public void makeTransform(Canvas3D canvas){
        setTimerRot((long)rotacion_false);
        TransformGroup rota = getRotartransform(this.timer_rotacion, 1);
        TransformGroup distance = getDistanceTransform();
        setTimerTras((long)traslacion_false);
        TransformGroup traslada = getRotartransform(this.timer_traslacion, 1);
        rota.addChild(esfera);
                
        distance.addChild(rota);
        traslada.addChild(distance);
        addChild(traslada);
    }
   
    
}

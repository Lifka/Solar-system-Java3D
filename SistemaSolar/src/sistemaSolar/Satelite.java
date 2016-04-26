/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;


public class Satelite extends Astro {
    private Planeta planeta;
    private Boolean has_view = false;
    private View view;
    
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
    
    public void setView(View vista){
        has_view = true;
        view = vista;
    }
    
    
    @Override
    public void makeTransform(){
        setTimerRot((long)rotacion_false);
        TransformGroup rota = getRotartransform(this.timer_rotacion, 1);
        TransformGroup distance = getDistanceTransform();
        setTimerTras((long)traslacion_false);
        TransformGroup traslada = getRotartransform(this.timer_traslacion, 1);
       
        
        rota.addChild(esfera); // 
                
        distance.addChild(rota); // Rotar alrededor planeta
        traslada.addChild(distance); // Separar del planeta
        
        if(has_view){
            
            Transform3D transformLook = new Transform3D ();
            transformLook.lookAt(new Point3d(-1, 0, 0), new Point3d (0, 0, 0),
                    new Vector3d(0, 1, 0));

            transformLook.invert();

            TransformGroup tgCamara = new TransformGroup(transformLook);
        
        
            ViewPlatform vpPlanta = new ViewPlatform();
            tgCamara.addChild(vpPlanta);
            traslada.addChild(tgCamara);
            view.attachViewPlatform(vpPlanta);
        }
            
        addChild(traslada); // Rota sobre su eje
    }
   
    
}

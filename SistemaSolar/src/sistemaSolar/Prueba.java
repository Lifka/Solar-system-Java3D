/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.Sphere;
import java.awt.BorderLayout;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setSize(10000, 10000);
        Visualization visualizationWindows = new Visualization(canvas);
        
        Universo universe = new Universo(new String(), canvas);
        
        SimpleUniverse simpleUniverse = universe.createUniverso();
       
        visualizationWindows.setVisible(true);
        
        
        
        // TODO
        BranchGroup raiz = new BranchGroup();
        
         Sphere sphere = new Sphere(0.5f);
        raiz.addChild(sphere);
     
        
        Color3f color = new Color3f(4.0f, -7.0f, -12.0f);
        Vector3f direction = new Vector3f(5, -6, -10);
        DirectionalLight light = new DirectionalLight(color, direction);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0), 100.0);
        light.setInfluencingBounds(bounds);
        raiz.addChild(light);
        
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();
       
        simpleUniverse.addBranchGraph(raiz);
        
       

        
       // Branch de planetas
       /* BranchGroup planetas = new BranchGroup();
        
        // Enganchar los planetas al universo
        simpleUniverse.addBranchGraph(planetas);
        
        /*************/
        /* PLANETAS */
        /***********/
        
        /* Mercurio */
        
        // Mercurio es transformable
      /*  TransformGroup conjuntoMercurio = new TransformGroup ();
        conjuntoMercurio.setCapability(TransformGroup .ALLOW_TRANSFORM_WRITE);
        
        // Enganchar conjuntoMercurio del branch de planetas
        planetas.addChild(conjuntoMercurio);
        //conjuntoMercurio.setTransform(Transformacion);
        
        /* Venus */
        
        // Venus es transformable
     /*   TransformGroup conjuntoVenus = new TransformGroup ();
        conjuntoMercurio.setCapability(TransformGroup .ALLOW_TRANSFORM_WRITE);
        
        // Enganchar conjuntoVenus del branch de planetas
        planetas.addChild(conjuntoVenus);
        //conjuntoVenus.setTransform(Transformacion);
        
        /* Tierra */
        
        // Tierra es transformable
   /*     TransformGroup conjuntoTierra = new TransformGroup ();
        conjuntoMercurio.setCapability(TransformGroup .ALLOW_TRANSFORM_WRITE);
        
        // Enganchar conjuntoTierra del branch de planetas
        planetas.addChild(conjuntoTierra);
        //conjuntoTierra.setTransform(Transformacion);
        */
        
      
      
    }
    
}

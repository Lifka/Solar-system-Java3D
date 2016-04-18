/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.Sphere;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.WindowConstants;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // *************** CANVAS
        // Crear canvas (pantalla)
        Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        // Tamaño canvas
        canvas.setSize(10000, 10000);
        // Ventana
        Visualization visualizationWindows = new Visualization(canvas);
        visualizationWindows.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
             
        // *************** UNIVERSE
        // Crear Simple Universe
        Universo universe = new Universo(new String(), canvas);
        // Sale de nuestra propia clase
        SimpleUniverse simpleUniverse = universe.createUniverso();
        // Crear los astros
        universe.crearSistemaSolar();
        HashMap<String,Astro> astros = universe.getAstros();
        ArrayList<Astro> astros_array = universe.getAstrosArray();
        
        // Hacemos visible la ventana
        visualizationWindows.setVisible(true);
        
        // Creamos el árbol
        BranchGroup raiz = new BranchGroup();
        
        // Fondo
        BranchGroup background = universe.createBackground();
        
        // *************** SOL
        // Definimos el objeto
        // Material:
        Material m = new Material();
        m.setEmissiveColor(255, 100, 80);
        m.setAmbientColor(255,100,80);
        // Lo creamos
        Astro sol = astros.get("sol");

        // Transformaciones del objeto (van en un transform group)
        Transform3D t3d = new Transform3D();
        // grupoArotar --> conjunto de elementos que se rotan juntos
        TransformGroup grupoArotar = new TransformGroup(t3d);
        // Capabilities
        grupoArotar.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        grupoArotar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        // Le damos la capacidad de rotar con el teclado
        RotarAstro comportamiento = new RotarAstro(grupoArotar);
        BoundingSphere bounds = new BoundingSphere();
        comportamiento.setSchedulingBounds(bounds);
        
        // Añadimos en el mismo nodo el objeto y el comportamiento
        grupoArotar.addChild(comportamiento);
        grupoArotar.addChild(sol);
        
        // Lo añadimos a la raiz
        raiz.addChild(grupoArotar);
        
        // *************** PLANETAS
        for(int i = 0; i < astros_array.size(); i++){
            
            Astro astro = astros_array.get(i);
            if (astro instanceof Planeta){
                System.out.println(((Planeta) astro).nombre);
                // Material
                Material mat = new Material();

                // Características del material
                // ------------

                // Transformaciones del objeto (van en un transform group)
                Transform3D transform = new Transform3D();
                transform.set(new Vector3f(astro.getDistancia(),0.0f,0.0f));
                TransformGroup grupo_rotar = new TransformGroup(transform);

                // Capabilities
                grupo_rotar.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
                grupo_rotar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

                // Le damos la capacidad de rotar con el teclado
                RotarAstro comportamient = new RotarAstro(grupo_rotar);
                BoundingSphere bound = new BoundingSphere();
                comportamient.setSchedulingBounds(bound);
                

                // Añadimos en el mismo nodo el objeto y el comportamiento
                grupo_rotar.addChild(comportamient);
                grupo_rotar.addChild(astro);
                raiz.addChild(grupo_rotar);
            }
            
            
        }
        
        
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();
        
        simpleUniverse.addBranchGraph(background);
        simpleUniverse.addBranchGraph(raiz);
       
        /*universe.crearSistemaSolar();
        universe.crearSistemaSolar();
        ArrayList<Astro> astros = universe.getAstrosArray();
        
        BranchGroup raiz = new BranchGroup();
        
        for (Astro a : astros)
            raiz.addChild(a);
     
        // hacemos la pantalla visible
        visualizationWindows.setVisible(true);
     
        
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

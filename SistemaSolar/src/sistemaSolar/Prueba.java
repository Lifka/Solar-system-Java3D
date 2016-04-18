/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.universe.SimpleUniverse;
import java.util.ArrayList;
import java.util.HashMap;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.WindowConstants;

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
        BoundingSphere bounds = new BoundingSphere();
        
        // Añadimos en el mismo nodo el objeto y el comportamiento
        grupoArotar.addChild(sol);
        
        // Lo añadimos a la raiz
        raiz.addChild(grupoArotar);
        
        // *************** PLANETAS
        for(int i = 0; i < astros_array.size(); i++){
            
            Astro astro = astros_array.get(i);
            if (astro instanceof Planeta){
                System.out.println(((Planeta) astro).nombre);
                System.out.println(((Planeta) astro).distancia);
                // Material
                Material mat = new Material();

                // Características del material
                // ------------
                
                raiz.addChild(astro);
                
            }
            
            
        }
        
        
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();
        
        simpleUniverse.addBranchGraph(background);
        simpleUniverse.addBranchGraph(raiz);
       
    }
    
}

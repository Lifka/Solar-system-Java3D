/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.WindowConstants;
import javax.vecmath.Color3f;

public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // *************** CANVAS
        // Crear canvas (pantalla)
        Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        Canvas3D canvas2 = new Canvas3D(SimpleUniverse.getPreferredConfiguration());

        // Tamaño canvas
        canvas.setSize(10000, 10000);
        canvas2.setSize(10000,10000);
        
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
        Astro sol = astros.get("sol");
        sol.makeTransform(canvas);

        // Transformaciones del objeto (van en un transform group)
        Transform3D t3d = new Transform3D();
        // grupoArotar --> conjunto de elementos que se rotan juntos
        TransformGroup grupoArotar = new TransformGroup(t3d);
        // Capabilities
        grupoArotar.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        grupoArotar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        // Le damos la capacidad de rotar con el teclado
        BoundingSphere bounds = new BoundingSphere();
        
        // Añadimos la luz puntual al sol
        LuzPuntual lp = new LuzPuntual(new Color3f(Color.WHITE));
        grupoArotar.addChild(lp);
        
        // Añadimos en el mismo nodo el objeto y el comportamiento
        grupoArotar.addChild(sol);
        
        // Lo añadimos a la raiz
        raiz.addChild(grupoArotar);
        
        // *************** PLANETAS
        for(int i = 0; i < astros_array.size(); i++){
            Astro astro = astros_array.get(i);
            if (astro instanceof Planeta){
                // TRANSFORMS
                astro.makeTransform(canvas);
                
                // AÑADIR ASTRO A LA RAIZ
                raiz.addChild(astro);
            }
        }
        
        ((Planeta)astros.get("tierra")).cloudEnabled(true, "src/texturas_planetas/cloud.png");
        
        
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();
        
       raiz.addChild(background);
       raiz.addChild(new Nave().getNaveBranch());
       raiz.compile();
       simpleUniverse.addBranchGraph(raiz);
       
    }
    
}

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
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

public class sistemaSolarMain {
    
    public static void main(String[] args) {
        
        // *************** CANVAS
        // Crear canvas
        Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        Canvas3D canvas2 = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        
          
        // *************** UNIVERSE
        // Crear Simple Universe
        Universo universe = new Universo();
        // Sale de nuestra propia clase
        universe.createUniverso();
        
        
        
        
        //****************CAMARAS****************
        
       
       Camaras camaras = new Camaras();
       
       universe.addView(camaras.getViewPlanta());// 0
       universe.addView(camaras.getViewPerspective(canvas2));// 1
       
       View viewMoon = camaras.getNewView();// 2
       View viewNave = camaras.getNewView();// 3
       
        
        
        //*********************************

        
        
        // Crear los astros
        universe.crearSistemaSolar();
        HashMap<String,Astro> astros = universe.getAstros();
        ArrayList<Astro> astros_array = universe.getAstrosArray();
        
        // Asignar vista a Luna
        ((Satelite)astros.get("luna")).setView(viewMoon);

        
        // Creamos el árbol
        BranchGroup raiz = new BranchGroup();
        
        // Fondo
        BranchGroup background = universe.createBackground();
        background.setPickable(false);
        
        // *************** SOL
        Astro sol = astros.get("sol");
        sol.makeTransform();

        // Transformaciones del objeto (van en un transform group)
        Transform3D t3d = new Transform3D();
        // grupoArotar --> conjunto de elementos que se rotan juntos
        TransformGroup grupoArotar = new TransformGroup(t3d);
        // Capabilities
        grupoArotar.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        grupoArotar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        
        // Añadimos la luz puntual al sol
        LuzPuntual lp = new LuzPuntual(new Color3f(Color.WHITE));
        lp.setPickable(false);
        
        grupoArotar.addChild(lp);
        
        // Añadimos en el mismo nodo el objeto y el comportamiento
        grupoArotar.setPickable(false);
        grupoArotar.addChild(sol);
        
        // Lo añadimos a la raiz
        raiz.addChild(grupoArotar);
        
        // *************** PLANETAS
        for(int i = 0; i < astros_array.size(); i++){
            Astro astro = astros_array.get(i);
            if (astro instanceof Planeta){
                // TRANSFORMS
                astro.makeTransform();
                
                // AÑADIR ASTRO A LA RAIZ
                raiz.addChild(astro);
            }
        }
        
        ((Planeta)astros.get("tierra")).cloudEnabled(true, "src/texturas_planetas/cloud.png");
        

       universe.simpleUniverse.getViewingPlatform().setNominalViewingTransform();

       raiz.addChild(background);
       
       // *************** NAVE
       Nave nave = new Nave();
       // Cámara nave:
       nave.setView(viewNave);
       TransformGroup tg_nave = nave.getNaveBranch();
       tg_nave.setPickable(false);
       raiz.addChild(tg_nave);
       
       PickForStop ps = new PickForStop(canvas,raiz);
       ps.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100));
       raiz.addChild(ps);
       
       PickForStop ps2 = new PickForStop(canvas2,raiz);
       ps2.setSchedulingBounds(new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 100));
       raiz.addChild(ps2);
       
       universe.locale.addBranchGraph(raiz);

       
       
       Gui windows1 = new Gui(canvas, "Planta fija", Gui.PLANTA, camaras);
       Gui windows2 = new Gui(canvas2, "Vistas", Gui.VISTAS, camaras);
    }
}

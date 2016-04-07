/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class Universo {
    private Nave nave;
    private HashMap<String,Astro> astros;
    private Texture textura;
    private Canvas3D aCanvas;
    

    public Universo (String archivo_text,Canvas3D aCanvas){
//        this.textura = new TextureLoader(archivo_text, null).getTexture();
        this.nave = null;
        this.astros = new HashMap<String,Astro>();
        this.aCanvas = aCanvas;
    }
    
    public int getNumComponentes(){
        int total = astros.size();
        if (nave != null)
            total++;
        return total;
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public HashMap<String,Astro> getAstros() {
        return astros;
    }

    public void setAstros(HashMap<String,Astro> astros) {
        this.astros = astros;
    }
    
    public void addAstro(Astro ast){
        astros.put(ast.getNombre(),ast);
    }
    
    public int numAstros(){
        return astros.size();
    }
    
    public void crearSistemaSolar(){
        // ESTRELLAS --> SOL
        String dir_text_sol = "texturas_estrellas/";
        Astro sol = new Estrella("Sol", 696342, 0.0, dir_text_sol + "sol.jpg", new Material(), Color.white, 26, 10);
        astros.put(sol.getNombre(),sol);
        
        // SATÉLITES
        String dir_text_sat = "texturas_satelites/";
        
        Astro luna = new Satelite("Luna", 1737, 370300, dir_text_sat + "luna.jpg", new Material(),Color.white, 27.32, 27.32);
        astros.put(luna.getNombre(), luna);
        
        Astro fobos = new Satelite("Fobos", 11, 9380, dir_text_sat + "fobos.jpg", new Material(), Color.white, 0.3 , 0.3);
        astros.put(fobos.getNombre(), fobos);
        
        Astro deimos = new Satelite("Deimos",7,23460, dir_text_sat + "deimos.jpg", new Material(), Color.white, 1.25,1.25); 
        astros.put(deimos.getNombre(), deimos);
        
        Astro io = new Satelite("Io",1820,422000, dir_text_sat + "io.jpg", new Material(), Color.white, 1.75,1.75);
        astros.put(io.getNombre(),io);
    }

    
    
    
    public SimpleUniverse createUniverso(){

        
        /***********    VIEWINGPLATFORM   **********/
        
        // Creamos lo que hace que se vean las cosas, manualmente, para poder asignarlo
        // y personalizarlo
        ViewingPlatform viewingPlatform = new ViewingPlatform ();
        // Radio de activación
        viewingPlatform.getViewPlatform().setActivationRadius(100f);
        
        // Transformación de vista -> dónde se está , a dónde se mira , Vup
        TransformGroup viewTransformGroup = viewingPlatform.getViewPlatformTransform();
        Transform3D viewTransform3D = new Transform3D ( );
        viewTransform3D.lookAt (new Point3d (20 ,20 ,20 ),
            new Point3d (0, 0, 0), new Vector3d (0 ,1 ,0));
        
        viewTransform3D.invert();
        viewTransformGroup .setTransform(viewTransform3D);
        
        // Mover la cámara con el ratón
        OrbitBehavior orbit = new OrbitBehavior (aCanvas, OrbitBehavior .REVERSE_ALL);
        BoundingSphere bound = new BoundingSphere (new Point3d (0.0f, 0.0f , 0.0f) , 100.0f);
        orbit.setSchedulingBounds(bound);
        
        orbit.setZoomFactor(2.0f);
        
        viewingPlatform. setViewPlatformBehavior(orbit);
    
        
        /**************   VIEWER     **************/
        
        Viewer viewer = new Viewer(aCanvas);
        View view = viewer.getView();
        view.setFieldOfView(Math.toRadians(45));
        view.setBackClipDistance(50.0);
        
        return new SimpleUniverse(viewingPlatform, viewer);

    }

}
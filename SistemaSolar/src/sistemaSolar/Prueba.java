/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.TransformGroup;

public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        Visualization visualizationWindows = new Visualization(canvas);
        
        Universo universe = new Universo(new String(), canvas);
        //universe.getViewingPlatform().setNominalViewingTransform();
        
        Appearance apariencia = new Appearance();
        
      
      
    }
    
}

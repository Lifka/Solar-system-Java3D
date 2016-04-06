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

public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        Visualization visualizationWindows = new Visualization(canvas);
        
        SimpleUniverse universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
        
        Appearance apariencia = new Appearance();
        apariencia.setPolygonAttributes(new PolygonAttributes(
                PolygonAttributes.POLYGON_LINE,
                PolygonAttributes.CULL_BACK,1.5f
        ));
        
        Sphere esfera = new Sphere(0.7f, apariencia);
        
        BranchGroup root = new BranchGroup();
        root.addChild(esfera);
        universe.addBranchGraph(root);
        visualizationWindows.setVisible(true);
    }
    
}

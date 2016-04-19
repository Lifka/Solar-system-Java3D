/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

public class Nave {
    
    private TransformGroup tg = new TransformGroup();
    
    public Nave(){
    }
    
    
    public TransformGroup getNaveBranch(){
        Scene modelo = null; 
        ObjectFile archivo = new ObjectFile (ObjectFile.RESIZE | ObjectFile.STRIPIFY | ObjectFile.TRIANGULATE );
        try {
          modelo = archivo.load ("src/nave/FA-22_Raptor.obj");
        } catch (FileNotFoundException e) {
          System.err.println (e);
          System.exit(1);
        } catch (ParsingErrorException e) {
          System.err.println (e);
          System.exit(1);
        } catch (IncorrectFormatException e) {
          System.err.println (e);
          System.exit(1);
        }
        
        TransformGroup mov = getRun();
        mov.addChild(modelo.getSceneGroup());
        
        
        return mov;
  
   }
    
    
   public TransformGroup getRun(){
        Transform3D yAxis = new Transform3D();
        TransformGroup tg = new TransformGroup(yAxis);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha timer = new Alpha(-1,Alpha.INCREASING_ENABLE, 0, 0, (long)200000/5, 0, 0 ,0, 0, 0);
        RotationInterpolator  rot_interpolator = new RotationInterpolator(timer, tg, yAxis, 0.0f, (float) Math.PI*2.0f);
        BoundingSphere bounds = new BoundingSphere();
        rot_interpolator.setSchedulingBounds(bounds);
        
        
        // añadimos al TransformGroup la animación y la figura
        tg.addChild(rot_interpolator);
        return tg;
   } 
}

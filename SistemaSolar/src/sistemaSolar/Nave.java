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
import javax.media.j3d.RotPosScalePathInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;

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
        
        Transform3D transform = new Transform3D();
        transform.rotX(5);
        transform.setScale(0.2);
        TransformGroup inclinacion = new TransformGroup(transform);
        inclinacion.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	inclinacion.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        TransformGroup mov = getMovimientoTransform();
        inclinacion.addChild(modelo.getSceneGroup());
        mov.addChild(inclinacion);
        
        mov.setPickable(false);
        
        return mov;
   }

    public TransformGroup getMovimientoTransform(){
        float scale_tam = 0.5f;
        float[] scale = {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
        
        float[] alphas = {0.0f, 0.15f, 0.18f, 0.30f, 0.31f,0.6f,0.61f,0.7f ,0.73f,0.85f,0.86f, 1.0f};
        
        Point3f [] positions = {
                new Point3f(15.0f*scale_tam, 0.0f, 0.0f), 
                new Point3f(20.0f*scale_tam, 5.0f*scale_tam, 0.0f),
                new Point3f(20.0f*scale_tam, 5.0f*scale_tam, 0.0f),  // giro
                new Point3f(15.0f*scale_tam, 0.0f, -4.0f*scale_tam),
                new Point3f(15.0f*scale_tam, 0.0f, -4.0f*scale_tam), // giro
                new Point3f(-10.0f*scale_tam, 0.0f, -4.0f*scale_tam),
                new Point3f(-10.0f*scale_tam, 0.0f, -4.0f*scale_tam), // giro
                new Point3f(-10.0f*scale_tam, 0.0f, 10.0f*scale_tam), 
                new Point3f(-10.0f*scale_tam, 0.0f, 10.0f*scale_tam), //giro
                new Point3f(15.0f*scale_tam, 0.0f, 10.0f*scale_tam),
                new Point3f(15.0f*scale_tam, 0.0f, 10.0f*scale_tam), //giro
                new Point3f(15.0f*scale_tam, 0.0f, 0.0f) 
        } ;
        
        Quat4f[] rotations = new Quat4f [12];
        for(int i = 0; i < 12; i++)
            rotations[i] = new Quat4f();
        
        rotations[0].set(new AxisAngle4f(0.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[1].set(new AxisAngle4f(1.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[2].set(new AxisAngle4f(-1.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[3].set(new AxisAngle4f(0.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[4].set(new AxisAngle4f(0.0f, 1.0f, 0.0f, (float)Math.toRadians(90)));
        rotations[5].set(new AxisAngle4f(0.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[6].set(new AxisAngle4f(0.0f, 1.0f, 0.0f, (float)Math.toRadians(180)));
        rotations[7].set(new AxisAngle4f(0.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[8].set(new AxisAngle4f(0.0f, 1.0f, 0.0f, (float)Math.toRadians(-90)));
        rotations[9].set(new AxisAngle4f(0.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[10].set(new AxisAngle4f(0.0f, 1.0f, 0.0f, (float)Math.toRadians(30)));
        rotations[11].set(new AxisAngle4f(0.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        
        
        Transform3D transform = new Transform3D();
        TransformGroup tg = new TransformGroup(transform);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha timer = new Alpha(-1,Alpha.INCREASING_ENABLE, 0, 0, (long)200000/5, 0, 0 ,0, 0, 0);
       
        RotPosScalePathInterpolator interpolator = new RotPosScalePathInterpolator(
                timer, tg, transform, alphas, rotations, positions, scale);

        BoundingSphere bounds = new BoundingSphere();
        interpolator.setSchedulingBounds(bounds);
        
        tg.addChild(interpolator);
        
        return tg;
    }
}


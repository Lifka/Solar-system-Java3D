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
import javax.media.j3d.Interpolator;
import javax.media.j3d.PathInterpolator;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.RotPosScalePathInterpolator;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.RotationPathInterpolator;
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
        transform.setScale(0.4);
        TransformGroup inclinacion = new TransformGroup(transform);
        inclinacion.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	inclinacion.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        
      /*  TransformGroup mov = getRun();
        TransformGroup rotation = getGirosTransform();
        */
        
        TransformGroup mov = getMovimientoTransform();
        inclinacion.addChild(modelo.getSceneGroup());
        mov.addChild(inclinacion);
      /*  mov.addChild(inclinacion);
        rotation.addChild(mov);*/
        
        
        return mov;
  
   }
    
    
   public TransformGroup getRun(){
        Transform3D transform = new Transform3D();
        TransformGroup tg = new TransformGroup(transform);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha timer = new Alpha(-1,Alpha.INCREASING_ENABLE, 0, 0, (long)2000000/5, 0, 0 ,0, 0, 0);
       
        PositionInterpolator interpolador = new PositionInterpolator(timer, tg);
       
        BoundingSphere bounds = new BoundingSphere();
        interpolador.setSchedulingBounds(bounds);
        interpolador.setStartPosition(5);
        interpolador.setEndPosition(50);
        
        
        // añadimos al TransformGroup la animación y la figura
        tg.addChild(interpolador);
        
        return tg;
   } 
   
   
   public TransformGroup getGirosTransform(){
        Transform3D transform = new Transform3D();
        TransformGroup tg = new TransformGroup(transform);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha timer = new Alpha(-1,Alpha.INCREASING_ENABLE, 0, 0, (long)500000/5, 0, 0 ,0, 0, 0);
       
        RotationInterpolator  rot_interpolator = new RotationInterpolator(timer, tg, transform, 0.0f, (float) Math.PI*2.0f);
       
        BoundingSphere bounds = new BoundingSphere();
        rot_interpolator.setSchedulingBounds(bounds);
        
        tg.addChild(rot_interpolator);
        
        return tg;
       
   }

    public TransformGroup getMovimientoTransform(){
        
        float[] scale = {1.0f, 1.0f, 1.0f, 1.0f,
                    1.0f, 1.0f, 1.0f, 1.0f};
        
        float[] alphas = {0.0f, 0.33f, 0.67f, 1.0f,
                    0.0f, 0.33f, 0.67f, 1.0f};
        
        Point3f [] positions = {
                new Point3f(15.0f, 0.0f, 0.0f), new Point3f(20.0f, 1.0f, 0.0f),
                new Point3f(30.0f, 0.0f, 0.0f), new Point3f(40.0f, 0.0f, 0.0f),
                new Point3f(30.0f, 0.0f, 4.0f), new Point3f(20.0f, 0.0f, 4.0f),
                new Point3f(15.0f, 0.0f, 4.0f), new Point3f(5.0f, 0.0f, 0.0f)
        } ;
        
        Quat4f[] rotations = new Quat4f [8];
        for(int i = 0; i < 8; i++)
            rotations[i] = new Quat4f();
        
        rotations[0].set(new AxisAngle4f(1.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[1].set(new AxisAngle4f(0.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[2].set(new AxisAngle4f(1.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[3].set(new AxisAngle4f(0.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[4].set(new AxisAngle4f(0.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[5].set(new AxisAngle4f(0.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[6].set(new AxisAngle4f(0.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        rotations[7].set(new AxisAngle4f(0.0f, 0.0f, 0.0f, (float)Math.toRadians(60)));
        
        
        Transform3D transform = new Transform3D();
        TransformGroup tg = new TransformGroup(transform);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha timer = new Alpha(-1,Alpha.INCREASING_ENABLE, 0, 0, (long)2000000/5, 0, 0 ,0, 0, 0);
       
        RotPosScalePathInterpolator interpolator = new RotPosScalePathInterpolator(
                timer, tg, transform, alphas, rotations, positions, scale);

        BoundingSphere bounds = new BoundingSphere();
        interpolator.setSchedulingBounds(bounds);
        
        tg.addChild(interpolator);
        
        return tg;
    }
}


/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public abstract class Astro extends BranchGroup{
    protected Primitive esfera;
    protected Appearance apariencia;
    
    protected String nombre;
 
    protected float radio;
    protected float distancia;
 
    protected float radio_false;
    protected float distancia_false;
    protected float rotacion_false;
    protected float traslacion_false;
    
    protected Texture textura;
    protected Material material;
    
    protected double t_rotacion, t_traslacion, ang_traslacion;
    
    public Astro(String nombre, float radio, float distancia){
        this.nombre = nombre;
        this.radio = radio;
        this.distancia = distancia;
        esfera = new Sphere();
    }
    
    public Astro(String nombre, float radio, float distancia,
            String archivo_textura, Material material,
            double rotacion, double traslacion, float radio_false,
            float distancia_false, float rotacion_false, float traslacion_false){
        
        this.nombre = nombre;
        this.radio = radio;
        this.distancia = distancia;
        this.radio_false = radio_false;
        this.distancia_false = distancia_false;
        this.rotacion_false = rotacion_false;
        this.traslacion_false = traslacion_false;
        this.material = material;
        
        setApariencia(archivo_textura, material);
        setMovimiento(rotacion, traslacion);
    }
    
  

    public TransformGroup createTransformGroup(TransformGroup tg, Transform3D transform){
        tg = new TransformGroup(transform);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        return tg;
    }
    
    public void setApariencia(String archivo_textura, Material material){
        apariencia = new Appearance();
        this.material = material;
        
        this.textura = new TextureLoader(archivo_textura, null).getTexture();
        TextureAttributes at = new TextureAttributes();
        at.setTextureMode(TextureAttributes.MODULATE);
        
        apariencia.setTexture(textura);
        apariencia.setTextureAttributes(at);
        
        apariencia.setMaterial(this.material);
        
        
        //this.color = color;
        esfera = new Sphere(radio_false/4, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS, 50, apariencia);
    }
    
    public void setMovimiento(double rotacion, double traslacion){
        this.t_rotacion = rotacion;
        this.t_traslacion = traslacion;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public float getDistancia(){
        return (float)(distancia_false * 1);
    }
    
    public double getRadio(){
        return (Math.sqrt(radio)/100);
    }
    public float getRadioFalse(){
        return radio_false;
    }
    
    public boolean rota(){
        return (t_rotacion > 0);
    }
    
    public boolean traslada(){
        return (t_traslacion > 0);
    }
    
    
    
    public abstract void makeTransform(Canvas3D canvas);

    
    public TransformGroup getRotartransform(float vel_rotar){
        Transform3D yAxis = new Transform3D();
        TransformGroup tg = new TransformGroup(yAxis);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha timer = new Alpha(-1,Alpha.INCREASING_ENABLE, 0, 0, (long)vel_rotar, 0, 0 ,0, 0, 0);
        RotationInterpolator  rot_interpolator = new RotationInterpolator(timer, tg, yAxis, 0.0f, (float) Math.PI*2.0f);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), radio_false/4);
        rot_interpolator.setSchedulingBounds(bounds);
        
        
        // añadimos al TransformGroup la animación y la figura
        tg.addChild(rot_interpolator);
        return(tg);
    }
    
    public TransformGroup getDistanceTransform(){
        Transform3D transform = new Transform3D();
        transform.set(new Vector3f(getDistancia(),0.0f,0.0f));
        
        TransformGroup tg = createTransformGroup(new TransformGroup(), transform);
        
        return(tg);
    }
    
    public TransformGroup getInclinationTransform(){
        Transform3D transform = new Transform3D();
        transform.rotZ(0.3);
        
        TransformGroup tg = createTransformGroup(new TransformGroup(), transform);
        
        return(tg);
    }

}

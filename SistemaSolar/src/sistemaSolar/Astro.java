/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Color;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

public abstract class Astro extends BranchGroup{
    protected Primitive esfera;
    protected Appearance apariencia;
    
    protected String nombre;
 
    protected float radio;
    protected float distancia;
    
    protected Texture textura;
    protected Material material;
    protected Color color;
    
    protected Punto posicion;
    
    
    protected double t_rotacion, t_traslacion, ang_traslacion;
    
    public Astro(String nombre, float radio, float distancia){
        this.nombre = nombre;
        this.radio = radio;
        this.distancia = distancia;
        esfera = new Sphere();
        
        makeMagic();
    }
    
    public Astro(String nombre, float radio, float distancia,
            String archivo_textura, Material material, Color color,
            double rotacion, double traslacion){
        
        this.nombre = nombre;
        this.radio = radio;
        this.distancia = distancia;
        setApariencia(archivo_textura, material, color);
        setMovimiento(rotacion, traslacion);
        
        makeMagic();
        
        
    }
    
    
    public TransformGroup createTransformGroup(TransformGroup tg, Transform3D transform){
        tg = new TransformGroup(transform);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        return tg;
        
    }
    
    public void setApariencia(String archivo_textura, Material material, Color color){
        apariencia = new Appearance();
        this.textura = new TextureLoader(archivo_textura, null).getTexture();
        apariencia.setTexture(textura);
        this.material = material;
        this.color = color;
        esfera = new Sphere((float) (Math.sqrt(radio)/100), Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS, 50, apariencia);
    }
    
    public void setMovimiento(double rotacion, double traslacion){
        this.t_rotacion = rotacion;
        this.t_traslacion = traslacion;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public float getDistancia(){
        return distancia/10;
    }
    
    public boolean rota(){
        return (t_rotacion > 0);
    }
    
    public boolean traslada(){
        return (t_traslacion > 0);
    }
    
    public Punto getLocalizacion(){
        return posicion;
    }
    
    public void desplaza(float x, float y, float z){
        posicion.relocate(posicion.getX()+x, posicion.getY()+y, posicion.getZ()+z);
    }
    
    public void makeMagic(){
        TransformGroup rota = getRotartransform();
        TransformGroup distancie = getDistanceTransform();
        rota.addChild(esfera);
        distancie.addChild(rota);
        addChild(distancie);
    }
    
    public TransformGroup getRotartransform(){
        Transform3D yAxis = new Transform3D();
        TransformGroup tg = new TransformGroup(yAxis);
        Alpha timer = new Alpha(-1,Alpha.INCREASING_ENABLE, 0, 0, 100000, 0, 0 ,0, 0, 0);
        RotationInterpolator  rot_interpolator = new RotationInterpolator(timer, tg, yAxis, 0.0f, (float) Math.PI*2.0f);
        BoundingSphere bounds = new BoundingSphere();
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

}

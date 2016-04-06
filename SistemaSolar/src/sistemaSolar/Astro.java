/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Color;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;

public abstract class Astro {
    protected String nombre;
 
    protected double radio;
    protected double distancia;
    
    protected Texture textura;
    protected Material material;
    protected Color color;
    
    protected double t_rotacion, t_traslacion, ang_traslacion;
    
    public Astro(String nombre, double radio, double distancia){
        this.nombre = nombre;
        this.radio = radio;
        this.distancia = distancia;
    }
    
    public void setApariencia(String archivo_textura, Material material, Color color){
        this.textura = new TextureLoader(archivo_textura, null).getTexture();
        this.material = material;
        this.color = color;
    }
    public void setMovimiento(double rotacion, double traslacion){
        this.t_rotacion = rotacion;
        this.t_traslacion = traslacion;
    }
}

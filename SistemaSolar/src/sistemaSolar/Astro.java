/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;

public abstract class Astro {
    String nombre;
    Texture textura;
    double radio;
    double distancia;
    
    Material material;
    double t_rotacion, t_traslacion, ang_traslacion;
    
    public Astro(double radio, double distancia, String archivo_textura, String nombre, Material material){
        this.nombre = nombre;
        this.radio = radio;
        this.distancia = distancia;
        textura = new TextureLoader(archivo_textura, null).getTexture();
        
    }
    
    public void setMovimiento(double rotacion, double traslacion){
        
    }
}

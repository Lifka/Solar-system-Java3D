/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import java.awt.Color;
import javax.media.j3d.Material;


public class Satelite extends Astro {
    
    public Satelite(String nombre, double radio, double distancia, 
            String archivo_textura, Material material, Color c,
            double t_rotacion, double t_traslacion) {
        super(nombre, radio, distancia, archivo_textura, material, c, t_rotacion, t_traslacion);
    }
    public Satelite(String nombre, double radio, double distancia){
        super(nombre, radio, distancia);
    }
    
    public double getDistancia(){
        // DISTANCIA RESPECTO A SU PLANETA
        return distancia;
    }
    
}

/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import javax.media.j3d.Material;


public class Satelite extends Astro {
    
    public Satelite(String nombre, double radio, double distancia, 
            String archivo_textura, Material material, Punto posicion) {
        super(nombre, radio, distancia, posicion);
    }
    
    public double getDistancia(){
        // DISTANCIA RESPECTO A SU PLANETA
        return distancia;
    }
    
}

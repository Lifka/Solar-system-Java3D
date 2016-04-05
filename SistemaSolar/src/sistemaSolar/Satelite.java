/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaSolar;

import javax.media.j3d.Material;

/**
 *
 * @author jgallardo
 */
public class Satelite extends Astro {
    
    public Satelite(double radio, double distancia, String archivo_textura, String nombre, Material material) {
        super(radio, distancia, archivo_textura, nombre, material);
    }
    
}

/*

    Gallardo Morales, Juan Carlos - jcgallardomorales@gmail.com
    Izquierdo Vera, Javier - javieriizquierdovera@gmail.com

 */
package sistemaSolar;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.media.j3d.Canvas3D;
import javax.swing.JFrame;


public class Visualization extends JFrame {
    public Visualization(Canvas3D canvas){
      
        setTitle("Sistema Solar Java3D");
        setSize(700,700);
        setLocation(100,100);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);
        repaint();
        
    }
}

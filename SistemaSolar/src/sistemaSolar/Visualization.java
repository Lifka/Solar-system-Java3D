/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaSolar;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.media.j3d.Canvas3D;
import javax.swing.JFrame;

/**
 *
 * @author jgallardo
 */
public class Visualization extends JFrame {
    public Visualization(Canvas3D canvas){
      
        setTitle("Java3D");
        setSize(700,700);
        setLocation(100,100);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(canvas, BorderLayout.CENTER);
        repaint();
        
    }
}

package helpers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ConvertirImagen {

    public static Image iconoAImagen(Icon icon){
        int w  = icon.getIconWidth();
        int h = icon.getIconHeight();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage bi = gc.createCompatibleImage(334, 184);
        Graphics2D g = bi.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        return bi;
    }

    public static byte[] imagenABytes(Image image){
        BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try{
            ImageIO.write(bi, "JPG", stream);
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("No se pudo convertir la imagen a bytes");
        }
        return stream.toByteArray();
    }
    
    /**
     * Este metodo nos ayuda a convertir un arreglo de bytes a un ImageIcon 😀
     * @param data
     * @return return 😀
     */
    public static ImageIcon bytesAImageIcon(byte[] data){
        
        BufferedImage img = null;
        
        try {
            img = ImageIO.read(new ByteArrayInputStream(data));
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        ImageIcon icono = new ImageIcon(img);
        return icono;
    }
}
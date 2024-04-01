package br.ulbra.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static String calcularMD5(String senha){
        String hashMD5 = "";
        try {
            // Crie uma instância do MessageDigest com o algoritmo MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Atualize o digest com os bytes do texto
            md.update(senha.getBytes());
            
            // Calcule o hash MD5
            byte[] digest = md.digest();
            
            // Converta o hash de bytes para uma representação hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            hashMD5 = sb.toString();  
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algoritmo MD5 não encontrado");
        }
        
        return hashMD5;
    }
    /*public static int salvarBoolean(boolean valor){
        if (valor = true)
            return 1;
        else           
            return 0;
    }*/
    
    public static Icon fileParaIcon(File file) {
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        return icon;
    }
    
    public static ImageIcon redimensionarIcon(Icon originalIcon, 
            int largura, int altura) {
        Image imagemOriginal = ((ImageIcon) originalIcon).getImage();
        Image novaImagem = imagemOriginal.getScaledInstance(
                largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(novaImagem);
    }
    
    public static byte[] iconToBytes(Icon icon) throws IOException {
        BufferedImage image = new BufferedImage(
                icon.getIconWidth(), 
                icon.getIconHeight(), 
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}

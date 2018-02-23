/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MAYANK BHUTANI, YAJAT DAWAR
 */

//imports
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;


public class MyDefish 
{
    private static String inputImageName = "qw"; 
    private static String panoImageName = inputImageName+"Output4";
    private static String imageExtensionName = ".png"; 
    
    //Main Method
    public static void main(String args[]) throws IOException
    {
        //var
        BufferedImage myInputImage = inputImage();
        BufferedImage myPanoImage;
        
        int inputImageWidth = myInputImage.getWidth();
        int inputImageHeight = myInputImage.getHeight();
        
        int panoImageWidth = (int)(inputImageWidth*Math.PI);
        int panoImageHeight = inputImageWidth/2;
       
        int[][] inputArray = new int[inputImageWidth][inputImageHeight];
        int[][] panoArray = new int[panoImageWidth][panoImageHeight];
        
        //input pixels into array
        for(int i = 0; i < inputImageWidth; i++)
        {
            for(int j = 0; j < inputImageHeight; j++)
            {
              inputArray[i][j] = myInputImage.getRGB(i, j);
            }
        }
        
        //manipulate
        int numx = 0,numy=0;
        int count=0;
        for(int h=panoImageHeight-1;h>=0;h--)
        {
	    for(int i = 0; i < panoImageWidth; i++)
	    {
	      	numx = (int) (panoImageHeight+(h)*Math.cos(2*(double)i/inputImageWidth) );
	       	numy =  (int) (panoImageHeight+(h)*Math.sin(2*(double)i/inputImageWidth));
                //System.out.println(numx+"  "+numy);
                panoArray[i][h] = inputArray[numx][numy];    
                inputArray[numx][numy] = 0;
                if(panoArray[i][h]==0)  count++;
	    }
            System.out.println(count+" "+h);
            count=0;
    	}
        
        /*
         int numx = 0,numy=0;
        int y=panoImageHeight-1,x=0;
        for(int h=panoImageHeight-1;h>=0;h--)
        {
	    for(int i = 0; i < panoImageWidth; i++)
	    {
	      	numx = (int) (panoImageHeight+(h)*Math.cos(2*(double)i/inputImageWidth) );
	       	numy =  (int) (panoImageHeight+(h)*Math.sin(2*(double)i/inputImageWidth));
                //System.out.println(numx+"  "+numy);
                if(inputArray[numx][numy] != 0){
                panoArray[x][y] = inputArray[numx][numy];    
                inputArray[numx][numy] = 0;
                x++;}
	    }
            x=0;
            y--;
    	}
        */
        
        
        //output pixels to array
        myPanoImage = new BufferedImage(panoImageWidth, panoImageHeight, BufferedImage.TYPE_INT_ARGB);
        for(int h=panoImageHeight-1;h>=0;h--)
        {
            for(int i = 0; i < panoImageWidth; i++)
            {
                myPanoImage.setRGB(i, h, panoArray[i][h]);    
            }
        }
        
        //output image to file
        outputImage(myPanoImage);
        
        
        
    }
    
    private static BufferedImage inputImage()
    {
        File file;
        BufferedImage image = null;
        try
        {
            file = new File("F:\\BITS\\extras\\project\\CEERI\\MyScript\\DiffScriptOutputv2\\"+inputImageName+imageExtensionName);
            image = ImageIO.read(file);
        }
        
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
        
        return image;        
    }
    
    private static void outputImage(BufferedImage image)
    {
        File file;
        try
        {
            file = new File("F:\\BITS\\extras\\project\\CEERI\\MyScript\\DiffScriptOutputv2\\"+panoImageName+imageExtensionName);
            ImageIO.write(image, "png", file);
        }
        
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
        
                
    }


}



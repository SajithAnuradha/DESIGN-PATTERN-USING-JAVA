package Prototype;

// ImageBuffer is the "abstract" Prototype
abstract class ImageBuffer implements Cloneable {
    public ImageBuffer clone()
    throws CloneNotSupportedException {
    return (ImageBuffer) super.clone();
    }
    public void log(String s) { System.out.println(s); }
    }

    // ImageBW is a "concrete" object to be created
    // using a prototype
    class ImageBW extends ImageBuffer {
    public ImageBuffer clone()
    throws CloneNotSupportedException {
    log("Cloning an image buffer for black & white");
    return (ImageBW) super.clone();
    }
    }
    // ImageRGB is a "concrete" object to be created
    // using a prototype
    class ImageRGB extends ImageBuffer {
    public ImageBuffer clone()
    throws CloneNotSupportedException {
    log("Cloning an image buffer for color");
    return (ImageRGB) super.clone();
    }
    }
  
    // BufferMaker is a class with a Factory Method
    class BufferMaker {
    private ImageBW imgbw; private ImageRGB imgrgb;
    // Create instances of these objects only once
    public BufferMaker() {
    imgbw = new ImageBW(); imgrgb = new ImageRGB();
    }
    // Create multiple instances by cloning of objects
    public ImageBuffer makeImageBuffer(char imgtyp)
    throws CloneNotSupportedException {
    switch(imgtyp) {
    case 'b': return imgbw.clone();
    case 'c': return imgrgb.clone();
    default: return null;
    }
    }
    }
 
    public class MarsRover {
    public static void main(String [] args) {
    ImageBuffer [] mars = new ImageBuffer[5];
    BufferMaker bufmaker = new BufferMaker();
    int i=0;
    try {
    mars[i++] = bufmaker.makeImageBuffer('a');
    mars[i++] = bufmaker.makeImageBuffer('c');
    mars[i++] = bufmaker.makeImageBuffer('b');
    mars[i++] = bufmaker.makeImageBuffer('c');
    mars[i++] = bufmaker.makeImageBuffer('a');
    } catch (CloneNotSupportedException e) {
    System.out.println("Exception!");
    }
    }
}
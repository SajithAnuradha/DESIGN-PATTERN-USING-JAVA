package AbstractFactory;

class Image {                                   // 01
    public Image() {

    }
    public Image(String name) {

    }
}
    // These generic abstract interfaces for Filters are
    // created for use by abstract Factory

    interface BlurFilter {
                      public Image apply(Image img);
    }
    interface EdgeDetectFilter {
                       public Image apply(Image img);
    }
    interface SharpenFilter {
                      public Image apply(Image img);
    }
    
    // These are the concrete implementations of the Filters
    // created independently. There can be many variants
    class BlurFilter3X3 implements BlurFilter {                 // 02
             public Image apply(Image img) {
                           System.out.println("applying blur filter with 3X3 kernel");
                           return new Image();
            }
    }

    class EdgeDetectFilter3X3 implements EdgeDetectFilter {        // 03
             public Image apply(Image img) {
                              System.out.println("applying edge detection filter with 3X3 kernel");
                              return new Image();
             }
    }

    class SharpenFilter3X3 implements SharpenFilter {              //04
             public Image apply(Image img) {
                            System.out.println("applying sharpenning filter with 3X3 kernel");
                            return new Image();
    }
    } 
    // This is the Abstract Factory that the clients would
    // use and they do not need to know any specific
    // implementation details.
    interface FilterFactory {
                                  public BlurFilter createBlurFilter();
                                  public EdgeDetectFilter createEdgeDetectFilter();
                                  public SharpenFilter createSharpenFilter();
    }
  
    // This is a Concrete implementation of Abstract Factory.
    // It is only here we decide exactly how filters are created.
    // This is done independent of any client programs.
    // Also, we can create many different Concrete Factories.
    class FilterFactory3X3 implements FilterFactory {               //05

                public BlurFilter createBlurFilter() {
                                System.out.println("creating blur filter with 3X3 kernel");
                                return new BlurFilter3X3();
                 }
                public EdgeDetectFilter createEdgeDetectFilter() {
                                 System.out.println("creating edge detection filter with 3X3 kernel");
                                 return new EdgeDetectFilter3X3();
                }

                public SharpenFilter createSharpenFilter() {
                                System.out.println("creating sharpenning filter with 3X3 kernel");
                                return new SharpenFilter3X3();
                }
    }
    
public class PhotoBooth {

    private BlurFilter blurf;
    private EdgeDetectFilter edgef;
    private SharpenFilter sharpf;
    private FilterFactory ffactory;


    public PhotoBooth(FilterFactory ffactory) {
                    this.ffactory = ffactory;
    // Different filters are created by the Factory object
                    blurf = ffactory.createBlurFilter();
                    edgef = ffactory.createEdgeDetectFilter();
                    sharpf = ffactory.createSharpenFilter();
    }
    public Image blur(Image img) { 
        return blurf.apply(img); 
    }
    public Image edgeDetect(Image img) {
        return edgef.apply(img);
    }
    public Image sharpen(Image img) { 
        return sharpf.apply(img); 
    }

    public static void main(String [] args) {
    // Use a concrete Filter Factory instance
           FilterFactory3X3 ff = new FilterFactory3X3();
    // Create a Photo Booth with specific Filter Factory
           PhotoBooth pb = new PhotoBooth(ff);
           Image lena = new Image("lena.jpg");
    lena = pb.sharpen(lena);
    lena = pb.edgeDetect(lena);
    lena = pb.blur(lena);
    }
    }
    
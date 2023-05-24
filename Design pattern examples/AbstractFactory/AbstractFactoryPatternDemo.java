package AbstractFactory;

class CIRCLE implements shape {
         public void  draw (){
             System.out.println("Inside CIRCLE::draw() method.");
         }
}

class RECTANGLE implements shape {

    public void  draw (){
        System.out.println("Inside  RECTANGLE::draw() method.");
    }
}

class SQUARE implements shape {

    public void  draw (){
        System.out.println("Inside  SQUARE ::draw() method.");
    }

}

interface shape {
    void draw ();
}

abstract class AbstractFactory {
    abstract shape getShape(String shapeType) ;
 }


public class AbstractFactoryPatternDemo{
    public static void main(String[] args) {
        
    }
}
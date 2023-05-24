package AbstractFactory;

interface Widgets {
    void render ();
}
interface button extends Widgets{      // button is interface because we will given two different implementation for button.
}

interface textBox extends Widgets{

}

class MaterialButton implements button{
    public void render(){
        System.out.println(" Material Button");
    }
}

class AntButton implements button{
    public void render(){
        System.out.println(" Ant Button");
    }
}

class MaterialTextBox implements textBox{
    public void render(){
        System.out.println(" Material TextBox");
    }
}


class AntTextBox implements textBox{
    public void render(){
        System.out.println(" Ant TextBox");
    }
}



























public class GUIFramework {
    
}

package Builder;

import java.util.ArrayList;
import java.util.List;

interface Item {
    public String name ();
    public Packing packing ();
    public float price ();

}
interface Packing {
    public String pack();
}

class Wrapper implements Packing {
    public String pack (){
        return "Wrapper";

    }
}

class Bottle  implements Packing{
    public String pack (){
        return "bottle";
    }
}

abstract class Burger implements Item {
    public abstract  String name ();

    public Packing packing (){
        return new Wrapper();
     }
    public abstract float price ();

}


abstract class CoolDrinks implements Item {
    public abstract  String name ();

    public Packing packing (){
        return new Bottle();
     }
    public abstract float price ();

}

class Pepsi extends CoolDrinks{

    public  String name() {
        return "pepsi";
    }

    public float price (){
        return  120;
    }
}


class ChickenBurger extends Burger{
    public String name  (){
        return "Chicken Burger";
    }
    public float price (){
        return 500;
    }
}


class VegBurger extends Burger{
    public String name  (){
        return "Veg Burger";
    }
    public float price (){
        return 300;
    }
}


class Coke extends CoolDrinks{

    public  String name() {
        return "Coke";
    }

    public float price (){
        return  150;
    }
}

class Meal {
    private List<Item> Items =new ArrayList<Item>();

    public void addItem (Item item){
         Items.add(item);
    }
    public float getCost (){
        float cost = 0;
        for (Item item: Items){
            cost +=item.price();
        }

        return cost;
    }

    public void showItems(){
         for (Item item:Items){
            System.out.print(" Item : "+item.name() );
            System.out.print("  packing " + item.packing().pack());
            System.out.println("  price :" + item.price());
            



         }
    }

}

class MealBuilder {
    public Meal prepareVegMeal (){
        Meal meal=new Meal();
        meal.addItem( new VegBurger());
        meal.addItem(new Coke() );
        return meal;
    }
    public Meal prepareNonVegMeal (){
        Meal meal=new Meal();
        meal.addItem( new ChickenBurger());
        meal.addItem(new Pepsi() );
        return meal;
    }
}




public class FastFoodRestaurant {
    public static void main(String[] args) {
        MealBuilder Menu= new MealBuilder();
        Meal vegi=Menu.prepareVegMeal();
        vegi.showItems();
        System.out.println(" Total cost " + vegi.getCost());


    }
    
}

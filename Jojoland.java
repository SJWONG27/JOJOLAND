package jojoland;

import java.util.*;

// Demo interface for restaurants
public class Jojoland {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();
        int currentDay = 3;
        String area = "Jade Garden";
        
        //Jade Garden, Cafe Deux Magots, Liberrio, Savage Garden, Trattoria Trussardi
        
        Menu menu = new Menu(area);
        SalesRecord record = new SalesRecord(menu);
        
        // get Sales from Pearl's Jam (Ordering List)
        
        // (example) add sales in for Day 1, 2 and 3
        ArrayList<Food> foodList = menu.getFoodList();
        for(Food food : foodList){
            if(food.getName().equals("Braised Chicken in Black Bean Sauce")){
                record.addSales(food,1,6);
                record.addSales(food, 2, 3);
                record.addSales(food, 3, 20);}
            else if(food.getName().equals("Braised Goose Web with Vermicelli"))
                record.addSales(food,1,3);
            else if(food.getName().equals("Scrambled Egg White with Milk"))
                record.addSales(food,2,12);
            else if(food.getName().equals("Poached Tofu with Dried Shrimps"))
                record.addSales(food,3,2);
        }
        
        boolean status = true;
        
        while(status){
        System.out.println("Current Location : "+area);
        System.out.println("[1] Move to : "); 
            System.out.println("\t[A] ...\t[B] ...");
        System.out.println("[2] View Waiting List and Order Processing List");
        System.out.println("[3] View Menu");
        System.out.println("[4] View Sales Information ");
        System.out.println("[5] Milagro Man");
        System.out.println("[6] Back (area before) ");
        System.out.println("[7] Back to Town Hall");
        System.out.println("Select : ");
        String choice = sc.nextLine();
        System.out.println("======================================================================");
        
        
        switch(choice){
            case "1" : // move to selected area
                break;
            case "2" : // go to pearl's jam code
                break;
            case "3" : menu.printMenu();
                break;
            case "4" : 
                       MoodyBlues salesInfo = new MoodyBlues(menu,record);
                       salesInfo.printMoodyBlues();
                break;
            case "5" : // Temporary copy of menu sales records
                       Menu menuMM = new Menu(area+" (Milagro Man Mode)");
                       SalesRecord recordMM = new SalesRecord(menuMM,record.getFoodSales());
                       MilagroMan expMode = new MilagroMan(menuMM,recordMM);
                       expMode.printMilagroMan();
                       recordMM = null;
                break;
            case "6" : // go to the area before
                break;
            case "7" : // go to Town Hall
                break;
            default : status = false ; // exit program
            }
        }
    }
}

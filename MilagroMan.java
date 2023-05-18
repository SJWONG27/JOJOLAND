package jojoland;

import java.util.*;

public class MilagroMan{
    private Menu menu; 
    private SalesRecord sale;  
    private MoodyBlues salesInfo;
    
    public MilagroMan(Menu menu, SalesRecord sale){
        this.menu = menu;
        this.sale = sale;
    }
    
    public void printMilagroMan(){
        boolean status = true;
        while(status){
        Scanner sc = new Scanner(System.in);
        System.out.println("Restaurant : "+menu.getRestaurant());
        System.out.println("[1] Modify Food Prices");
        System.out.println("[2] View Sales Information");
        System.out.println("[3] Exit Milagro Man");
        System.out.println("Select : ");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println("======================================================================");
        switch(choice){
                case 1:
                    System.out.println("Enter food name : ");
                    String name = sc.nextLine();
                    System.out.println("Enter new price : ");
                    double newPrice = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter Start Day : ");
                    int startDay = sc.nextInt();
                    System.out.println("Enter End Day : ");
                    int endDay = sc.nextInt();
                    System.out.println("======================================================================");
                    modifyFoodPrice(name,newPrice,startDay,endDay);
                    break;
                case 2:
                    salesInfo = new MoodyBlues(menu,sale);
                    salesInfo.printMoodyBlues();
                    break;
                case 3:
                    status = false;
                    break;
                default: status = false;
            }
        }
    }

    public void modifyFoodPrice(String foodName, double newPrice, int startDay, int endDay) {
        // Modify the food price within the given range of days starting from the next day
        menu.modifyMenu(foodName,newPrice);
        ArrayList<FoodSales> newFoodSales = new ArrayList<>();
        for (FoodSales foodSales : sale.getFoodSales()) {
            if (foodSales.getName().equals(foodName) && foodSales.getDay() >= startDay && foodSales.getDay() <= endDay) {
                foodSales.setPrice(newPrice);
            }
            newFoodSales.add(foodSales);
        }
        sale.setFoodSales(newFoodSales);
    }
}
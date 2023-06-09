package moodyBlues;
import java.util.*;

public class MilagroMan{
    private Menu menu; 
    private SalesRecord sale;  
    private MoodyBlues salesInfo;
    
    public MilagroMan(String restaurant){
        menu = new Menu(restaurant);
        menu.setRestaurant(restaurant+" (Milagro Man Mode)");
        sale = new SalesRecord(menu);
        sale.copySales(new SalesRecord(new Menu(restaurant)));
    }
    
    public void printMilagroMan(){
        boolean status = true;
        while(status){
        Scanner sc = new Scanner(System.in);
        System.out.println("==============================================================================");
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
                    modifyFoodPrice(name,newPrice,startDay,endDay);
                    break;
                case 2:
                    salesInfo = new MoodyBlues(menu.getRestaurant());
                    salesInfo.printMoodyBlues();
                    break;
                case 3:
                    status = false;
                    menu.resetFoodList(); // Reset menu to its original state
                    break;
                default: status = false;
            }
        }
    }

    public void modifyFoodPrice(String foodName, double newPrice, int startDay, int endDay) {
        menu.modifyMenu(foodName, newPrice);
        menu.printFoodList();
        for (int day = startDay; day <= endDay; day++) {
            sale.setSale(day, foodName, newPrice);
        }
    }
    
    
    
}

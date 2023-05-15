/*
 * NUR FATIHA SYUHADA BINTI AZIZI      U2101063/2      OCC 1
 */
package jojoland;

import java.util.*;

/**
 *
 * @author fasyu
 */
public class Jojoland {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();
        String area = "Jade Garden";
        Menu menu = new Menu(area);
        SalesRecord record = new SalesRecord(menu);
        
        //demo to add sales in Day 1 until Day 5
        int day = 1;
        ArrayList<Food> food = menu.foodList;
        for(int i=1; i<=5 ; i++){
            day = i;
            for(Food item : food){
                int random = ran.nextInt(5);
                record.addSales(item.getName(),item.getPrice(),day,random);
            }
        }
        
        while(true){
        System.out.println("\nCurrent Location : "+area);
        System.out.println("[3] View Menu");
        System.out.println("[4] View Sales Information ");
        System.out.println("[5] Milagro Man");
        System.out.println("Select : ");
        int choice = sc.nextInt();
        System.out.println("======================================================================");
        switch(choice){
            case 3 : 
                     menu.printMenu();
                break;
            case 4 : MoodyBlues salesInfo = new MoodyBlues(menu,record);
                     salesInfo.printMoodyBlues();
                break;
            case 5 : MilagroMan expMode = new MilagroMan(area);
                     expMode.printMilagroMan();
                break;
        }
        }
    }
}

package moodyBlues;
import TheWorld.defaultMap;
import pearlJam.CafeDeuxMagots;
import pearlJam.JadeGarden;
import pearlJam.Libeccio;
import pearlJam.SavageGarden;
import pearlJam.TrattoriaTrussardi;
import joestar.Joestar;

import java.util.*;

// Demo interface for restaurants
public class testerRestaurant {
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        Random ran = new Random();
        
        String area = "Jade Garden";
        
        JadeGarden jd = new JadeGarden();
        
        /*
        System.out.println("Choose location :");
        System.out.println("[1] Jade Garden");
        System.out.println("[2] Cafe Deux Magots");
        System.out.println("[3] Liberrio");
        System.out.println("[4] Savage Garden");
        System.out.println("[5] Trattoria Trussardi");
        boolean opt = true;
        while(opt){
        System.out.print("Select : ");
        int select = sc.nextInt();
        switch(select){
            case 1 : area = "Jade Garden";
            break;
            case 2 : area = "Cafe Deux Magots";
            break;
            case 3 : area = "Liberrio";
            break;
            case 4 : area = "Savage Garden";
            break;
            case 5 : area = "Trattoria Trussardi";
            break;
            default: 
                System.out.println("No such restaurant");
        }
        */
        Menu menu = new Menu(area);
        SalesRecord record = new SalesRecord(menu);
        
        // get Sales from Pearl's Jam (Ordering List)
        
        // (example) add sales in for Day 1-8
        for(int i = 1 ; i < 8 ; i++)
            record.recordSalefromFile(i,area);
        
        boolean opt = true;
            
        defaultMap game = new defaultMap();
        
        while(opt=true){
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
            case "2" : 
            
                break;
            case "3" : menu.printMenu();
                break;
            case "4" : 
                       MoodyBlues salesInfo = new MoodyBlues(area);
                       salesInfo.printMoodyBlues();
                break;
            case "5" : 
                       MilagroMan expMode = new MilagroMan(area);
                       expMode.printMilagroMan();
                break;
            case "6" : // go to the area before
                break;
            case "7" : // go to Town Hall
                break;
            default : opt = false ; // exit program
            }
        }
    }
}

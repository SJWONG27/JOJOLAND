/*
 * NUR FATIHA SYUHADA BINTI AZIZI      U2101063/2      OCC 1
 */
package jojoland;

import java.io.*;
import java.util.*;

/**
 *
 * @author fasyu
 */
public class MoodyBlues{
    Menu menu; // reference to the Menu instance : String restaurant , ArrayList<Food> foodList
    SalesRecord sale; // reference to the SalesRecord instance : ArrayList<FoodSales> foodSales;
    
    public MoodyBlues(Menu menu, SalesRecord sale){
        this.menu = menu;
        this.sale = sale;
    }
    
    // A method to print option available in MoodyBlues(Sales Information)
    public void printMoodyBlues(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Restaurant: "+menu.getRestaurant());
            System.out.println("Sales Information");
            System.out.println("[1] View Sales");
            System.out.println("[2] View Aggregated Information");
            System.out.println("\t[A] Minimum Sales");
            System.out.println("\t[B] Maximum Sales");
            System.out.println("\t[C] Top k Highest Sales");
            System.out.println("\t[D] Total and Average Sales");
            System.out.println("[3] Exit");
            System.out.println("Select : ");
            String choice = sc.nextLine();

            switch(choice){
                case "1":
                    viewSales();
                    break;
                case "2A":
                    viewMinSales();
                    break;
                case "2B":
                    viewMaxSales();
                    break;
                case "2C":
                    viewTopHighestSales();
                    break;
                case "2D":
                    viewTotalAverageSales();
                    break;
                case "3":
                    // exit view sales info
                default:
            }
        }    
    }
    
    
    // A method to print sales on selected day
    public void viewSales(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Day : ");
        int day = sc.nextInt();
        double totalSales = 0;
        System.out.println("======================================================================");
        System.out.println("Restaurant : "+menu.getRestaurant());
        System.out.println("Day "+day+" Sales");
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.println("| Food                                | Quantity | Total Price |");
        System.out.println("+-------------------------------------+----------+-------------+");
        
        for(FoodSales sale : sale.getSalesByDay(day)){
            double totalPrice = sale.getQuantity()*sale.getPrice();
            totalSales+=totalPrice;
            System.out.printf("| %-35s | %5d    |   $%-6.2f   |\n",sale.getName(),sale.getQuantity(),totalPrice);
        }
        
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.printf("|                                   Total Sales  |   $%-6.2f  |\n",totalSales);
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.println("======================================================================");
    }
    
    
    // A method to print minimum sales 
    private void viewMinSales() {
        System.out.println("======================================================================");

        double min = 99999.00;
        int dayMin = 0 ;
        for (Map.Entry<Integer, Double> entry : SalesRecord.mergeQuantities(sale.foodSales).entrySet()) {
        // Get total sales by day
            int day = entry.getKey();
            double totalSales = entry.getValue();

            if(totalSales<min){
                min = totalSales;
                dayMin = day ;
            }
        }
        System.out.println("Minimum sales Day "+dayMin);
        System.out.println("Total Sales : "+min);
        System.out.println("======================================================================");
    }
    // A method to print maximum sales
    private void viewMaxSales() {
        System.out.println("======================================================================");
        double max = -1.00;
        int dayMax = 0 ;
        for (Map.Entry<Integer, Double> entry : SalesRecord.mergeQuantities(sale.foodSales).entrySet()) {
        // Get total sales by day
            int day = entry.getKey();
            double totalSales = entry.getValue();

            if(totalSales>max){
                max = totalSales;
                dayMax = day ;
            }
        }
        System.out.println("Minimum sales Day "+dayMax);
        System.out.println("Total Sales : "+max);
        System.out.println("======================================================================");
    }
    
    // A method to print top k highest selling dishes
    private void viewTopHighestSales() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter k value : ");
        int k = sc.nextInt();
        int i = 0;
        System.out.println("======================================================================");
        ArrayList<FoodSales> foodSalesList = sale.mergeAllFoodSales(sale.foodSales);
        
        // Sort the list based on high to low quantity
        Collections.sort(foodSalesList, new Comparator<FoodSales>() {
            @Override
            public int compare(FoodSales o1, FoodSales o2) {
                return o2.getQuantity() - o1.getQuantity();
            }
        });

        // Print the sorted list
        for (FoodSales foodSales : foodSalesList) {
            i++;
            System.out.println("Name: " + foodSales.getName() +
                    ", Quantity: " + foodSales.getQuantity());
            if(i==k)
                break;
        }
        
        System.out.println("======================================================================");
    }

    private void viewTotalAverageSales() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Start Day : ");
        int startDay = sc.nextInt();
        System.out.println("Enter End Day : ");
        int endDay = sc.nextInt();
        System.out.println("======================================================================");
        System.out.println("Restaurant : "+ menu.getRestaurant());
        System.out.println("Total and Average Sales (Day "+startDay+" - "+endDay+")");
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.println("| Food                                | Quantity | Total Price |");
        System.out.println("+-------------------------------------+----------+-------------+");
        
        double totalSales = 0;
        SalesRecord temp = sale;
        for(FoodSales saleByDay : temp.mergeFoodSalesByDay(temp.foodSales,startDay,endDay)){
            double totalPrice = saleByDay.getQuantity()*saleByDay.getPrice();
            totalSales+=totalPrice;
            System.out.printf("| %-35s | %5d    |   $%-6.2f   |\n",saleByDay.getName(),saleByDay.getQuantity(),totalPrice);
        }
        
        double averageSales = totalSales/(endDay-startDay+1);
        
        
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.printf("|                                   Total Sales  |   $%-6.2f  |\n",totalSales);
        System.out.printf("|                                 Average Sales  |   $%-6.2f  |\n",averageSales);
        System.out.println("+-------------------------------------+----------+-------------+");
        System.out.println("======================================================================");
    }
    
    
}
        

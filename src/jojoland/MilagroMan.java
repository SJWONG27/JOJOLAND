/*
 * NUR FATIHA SYUHADA BINTI AZIZI      U2101063/2      OCC 1
 */
package jojoland;

import java.util.Scanner;

/**
 * Milagro Mode : experimental mode // incomplete
 */
public class MilagroMan {
    
    private String area;
    public MilagroMan(){
        area = null;
    }
    public MilagroMan(String area){
        this.area = area;
    }
    
    public void printMilagroMan(){
        Scanner sc = new Scanner(System.in);
        System.out.println("======================================================================");
        System.out.println("Restaurant : "+area+" (Milagro Man Mode)");
        System.out.println("[1] Modify Food Prices");
        System.out.println("[2] View Sales Information");
        System.out.println("[3] Exit Milagro Man");
        System.out.println("Select : ");
        int choice = sc.nextInt();
        switch(choice){
                case 1:
                    modifyFoodPrice();
                    break;
                case 2:
                    viewSalesInformation();
                    break;
                case 3:
                    exitMilagroMan();
                    break;
                default:
            }
    }

    private void modifyFoodPrice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter food name : ");
        String name = sc.nextLine();
        System.out.println("Enter new price : $");
        double newPrice = sc.nextDouble();
        System.out.println("Enter Start Day : ");
        int startDay = sc.nextInt();
        System.out.println("Enter End Day : ");
        int endDay = sc.nextInt();
        
        
        
        printMilagroMan();
    }

    private void viewSalesInformation() {
       
    }

    private void exitMilagroMan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

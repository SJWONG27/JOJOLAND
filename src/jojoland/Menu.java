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
public class Menu {
    String restaurant; // restaurant's name
    ArrayList<Food> foodList; // list of menu
    
    public Menu(String restaurant) {
        this.restaurant = restaurant; 
        foodList = readMenuFromFile(); // read ArrayList<Food> from file according to each restaurant
    }
    // A getter method for restaurant's name
    public String getRestaurant() {
        return restaurant;
    }
    // A method to read menu from file based on restaurant name , return ArrayList<Food> 
    private ArrayList<Food> readMenuFromFile() {
        ArrayList<Food> menu = new ArrayList<>();
        ArrayList<String> menuName = new ArrayList<>();
        ArrayList<Double> menuPrice = new ArrayList<>();
        
        String fileName = restaurant + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                menuName.add(data[0]);
                menuPrice.add(Double.parseDouble(data[1]));
            }
                for(int i=0;i<menuName.size();i++){
                    menu.add(new Food(menuName.get(i),menuPrice.get(i)));
                }
            return menu;
        } catch (IOException e) {
            // Handle file reading error
            System.out.println("Error reading menu data: " + e.getMessage());
        }
        return null;
    }

    // A method to write menu to file based on restaurant name , main purpose to save any changes in menu
    private void writeMenuToFile() {
        String fileName = restaurant+ ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Food menuItem : foodList) {
                writer.write(menuItem.getName()+","+menuItem.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            // Handle file writing error
            System.out.println("Error writing menu data: " + e.getMessage());
        }
    }
    
    // A method to add new food to the menu
    public void addMenu(String name,double price){
        Food newFood = new Food(name,price);
        foodList.add(newFood);
        System.out.println("Menu item '"+name+"' added successfully");
        System.out.println("======================================================================");
        printMenu();
        writeMenuToFile();
    }
    
    // A method to remove food from the menu , parameter needed food's name
    public void removeMenu(String name) {
        int index = -1;
        for (int i = 0; i < foodList.size(); i++) {
            Food food = foodList.get(i);
            if (food.getName().equals(name)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            foodList.remove(index);
            System.out.println("Menu item '" + name + "' removed successfully.");
            System.out.println("======================================================================");
        } else {
            System.out.println("Menu item '" + name + "' not found.");
            System.out.println("======================================================================");
        }
        printMenu();
        writeMenuToFile();
    }
    
    // A method to modify food Price from the menu , parameter needed food's name and new food's price
    public void modifyMenu(String name, double newPrice) {
        boolean found = false;
        for (Food food : foodList) {
            if (food.getName().equals(name)) {
                food.setPrice(newPrice);
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Menu item '" + name + "' price modified successfully.");
            System.out.println("======================================================================");
        } else {
            System.out.println("Menu item '" + name + "' not found.");
            System.out.println("======================================================================");
        }
        printMenu();
        writeMenuToFile();
    }

    // A method to print the menu and the option available
    public void printMenu(){
        System.out.println(getRestaurant()+"'s Menu");
        for (Food item : foodList){
            item.printFoodInfo();
        }
        System.out.println("\n[1] Add Food");
        System.out.println("[2] Modify Food Price");
        System.out.println("[3] Remove Food");
        System.out.println("[4] Exit View Menu");
        Scanner sc = new Scanner(System.in);
        System.out.println("Select : ");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println("======================================================================");
        switch(choice){
            case 1: 
                System.out.print("Enter new food name to add : ");
                String newName = sc.nextLine();
                System.out.print("Enter new food price : ");
                double newPrice = sc.nextDouble();
                addMenu(newName,newPrice);
            break;
            case 2: 
                System.out.print("Enter food name to modify : ");
                String name = sc.nextLine();
                System.out.print("Enter new food price : ");
                newPrice = sc.nextDouble();
                modifyMenu(name,newPrice);
            break;
            case 3: 
                System.out.print("Enter food name to remove : ");
                name = sc.nextLine();
                removeMenu(name);
            break;
            default: System.out.println("");
        }
    }
    
}

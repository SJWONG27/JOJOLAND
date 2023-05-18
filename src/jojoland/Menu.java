package jojoland;

import java.io.*;
import java.util.*;

// Menu class to view, add , modify and remove menu
public class Menu {
    private String restaurant; // restaurant's name
    private ArrayList<Food> foodList; // list of menu
    
    public Menu(String restaurant) {
        this.restaurant = restaurant; 
        foodList = readMenuFromFile(); // get ArrayList<Food> from file according to each restaurant
    }
    
    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }
    
    
    
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
    
    public void addMenu(String name,double price){
        Food newFood = new Food(name,price);
        foodList.add(newFood);
        System.out.println("Menu item '"+name+"' added successfully");
        System.out.println("======================================================================");
        printMenu();
        writeMenuToFile();
    }
    
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
        writeMenuToFile();
    }

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
                printMenu();
            break;
            case 3: 
                System.out.print("Enter food name to remove : ");
                name = sc.nextLine();
                removeMenu(name);
            break;
            default: 
        }
    }
    
}

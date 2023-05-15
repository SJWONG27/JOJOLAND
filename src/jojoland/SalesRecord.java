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
public class SalesRecord{
    Menu menu; // reference to the Menu instance : String restaurant , ArrayList<Food> foodList
    
    ArrayList<FoodSales> foodSales; // list of food sales by day
    
    public SalesRecord(Menu menu){
        this.menu = menu;
        foodSales = readSaleFromFile();
}
    // A method to add sale 
    public void addSales(String name,double price,int day,int quantity) {
        FoodSales newSale = new FoodSales(name,price,day,quantity);
        foodSales.add(newSale);
        foodSales = mergeFoodSales(foodSales);
        writeSaleToFile();
    }
    
    // A method to merge FoodSales list based on name and price 
    public ArrayList<FoodSales> mergeAllFoodSales(ArrayList<FoodSales> foodSalesList){
        Map<String, FoodSales> mergedSales = new HashMap<>();

        // Iterate through the list and merge items based on name and price
        for (FoodSales foodSales : foodSalesList) {
            String key = foodSales.getName() + "-" + foodSales.getPrice();
            if (mergedSales.containsKey(key)) {
                // Item with the same name and price already exists, merge the quantities and days
                FoodSales mergedItem = mergedSales.get(key);
                mergedItem.setQuantity(mergedItem.getQuantity() + foodSales.getQuantity());
                mergedItem.setDay(mergedItem.getDay() + foodSales.getDay());
            } else {
                // Item doesn't exist, add it to the mergedSales map
                mergedSales.put(key, foodSales);
            }
        }

        // Convert the map values back to a list
        return new ArrayList<>(mergedSales.values());
    }
    
    // A method to merge FoodSales list based on name , price and day
    public ArrayList<FoodSales> mergeFoodSales(ArrayList<FoodSales> foodSalesList) {
        Map<String, FoodSales> mergedMap = new HashMap<>();
        // Iterate through the list and merge items based on name , price and day 
        for (FoodSales currentSales : foodSalesList) {
            String key = currentSales.getName() + "_" + currentSales.getPrice() + "_" + currentSales.getDay();
            FoodSales mergedSales = mergedMap.get(key);
            if (mergedSales == null) {
                // Item doesn't exist, add it to the mergedMap map
                mergedMap.put(key, currentSales);
            } else {
                // Item with the same name , price and day already exists, merge the quantities
                mergedSales.setQuantity(mergedSales.getQuantity() + currentSales.getQuantity());
            }
        }
        return new ArrayList<>(mergedMap.values());
    }
    
    // A method to merge FoodSales list based on name and price , between range of day
    public static ArrayList<FoodSales> mergeFoodSalesByDay(ArrayList<FoodSales> foodSalesList, int startDay, int endDay) {
        Map<String, FoodSales> mergedMap = new HashMap<>();
        // Iterate through the list and merge items
        for (FoodSales foodSales : foodSalesList) {
            // Merging occur only within range of day
                if (foodSales.getDay() >= startDay && foodSales.getDay() <= endDay) {
                String key = foodSales.getName() + "_" + foodSales.getPrice();
                if (mergedMap.containsKey(key)) {
                    FoodSales mergedFoodSales = mergedMap.get(key);
                    mergedFoodSales.setQuantity(mergedFoodSales.getQuantity() + foodSales.getQuantity());
                } else {
                    mergedMap.put(key, new FoodSales(foodSales.getName(), foodSales.getPrice(),
                            foodSales.getDay(), foodSales.getQuantity()));
                }
            }
        }
        return new ArrayList<>(mergedMap.values());
    }
    
    // A method to merge FoodSales list based on name and price , between range of day
    public static Map<Integer, Double> mergeQuantities(ArrayList<FoodSales> foodSalesList) {
        Map<Integer, Double> totalSalesByDay = new HashMap<>();
        // Iterate through the list and merge items
        for (FoodSales foodSales : foodSalesList) {
            int day = foodSales.getDay();
            double totalPrice = foodSales.getPrice() * foodSales.getQuantity();

            if (totalSalesByDay.containsKey(day)) {
                double currentTotal = totalSalesByDay.get(day);
                totalSalesByDay.put(day, currentTotal + totalPrice);
            } else {
                totalSalesByDay.put(day, totalPrice);
            }
        }
        
        return totalSalesByDay;
    }
    
    
    public ArrayList<FoodSales> getSalesByDay(int day){
        ArrayList<FoodSales> saleByDay = new ArrayList<>();
        for(FoodSales sale : foodSales){
            if(sale.getDay()==day)
                saleByDay.add(sale);
        }
        return saleByDay;
    }
    
    
    // A method to read sale record from file
    public ArrayList<FoodSales> readSaleFromFile() {
        ArrayList<FoodSales> sale = new ArrayList<>();
        ArrayList<String> menuName = new ArrayList<>();
        ArrayList<Double> menuPrice = new ArrayList<>();
        ArrayList<Integer> day = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        
        String fileName = menu.getRestaurant() + " Sale.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                menuName.add(data[0]);
                menuPrice.add(Double.parseDouble(data[1]));
                day.add(Integer.parseInt(data[2]));
                quantity.add(Integer.parseInt(data[3]));
            }
                for(int i=0;i<menuName.size();i++){
                    sale.add(new FoodSales(menuName.get(i),menuPrice.get(i),day.get(i),quantity.get(i)));
                }
        
            return sale;
        } catch (IOException e) {
            // Handle file reading error
            System.out.println("Error reading menu data: " + e.getMessage());
        }
        return null;
    }
    public void writeSaleToFile() {
        String fileName = menu.getRestaurant()+ " Sale.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (FoodSales sale : foodSales) {
                writer.write(sale.getName()+","+sale.getPrice()+","+sale.getDay()+","+sale.getDay());
                writer.newLine();
            }
        } catch (IOException e) {
            // Handle file writing error
            System.out.println("Error writing menu data: " + e.getMessage());
        }
    }
    
    public void printSalesRecord(int day){
        System.out.println("Day "+day+" Sales");
        for(FoodSales sale : foodSales){
            if(sale.getDay()==day)
                System.out.println(sale.getName()+" "+sale.getQuantity());
                
        }
        System.out.println("");
    }
    
}

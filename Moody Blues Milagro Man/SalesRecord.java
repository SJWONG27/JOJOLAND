package moodyBlues;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/*The SalesRecord class uses a Map to store the sales data, 
    where the key is the day representing the day, 
    and the value is another Map that maps the food name 
    to the quantity sold on that particular day.*/

public class SalesRecord implements Cloneable {
    private Menu menu;
    public int currentDay;
    private static Map<Integer, Map<String, Integer>> quantityData; // Map<day, Map<foodName, Quantity>>
    private static Map<Integer, Map<String, Double>> salesData; 
    private static Map<Integer, Map<String, Double>> defaultSalesData; 
    
    // The constructor initializes the salesData map.
    
    public SalesRecord(Menu menu) {
        this.menu = menu;
        if (quantityData == null && salesData == null) {
            quantityData = new HashMap<>();
            defaultSalesData = new HashMap<>();
            // currentDay = 8
            for(int i = 1 ; i < 8 ; i++)
                recordSalefromFile(i,menu.getRestaurant());
        }
        salesData = new HashMap<>(defaultSalesData);
    }

    public Map<Integer, Map<String, Integer>> getQuantityData() {
        return quantityData;
    }

    public Map<Integer, Map<String, Double>> getSalesData() {
        return salesData;
    }
    
    public void recordSalefromFile(int day, String restaurantName){
        String filePath = String.format("day%d_order_history.txt", day);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(restaurantName)) {
                    String[] parts = line.split("\\s{2,}");
                    if (parts.length >= 5) {
                        String foodOrder = parts[3].trim();
                        recordQuantity(day,foodOrder);
                        for(Food food : menu.getFoodList()){
                            recordSale(day, food.getName(), food.getPrice(), getQuantity(day,food.getName()));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void printRestaurant(){
        System.out.println(menu.getRestaurant());
    }
    
    /*The recordQuantity method records a quantity by taking the day 
    and the food name as parameters. 
    It retrieves the Map of food sales for that day 
    and increments the quantity by 1. 
    If the food or day is not present in the map, it creates a new entry.*/
    public void recordQuantity(int day, String foodName) {
        Map<String, Integer> foodQuantity = quantityData.getOrDefault(day, new HashMap<>());
        int quantity = foodQuantity.getOrDefault(foodName, 0);
        foodQuantity.put(foodName, quantity+1);
        quantityData.put(day, foodQuantity);
    }

    /*The getQuantity method retrieves the quantity of a specific food 
    sold on a given day. It checks if the day exists in the salesData map 
    and then retrieves the Map of food sales for that date. 
    If the food exists in the map, it returns the quantity sold. 
    Otherwise, it returns 0.*/
    public int getQuantity(int day, String foodName) {
        Map<String, Integer> foodQuantity = quantityData.get(day);
        if (foodQuantity != null) {
            return foodQuantity.getOrDefault(foodName, 0);
        }
        return 0;
    }
        
    /*The getQuantityInRange method takes a startDay ,an endDay and a foodname as parameters. 
    It iterates over the days within the specified range and retrieves the sales data for each day from the salesData map. 
     Then, it checks if the foodName exists in the sales data for that day and accumulates the sales quantity*/
    public int getQuantityInRange(int startDay, int endDay, String foodName) {
        int totalQuantity = 0;
        for (int day = startDay; day <= endDay; day++) {
            Map<String, Integer> foodQuantity = quantityData.get(day);
            if (foodQuantity != null) {
                Integer quantity = foodQuantity.get(foodName);
                if (quantity != null) {
                    totalQuantity += quantity;
                }
            }
        }
        return totalQuantity;
    }
    
    /*The getTopSellingDishes method calculates the total quantity sold for each dish by iterating over the salesData map. 
    It uses a dishQuantityMap to store the cumulative quantities sold for each dish.
    Next, the code converts the dishQuantityMap into a list of map entries and 
    sorts them in descending order based on the quantity sold using the comparingByValue method of Map.Entry.
    Finally, the method retrieves the top K dishes from the sorted list and returns them as a List<String>.*/
    public ArrayList<String> getTopSellingDishes(int k) {
        Map<String, Integer> dishQuantityMap = new HashMap<>();
        // Calculate the total quantity sold for each dish
        for (Map<String, Integer> foodQuantity : quantityData.values()) {
            for (Map.Entry<String, Integer> entry : foodQuantity.entrySet()) {
                String dish = entry.getKey();
                int quantity = entry.getValue();
                dishQuantityMap.put(dish, dishQuantityMap.getOrDefault(dish, 0) + quantity);
            }
        }
        // Sort the dishes based on the quantity sold
        ArrayList<Map.Entry<String, Integer>> sortedDishes = new ArrayList<>(dishQuantityMap.entrySet());
        sortedDishes.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        // Retrieve the top K dishes
        ArrayList<String> topSellingDishes = new ArrayList<>();
        for (int i = 0; i < k && i < sortedDishes.size(); i++) {
            topSellingDishes.add(sortedDishes.get(i).getKey());
        }
        return topSellingDishes;
    }
    
    public void recordSale(int day, String foodName, double foodPrice, int quantity) {
        Map<String, Double> foodSales = defaultSalesData.getOrDefault(day, new HashMap<>());
        double price = foodSales.getOrDefault(foodName, 0.00);
        foodSales.put(foodName, quantity*foodPrice);
        defaultSalesData.put(day, foodSales);
    }
    
    public void setSale(int day, String foodName, double newPrice) {
        Map<String, Double> foodSales = salesData.getOrDefault(day, new HashMap<>());
        foodSales.put(foodName, getQuantity(day,foodName)*newPrice);
        salesData.put(day, foodSales);
    }
    
    public double getSale(int day, String foodName) {
        Map<String, Double> foodSales = salesData.get(day);
        if (foodSales != null) {
            return foodSales.getOrDefault(foodName, 0.0);
        }
        return 0;
    }
    
    public double getSaleInRange(int startDay, int endDay, String foodName) {
        double totalSales = 0;
        for (int day = startDay; day <= endDay; day++) {
            Map<String, Double> foodSales = salesData.get(day);
            if (foodSales != null) {
                Double price = foodSales.get(foodName);
                if (price != null) {
                    totalSales += price;
                }
            }
        }
        return totalSales;
    }
    
    public void copySales(SalesRecord sale){
        quantityData = new HashMap<>(sale.getQuantityData());
        salesData = new HashMap<>(sale.getSalesData());
    }
    
    public void resetSales() {
        salesData = new HashMap<>(defaultSalesData);
    }
}

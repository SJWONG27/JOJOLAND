package restaurantRule;
import java.util.*;

public class JadeGarden {
	public static void main(String[] args) {
		
    	System.out.println("Restaurant: Jane Garden\n");
    	
        // Create a queue of customers and add them to the waiting list
        Queue<Customer> customers = new ArrayDeque<>();
        
        customers.add(new Customer("Jonathan Joestar",20, "Male", "Braised Chicken in Black Bean Sauce"));
        customers.add(new Customer("Joseph Joestar", 18, "Male", "Scrambled Egg White with Milk"));
        customers.add(new Customer("Jotaro Kujo", 17, "Male", "Braised Goose Web with Vermicelli"));
        customers.add(new Customer("Josuke Higashikata", 16, "Male", "Poached Tofu with Dried Shrimps "));
        customers.add(new Customer("Giorno Giovanna", 15, "Male", "Deep-fried Hiroshima Oysters   "));
        customers.add(new Customer("Jolyne Cujoh", 19, "Female", "Braised Goose Web with Vermicelli "));
       
        // Print the list of customers in the waiting list
        System.out.println("Waiting list");
        System.out.println("-+---------------------------------------------------------------------------------------+-");        
        System.out.println("Name\t\t\tAge\t\tGender\t\tOrder");
        System.out.println("-+---------------------------------------------------------------------------------------+-");
        
        for (Customer customer : customers) {
            System.out.printf("%-23s %-15d %-15s %6s \n", customer.getName(), customer.getAge(), customer.getGender(), customer.getOrder());
        }
        
        // Serve customers based on the unique rule
        System.out.println("\nOrder Processing list");
        System.out.println("-+---------------------------------------------------------------------------------------+-");        
        System.out.println("Name\t\t\tAge\t\tGender\t\tOrder");
        System.out.println("-+---------------------------------------------------------------------------------------+-");  
        
        while (!customers.isEmpty()) {
            System.out.println(customers.poll());
            if (!customers.isEmpty()) {
                System.out.println(((ArrayDeque<Customer>) customers).pollLast());
            }
        }
    }
}
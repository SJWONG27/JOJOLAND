package restaurantRule;

import java.util.LinkedList;
import java.util.Queue;

import java.util.*;
public class Libeccio {
    private Queue<Customer> queue;
    private int day;

    public Libeccio() {
        queue = new LinkedList<>();
        day = 1;
    }

    public void addCustomer(Customer customer) {
        queue.add(customer);
    }

    public void serveCustomers() {
        while (!queue.isEmpty()) {
            boolean served = false;
            int size = queue.size();

            // iterate through customers in the queue
            for (int i = 0; i < size; i++) {
                Customer customer = queue.poll();
                int number = i + 1;

                // check if number is a multiple of the current day number
                if (number % day == 0) {
                    // customer is removed from queue and served last
                    queue.add(customer);
                } else {
                    // customer is served
                	System.out.printf("\n%-23s %-15d %-15s %6s", customer.getName(), customer.getAge(), customer.getGender(), customer.getOrder());
                    served = true;
                }
            }

            // if all customers are multiples of the current day number, start over from the beginning
            if (!served) {
                day++;
            }
        }
    }

    public static void main(String[] args) {
    	Libeccio cafe = new Libeccio();
        cafe.addCustomer(new Customer("Jonathan Joestar", 20, "Male", "Braised Chicken in Black Bean Sauce"));
        cafe.addCustomer(new Customer("Joseph Joestar", 18, "Male", "Scrambled Egg White with Milk"));
        cafe.addCustomer(new Customer("Jotaro Kujo", 17, "Male", "Braised Goose Web with Vermicelli"));
        cafe.addCustomer(new Customer("Josuke Higashikata", 16, "Male", "Poached Tofu with Dried Shrimps"));
        cafe.addCustomer(new Customer("Giorno Giovanna", 15, "Male", "Deep-fried Hiroshima Oysters"));
        cafe.addCustomer(new Customer("Jolyne Cujoh", 19, "Female", "Braised Goose Web with Vermicelli"));

        System.out.println("Restaurant: Libeccio\n");
        
        System.out.println("Waiting List");
        System.out.println("-+---------------------------------------------------------------------------------------+-");        
        System.out.println("Name\t\t\tAge\t\tGender\t\tOrder");
        System.out.println("-+---------------------------------------------------------------------------------------+-\n");  
        for (Customer customer : cafe.queue) {
            System.out.println(customer);
        }
        
        System.out.println("\nOrder Processing list");
        System.out.println("-+---------------------------------------------------------------------------------------+-");        
        System.out.println("Name\t\t\tAge\t\tGender\t\tOrder");
        System.out.println("-+---------------------------------------------------------------------------------------+-");  
        cafe.serveCustomers();
    }
}



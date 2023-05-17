package restaurantRule;

import java.util.*;

public class CafeDeuxMagots {
    private static List<Customer> queue = new ArrayList<>();

    public void addCustomer(Customer customer) {
        queue.add(customer);
    }
    
    public Customer getOldestCustomer() {
        if (queue.isEmpty()) {
            return null;
        }
        Customer oldest = queue.get(0);
        for (Customer customer : queue) {
            if (customer.getAge() > oldest.getAge()) {
                oldest = customer;
            }
        }
        return oldest;
    }
    
    public Customer getYoungestCustomer() {
        if (queue.isEmpty()) {
            return null;
        }

        Customer youngest = queue.get(0);
        for (int i = 1; i < queue.size(); i++) {
            Customer customer = queue.get(i);
            if (customer.getAge() < youngest.getAge()) {
                youngest = customer;
            }
        }
        return youngest;
    }

    public void getNullAge(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        if (customer.getAge() == 0) {
            queue.add(customer);
        } else {
            boolean inserted = false;
            for (int i = 0; i < queue.size(); i++) {
                Customer c = queue.get(i);
                if (c.getAge() == 0 || c.getAge() > customer.getAge()) {
                    queue.add(i, customer);
                    inserted = true;
                    break;
                }
            }
            if (!inserted) {
                queue.add(customer);
            }
        }
    }


    public void serveCustomer(Customer customer) {
        // Serve the customer (e.g. take their order, prepare their drink, etc.)
        this.queue.remove(customer);
    }

    // main method
    public static void main(String[] args) { 	 
        CafeDeuxMagots cafe = new CafeDeuxMagots();
        cafe.addCustomer(new Customer("Jonathan Joestar", 20, "Male", "Braised Chicken in Black Bean Sauce"));
        cafe.addCustomer(new Customer("Joseph Joestar", 18, "Male", "Scrambled Egg White with Milk"));
        cafe.addCustomer(new Customer("Jotaro Kujo", 17, "Male", "Braised Goose Web with Vermicelli"));
        cafe.addCustomer(new Customer("Josuke Higashikata", 16, "Male", "Poached Tofu with Dried Shrimps"));
        cafe.addCustomer(new Customer("Giorno Giovanna", 15, "Male", "Deep-fried Hiroshima Oysters"));
        cafe.addCustomer(new Customer("Jolyne Cujoh", 19, "Female", "Braised Goose Web with Vermicelli"));
        
       
        System.out.println("Restaurant: Cafe Deux Magots\n");
        
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
        int index = 0;
        while (!cafe.queue.isEmpty()) {
            Customer customer;
            if (index % 2 == 0) {
                // Serve the oldest customer
                customer = cafe.getOldestCustomer();
            } else {
                // Serve the youngest customer
                customer = cafe.getYoungestCustomer();
            }
            cafe.serveCustomer(customer);        
            System.out.printf("\n%-23s %-15d %-15s %6s",customer.getName() , customer.getAge() ,customer.getGender(), customer.getOrder());
            index++;
        }  
    }
}

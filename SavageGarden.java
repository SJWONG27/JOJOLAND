package restaurantRule;
import java.util.*;

public class SavageGarden {
    private Queue<Customer> queue;
    private int day;

    public SavageGarden() {
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
            int position = 0;

            while (position < size) {
                Customer customer = null;
                Iterator<Customer> iterator = queue.iterator();
                for (int i = 0; i <= position; i++) {
                    customer = iterator.next();
                }

                if ((position + 1) % day == 0) {
                    // customer is served
                    iterator.remove();
                    System.out.printf("\n%-23s %-15d %-15s %6s", customer.getName(), customer.getAge(), customer.getGender(), customer.getOrder());
                    served = true;
                    size--;
                    position = 0;
                } else {
                    // customer says their number
                    position++;
                }
            }
            
            // if all customers are multiples of the current day number, start over from the beginning
            if (!served) {
                day++;
            }
        }
    }
    
    public static void main(String[] args) {
    	SavageGarden cafe = new SavageGarden();
        cafe.addCustomer(new Customer("Jonathan Joestar", 20, "Male", "Braised Chicken in Black Bean Sauce"));
        cafe.addCustomer(new Customer("Joseph Joestar", 18, "Male", "Scrambled Egg White with Milk"));
        cafe.addCustomer(new Customer("Jotaro Kujo", 17, "Male", "Braised Goose Web with Vermicelli"));
        cafe.addCustomer(new Customer("Josuke Higashikata", 16, "Male", "Poached Tofu with Dried Shrimps"));
        cafe.addCustomer(new Customer("Giorno Giovanna", 15, "Male", "Deep-fried Hiroshima Oysters"));
        cafe.addCustomer(new Customer("Jolyne Cujoh", 19, "Female", "Braised Goose Web with Vermicelli"));

        System.out.println("Restaurant: Savage Garden\n");
        
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


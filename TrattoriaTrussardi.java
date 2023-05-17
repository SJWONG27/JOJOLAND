package restaurantRule;

import java.util.ArrayList;
import java.util.List;

public class TrattoriaTrussardi {
    private List<Customer> queue;

    public TrattoriaTrussardi() {
        queue = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        queue.add(customer);
    }

    public void serveCustomers() {
        boolean serveYoungestMan = true;
        boolean serveOldestWoman = true;

        while (!queue.isEmpty()) {
            Customer customerToServe = null;

            // Serve the youngest man
            if (serveYoungestMan) {
                customerToServe = getYoungestMaleCustomer();
                if (customerToServe != null) {
                	System.out.printf("\n%-23s %-15d %-15s %6s",customerToServe.getName() , customerToServe.getAge() ,customerToServe.getGender(), customerToServe.getOrder());
                    queue.remove(customerToServe);
                   // System.out.println("Served customer: " + customerToServe.name + " (" + customerToServe.age + " years old)");
                }
                serveYoungestMan = false;
            }
            // Serve the oldest woman
            else if (serveOldestWoman) {
                customerToServe = getOldestFemaleCustomer();
                if (customerToServe != null) {
                	System.out.printf("\n%-23s %-15d %-15s %6s",customerToServe.getName() , customerToServe.getAge() ,customerToServe.getGender(), customerToServe.getOrder());
                    queue.remove(customerToServe);
                    //System.out.println("Served customer: " + customerToServe.name + " (" + customerToServe.age + " years old)");
                }
                serveOldestWoman = false;
            }
            // Serve the oldest man
            else {
                customerToServe = getOldestMaleCustomer();
                if (customerToServe != null) {
                	System.out.printf("\n%-23s %-15d %-15s %6s",customerToServe.getName() , customerToServe.getAge() ,customerToServe.getGender(), customerToServe.getOrder());
                    queue.remove(customerToServe);
                   // System.out.println("Served customer: " + customerToServe.name + " (" + customerToServe.age + " years old)");
                }

                // If there are no more men, serve the youngest woman
                if (customerToServe == null && !queue.isEmpty()) {
                    customerToServe = getYoungestFemaleCustomer();
                    if (customerToServe != null) {
                    	System.out.printf("\n%-23s %-15d %-15s %6s",customerToServe.getName() , customerToServe.getAge() ,customerToServe.getGender(), customerToServe.getOrder());
                        queue.remove(customerToServe);
                       // System.out.println("Served customer: " + customerToServe.name + " (" + customerToServe.age + " years old)");
                    }
                }

                // Reset the gender flags for the next turn
                serveYoungestMan = true;
                serveOldestWoman = true;
            }
        }
    }


    public Customer getYoungestMaleCustomer() {
        Customer youngestMale = null;
        for (Customer customer : queue) {
            if (customer.getGender().equals("Male") && (youngestMale == null || customer.getAge() < youngestMale.getAge())) {
                youngestMale = customer;
            }
        }
        return youngestMale;
    }

    public Customer getOldestFemaleCustomer() {
        Customer oldestFemale = null;
        for (Customer customer : queue) {
            if (customer.getGender().equals("Female") && (oldestFemale == null || customer.getAge() > oldestFemale.getAge())) {
                oldestFemale = customer;
            }
        }
        return oldestFemale;
    }

    public Customer getOldestMaleCustomer() {
        Customer oldestMale = null;
        for (Customer customer : queue) {
            if (customer.getGender().equals("Male") && (oldestMale == null || customer.getAge() > oldestMale.getAge())) {
                oldestMale = customer;
            }
        }
        return oldestMale;
    }

    public Customer getYoungestFemaleCustomer() {
        Customer youngestFemale = null;
        for (Customer customer : queue) {
            if (customer.getGender().equals("Female") && (youngestFemale == null || customer.getAge() < youngestFemale.getAge())) {
                youngestFemale = customer;
            }
        }
        return youngestFemale;
    }
    
   
    public static void main(String[] args) {
    	TrattoriaTrussardi cafe = new TrattoriaTrussardi();
        cafe.addCustomer(new Customer("Jonathan Joestar", 20, "Male", "Braised Chicken in Black Bean Sauce"));
        cafe.addCustomer(new Customer("Joseph Joestar", 18, "Male", "Scrambled Egg White with Milk"));
        cafe.addCustomer(new Customer("Jotaro Kujo", 17, "Male", "Braised Goose Web with Vermicelli"));
        cafe.addCustomer(new Customer("Josuke Higashikata", 16, "Male", "Poached Tofu with Dried Shrimps"));
        cafe.addCustomer(new Customer("Giorno Giovanna", 15, "Male", "Deep-fried Hiroshima Oysters"));
        cafe.addCustomer(new Customer("Jolyne Cujoh", 19, "Female", "Braised Goose Web with Vermicelli"));
        
        System.out.println("Restaurant: Trattoria Trussardi\n");
        
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

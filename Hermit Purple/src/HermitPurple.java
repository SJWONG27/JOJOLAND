import java.util.*;

class HermitPurple {

    public Scanner sc = new Scanner(System.in);
    public UndirectedGraph<String, Integer> defaultMap = new UndirectedGraph<>();
    public String currentLocation ="Town Hall";
    public Stack<String> history;
    public Stack<String> forwardHistory;
    public int currentDay;


    public HermitPurple(){
        // Adding all cities as a vertex inside the defaultMap
        String[] cities = {"Town Hall", "Morioh Grand Hotel", "Trattoria Trussardi", "Green Dolphin Street Prison",
                            "Angelo Rock", "DIO's Mansion","Vineyard", "Savage Garden", "Polnareff Land", "Cafe Deux Magots",
                            "Jade Garden","San Giorgio Maggiore", "Libeccio", "Joestar Mansion"};
        for (String i : cities) 
            defaultMap.addVertex(i);
        
        // Town Hall as origin
        defaultMap.addEdge("Town Hall", "Morioh Grand Hotel", 5);
        defaultMap.addEdge("Town Hall", "Jade Garden", 5);
        defaultMap.addEdge("Town Hall", "Cafe Deux Magots", 4);
        
        // Morioh Grand Hotel as origin
        defaultMap.addEdge("Morioh Grand Hotel", "Trattoria Trussardi", 6);
        defaultMap.addEdge("Morioh Grand Hotel", "Jade Garden", 3);

        // Jade Garden as origin
        defaultMap.addEdge("Jade Garden", "San Giorgio Maggiore", 2);
        defaultMap.addEdge("Jade Garden", "Joestar Mansion", 2);

        // Cafe Deux Magots as origin
        defaultMap.addEdge("Cafe Deux Magots", "Jade Garden", 3);
        defaultMap.addEdge("Cafe Deux Magots", "Savage Garden", 4);
        defaultMap.addEdge("Cafe Deux Magots", "Polnareff Land", 4);

        // Savage Garden as origin
        defaultMap.addEdge("Savage Garden", "Polnareff Land", 6);
        defaultMap.addEdge("Savage Garden", "Joestar Mansion", 4);
        defaultMap.addEdge("Savage Garden", "Vineyard", 8);

        // Vineyard as origin
        defaultMap.addEdge("Vineyard", "Joestar Mansion", 3);
        defaultMap.addEdge("Vineyard", "Libeccio", 6);
        defaultMap.addEdge("Vineyard", "DIO's Mansion", 3);

        // Libeccio as origin
        defaultMap.addEdge("Libeccio", "Joestar Mansion", 6);
        defaultMap.addEdge("Libeccio", "DIO's Mansion", 2);
        defaultMap.addEdge("Libeccio", "Green Dolphin Street Prison", 3);
        defaultMap.addEdge("Libeccio", "San Giorgio Maggiore", 4);

        // Trattoria Trussardi as origin
        defaultMap.addEdge("Trattoria Trussardi", "San Giorgio Maggiore", 3);
        defaultMap.addEdge("Trattoria Trussardi", "Green Dolphin Street Prison", 6);

        // Angelo Rock as origin
        defaultMap.addEdge("Angelo Rock", "DIO's Mansion", 3);
        defaultMap.addEdge("Angelo Rock", "Green Dolphin Street Prison", 2);

        currentLocation = "Town Hall";
        history = new Stack<>();
        forwardHistory = new Stack<>();
        currentDay = 1;
    }

    public void move(String destination) {
        int distance = defaultMap.getEdgeWeight(currentLocation, destination);
        if (distance == -1) {
            System.out.println("Error: " + destination + " is not adjacent to " + currentLocation);
            return;
        }
        history.push(currentLocation);
        forwardHistory.clear();
        currentLocation = destination;
        System.out.println("Moved to " + destination);
    }

    public void moveBack() {
        if (history.isEmpty()) {
            System.out.println("Error: no history to go back to");
            return;
        }
        forwardHistory.push(currentLocation);
        currentLocation = history.pop();
        System.out.println("Moved back to " + currentLocation);
    }

    public void moveForward() {
        if (forwardHistory.isEmpty()) {
            System.out.println("Error: no forward history to go to");
            return;
        }
        history.push(currentLocation);
        currentLocation = forwardHistory.pop();
        System.out.println("Moved forward to " + currentLocation);
    }

    public void moveTownHall() {
        history.push(currentLocation);
        forwardHistory.clear();
        currentLocation = "Town Hall";
        System.out.println("Moved to Town Hall");
    }

    public void advanceDay() {
        currentDay++;
        currentLocation = "Town Hall";
        history.clear();
        forwardHistory.clear();
        System.out.println("Advanced to Day " + currentDay);
    }

    public ArrayList<String> getNeighbours(){
        return(defaultMap.getNeighboursWithIndex(currentLocation));      
    }
    
}

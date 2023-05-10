import java.util.*;

public class Jojo {
    public static void main(String[] args) {
        HermitPurple game = new HermitPurple();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Day " + game.currentDay);
            System.out.println("Current location: " + game.currentLocation);
            switch(game.currentLocation){
                case "Town Hall":
                System.out.println("[1] Move to: \n" + game.getNeighbours());
                System.out.println("[2] Advance to Next Day");
                System.out.println("[3] Save Game");
                System.out.println("[4] Exit");
                break;
                case "Morioh Grand Hotel":
                System.out.println("[1] Move to: " + game.getNeighbours());
                System.out.println("[2] View President Information");
                System.out.println("[3] The Hand");
                System.out.println("[4] Back");
                System.out.println("[5] Forward");
                System.out.println("[6] Back to Town Hall");
                break;
                case "Trattoria Trussardi":
                System.out.println("[1] Move to: " + game.getNeighbours());
                System.out.println("[2] View Waiting List and Order Processing List");
                System.out.println("[3] View Menu");
                System.out.println("[4] View Sales INformation");
                System.out.println("[5] Milagro Man");
                System.out.println("[6] Back");
                System.out.println("[7] Forward");
                System.out.println("[8] Back to Town Hall");
                break;
                case "Angelo Rock":
                System.out.println("[1] Move to: " + game.getNeighbours());
                System.out.println("[2] View President Information");
                System.out.println("[3] Red Hot Chili Pepper");
                System.out.println("[4] Back");
                System.out.println("[5] Forward");
                System.out.println("[6] Back to Town Hall");
                break;
                default:
                System.out.println("[1] Move to: " + game.getNeighbours());
                System.out.println("[2] View President Information");
                System.out.println("[3] Back");
                System.out.println("[4] Forward");
                System.out.println("[5] Back to Town Hall");
                break;
            }

            String choice = scanner.nextLine();
            // scanner.nextLine(); // consume newline
            switch (choice) {
                case "1":
                System.out.println("Where you want to go? Type it out~");
                String choiceDes = scanner.nextLine();
                game.move(choiceDes);
                case "2":
                if (game.currentLocation.equals("Town Hall")) {
                    // handle "[2] Advance to Next Day" for Town Hall
                } else if (game.currentLocation.equals("Trattoria Trussardi")) {
                    // handle "[2] View Waiting List and Order Processing List" for Trattoria Trussardi
                } else {
                    // handle "[2] View President Information" for other locations
                }
                break;

                case "3":
                if (game.currentLocation.equals("Town Hall")) {
                    // handle "[3] Save Game" for Town Hall
                } else if (game.currentLocation.equals("Morioh Grand Hotel")) {
                    // handle "[3] The Hand" for Morioh Grand Hotel
                } else if (game.currentLocation.equals("Trattoria Trussardi")) {
                    // handle "[3] View Menu" for Trattoria Trussardi
                } else if (game.currentLocation.equals("Angelo Rock")) {
                    // handle "[3] Red Hot Chili Pepper" for Angelo Rock
                } else {
                    game.moveBack();
                }
                break;

                case "4":
                if (game.currentLocation.equals("Town Hall")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                } else if (game.currentLocation.equals("Morioh Grand Hotel") || game.currentLocation.equals("Angelo Rock")) {
                    game.moveBack();
                } else if (game.currentLocation.equals("Trattoria Trussardi")) {
                    // handle "[4] View Sales INformation" for "Trattoria Trussardi"
                }else {
                    game.moveForward();
                }
                break;

                case "5":
                if (game.currentLocation.equals("Morioh Grand Hotel")) {
                    game.moveForward();
                } else if (game.currentLocation.equals("Trattoria Trussardi")) {
                    // handle "[5] Milagro Man" for "Trattoria Trussardi"
                } else if (game.currentLocation.equals("Angelo Rock")) {
                    game.moveForward();
                } else {
                    game.moveTownHall();
                }
                break;

                case "6":
                if (game.currentLocation.equals("Morioh Grand Hotel") || game.currentLocation.equals("Angelo Rock")) {
                    game.moveTownHall();
                } else if (game.currentLocation.equals("Trattoria Trussardi")) {
                    game.moveBack();
                }else {
                    System.out.println("Invalid choice.");
                    break;
                }
                break;

                case "7":
                if (game.currentLocation.equals("Trattoria Trussardi")){
                    game.moveForward();
                }
                else {
                    System.out.println("Invalid choice.");
                    break;
                }

                case "8":
                if (game.currentLocation.equals("Trattoria Trussardi")){
                    game.moveTownHall();;
                }
                else {
                    System.out.println("Invalid choice.");
                    break;
                }

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

}

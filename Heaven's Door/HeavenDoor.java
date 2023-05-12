import java.io.FileInputStream;
import java.util.*;

public class HeavenDoor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
        System.out.println("[1] View Resident’s Profile");
        System.out.println("[2] Sort");
        System.out.println("[3] Exit");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                residentInformation("Polnareff Land");
                break;
            case 2:
                sortResident("Polnareff Land");
                break;
            case 3:
            default:
        }
    }
    }

    public static void residentInformation(String area){
        try{
            Scanner inputStream = new Scanner(new FileInputStream("merged_file.csv"));
            
            System.out.println("+----+----------------------+-------+--------+-----------------+--------------------+----------+----------+----------+-----------+-----------------------+");
            System.out.println("| No | Name                 |  Age  | Gender | Stand           | Destructive Power  | Speed    | Range    | Stamina  | Precision | Development Potential |");
            System.out.println("+----+----------------------+-------+--------+-----------------+--------------------+----------+----------+----------+-----------+-----------------------+");
            int rowNum = 1;
            while(inputStream.hasNextLine()){
                String data = inputStream.nextLine();
                String[] values = data.split(",");
                for (int i = 0; i < values.length; i++) {
                    if (values[i].isEmpty()) {
                        values[i] = "N/A";
                    }
                }
                if(area.equalsIgnoreCase(values[3])){
                    System.out.printf("|%-4s|%-22s|%-7s|%-8s|%-17s|%-20s|%-10s|%-10s|%-10s|%-11s|%-23s|\n", rowNum, values[0], values[1], values[2], values[5], values[6], values[7], values[8], values[9], values[10], values[11]);
                    System.out.println("+----+----------------------+-------+--------+-----------------+--------------------+----------+----------+----------+-----------+-----------------------+");
                    rowNum++;
                }
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void sortResident(String area){
        try{
            Scanner inputStream = new Scanner(new FileInputStream("merged_file.csv"));
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the column to sort by (Stamina, Age, Precision): ");
            String sortBy = sc.nextLine();
            System.out.println("Enter the sorting order (ASC or DESC): ");
            String sortOrder = sc.nextLine();
            List<String[]> residents = new ArrayList<>();
            int rowNum = 1;
            while(inputStream.hasNextLine()){
                String data = inputStream.nextLine();
                String[] values = data.split(",");
                for (int i = 0; i < values.length; i++) {
                    if (values[i].isEmpty()) {
                        values[i] = "N/A";
                    }
                }
                if(area.equalsIgnoreCase(values[3])){
                    residents.add(new String[] {String.valueOf(rowNum), values[0], values[1], values[2], values[5], values[6], values[7], values[8], values[9], values[10], values[11]});
                    rowNum++;
                }
            }
            final int sortColumnIndex;
            switch (sortBy.toLowerCase()) {
                case "name":
                    sortColumnIndex = 1;
                    break;
                case "gender":
                    sortColumnIndex = 3;
                    break;  
                case "stand":
                    sortColumnIndex = 4;
                    break;
                case "destructive power":   
                    sortColumnIndex = 5;
                    break;
                case "speed":
                    sortColumnIndex = 6;
                    break;
                case "range":
                    sortColumnIndex = 7;
                    break;
                case "stamina":
                    sortColumnIndex = 8;
                    break;
                case "precision":
                    sortColumnIndex = 9;
                    break;
                case "age":
                    sortColumnIndex = 2;
                    break;
                case "development potential":
                    sortColumnIndex = 10;
                    break;
                default:
                    System.out.println("Invalid column selected");
                    return;
            }
            Comparator<String[]> comparator = (a, b) -> {
                int result;
                if (sortOrder.equalsIgnoreCase("asc")) {
                    result = a[sortColumnIndex].compareTo(b[sortColumnIndex]);
                } else if (sortOrder.equalsIgnoreCase("desc")) {
                    result = b[sortColumnIndex].compareTo(a[sortColumnIndex]);
                } else {
                    System.out.println("Invalid sorting order selected");
                    return 0;
                }
                return result;
            };
            residents.sort(comparator);
            System.out.println("+----+----------------------+-------+--------+-----------------+--------------------+----------+----------+----------+-----------+-----------------------+");
            System.out.println("| No | Name                 |  Age  | Gender | Stand           | Destructive Power  | Speed    | Range    | Stamina  | Precision | Development Potential |");
            System.out.println("+----+----------------------+-------+--------+-----------------+--------------------+----------+----------+----------+-----------+-----------------------+");
            for (String[] resident : residents) {
                System.out.printf("|%-4s|%-22s|%-7s|%-8s|%-17s|%-20s|%-10s|%-10s|%-10s|%-11s|%-23s|\n", rowNum, resident[1], resident[2], resident[3], resident[4], resident[5], resident[6], resident[7], resident[8], resident[9], resident[10]);
                rowNum++;
                System.out.println("+----+----------------------+-------+--------+-----------------+--------------------+----------+----------+----------+-----------+-----------------------+");
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }
}

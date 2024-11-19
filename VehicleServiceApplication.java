import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class VehicleServiceApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleServiceCenter serviceCenter = new VehicleServiceCenter();
        ServiceLane serviceLane = new ServiceLane();

        while (true) {
            System.out.println("Vehicle Service Center Management System");
            System.out.println("1. Add Customer");
            System.out.println("2. Process Services");
            System.out.println("3. Display Receipts");
            System.out.println("4. View Customer List");
            System.out.println("5. Upload Customer List File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCustomer(serviceCenter, serviceLane, scanner);
                    break;
                case 2:
                    serviceLane.processServices();
                    break;
                case 3:
                    serviceLane.displayCompletedTransactions();
                    break;
                case 4:
                    serviceLane.viewCustomerList();
                    break;
                case 5:
                    uploadCustomerListFile(serviceCenter, serviceLane, scanner);
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCustomer(VehicleServiceCenter serviceCenter, ServiceLane serviceLane, Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter Vehicle Plate Number: ");
        String vehiclePlateNumber = scanner.nextLine();

        CustomerInfo customer = new CustomerInfo(customerId, customerName, vehiclePlateNumber);

        System.out.print("Enter the number of services: ");
        int numServices = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numServices; i++) {
            System.out.print("Enter Service ID: ");
            int serviceId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Service Type: ");
            String serviceType = scanner.nextLine();
            System.out.print("Enter Service Cost: ");
            double serviceCost = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Service Date: ");
            String serviceDate = scanner.nextLine();
            System.out.print("Enter Estimated Completion Time: ");
            String estimatedCompletionTime = scanner.nextLine();

            ServiceInfo service = new ServiceInfo(serviceId, serviceType, serviceCost, serviceDate, estimatedCompletionTime);
            customer.addService(service);
        }

        serviceCenter.addCustomer(customer);
        serviceLane.addCustomerToLane(customer);
        System.out.println("Customer added successfully.");
    }

    private static void uploadCustomerListFile(VehicleServiceCenter serviceCenter, ServiceLane serviceLane, Scanner scanner) {
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                int customerId = Integer.parseInt(tokenizer.nextToken());
                String customerName = tokenizer.nextToken();
                String vehiclePlateNumber = tokenizer.nextToken();

                CustomerInfo customer = new CustomerInfo(customerId, customerName, vehiclePlateNumber);

                while (tokenizer.hasMoreTokens()) {
                    int serviceId = Integer.parseInt(tokenizer.nextToken());
                    String serviceType = tokenizer.nextToken();
                    double serviceCost = Double.parseDouble(tokenizer.nextToken());
                    String serviceDate = tokenizer.nextToken();
                    String estimatedCompletionTime = tokenizer.nextToken();

                    ServiceInfo service = new ServiceInfo(serviceId, serviceType, serviceCost, serviceDate, estimatedCompletionTime);
                    customer.addService(service);
                }

                serviceCenter.addCustomer(customer);
                serviceLane.addCustomerToLane(customer);
            }
            System.out.println("Customer list uploaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

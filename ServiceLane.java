import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

class ServiceLane {
    private Queue<CustomerInfo> lane1;
    private Queue<CustomerInfo> lane2;
    private Queue<CustomerInfo> lane3;
    private List<CustomerInfo> completeList;
    private List<CustomerInfo> allCustomersList; // To keep track of all customers

    public ServiceLane() {
        lane1 = new LinkedList<>();
        lane2 = new LinkedList<>();
        lane3 = new LinkedList<>();
        completeList = new ArrayList<>();
        allCustomersList = new ArrayList<>();
    }

    public void addCustomerToLane(CustomerInfo customer) {
        allCustomersList.add(customer); // Add to all customers list
        if (customer.getServices().size() <= 3) {
            if (lane1.size() <= lane2.size()) {
                lane1.add(customer);
            } else {
                lane2.add(customer);
            }
        } else {
            lane3.add(customer);
        }
    }

    public void processServices() {
        System.out.println("Processing Lane 1:");
        processLane(lane1);
        System.out.println("Processing Lane 2:");
        processLane(lane2);
        System.out.println("Processing Lane 3:");
        processLane(lane3);
    }

    private void processLane(Queue<CustomerInfo> lane) {
        while (!lane.isEmpty()) {
            CustomerInfo customer = lane.poll();
            completeList.add(customer);
            displayReceipt(customer);
        }
    }

    private void displayReceipt(CustomerInfo customer) {
        double totalCost = 0;
        for (ServiceInfo service : customer.getServices()) {
            totalCost += service.getServiceCost();
        }
        System.out.printf("%-15d %-25s %-20s %-15.2f%n", customer.getCustomerId(), customer.getCustomerName(), customer.getVehiclePlateNumber(), totalCost);
        System.out.println("---------------------------------------------------------------");
    }

    public void displayCompletedTransactions() {
        System.out.println("List of Receipts:");
        System.out.printf("%-15s %-25s %-20s %-15s%n", "Customer ID", "Customer Name", "Vehicle Plate Number", "Total Service Cost");
        System.out.println("---------------------------------------------------------------");
        for (CustomerInfo customer : completeList) {
            displayReceipt(customer);
        }
    }

    public void viewCustomerList() {
        System.out.println("Customer List:");
        System.out.printf("%-15s %-25s %-20s %-10s%n", "Customer ID", "Customer Name", "Vehicle Plate Number", "Status");
        System.out.println("---------------------------------------------------------------");
        for (CustomerInfo customer : allCustomersList) {
            String status = completeList.contains(customer) ? "Processed" : "Not Processed";
            System.out.printf("%-15d %-25s %-20s %-10s%n", customer.getCustomerId(), customer.getCustomerName(), customer.getVehiclePlateNumber(), status);
        }
    }
}

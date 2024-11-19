import java.util.LinkedList;
import java.util.List;

class CustomerInfo {
    private int customerId;
    private String customerName;
    private String vehiclePlateNumber;
    private List<ServiceInfo> services;

    public CustomerInfo(int customerId, String customerName, String vehiclePlateNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.vehiclePlateNumber = vehiclePlateNumber;
        this.services = new LinkedList<>();
    }

    // Getters and setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public List<ServiceInfo> getServices() {
        return services;
    }

    public void addService(ServiceInfo service) {
        this.services.add(service);
    }
}

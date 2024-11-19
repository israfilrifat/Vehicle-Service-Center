import java.util.LinkedList;
import java.util.List;

class VehicleServiceCenter {
    private List<CustomerInfo> customerList;

    public VehicleServiceCenter() {
        customerList = new LinkedList<>();
    }

    public void addCustomer(CustomerInfo customer) {
        customerList.add(customer);
    }

    public List<CustomerInfo> getCustomerList() {
        return customerList;
    }
}

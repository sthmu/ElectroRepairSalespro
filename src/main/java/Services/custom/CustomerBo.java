package Services.custom;

import DTO.CustomerDto;
import Dao.custom.impl.CustomerDaoimpl;
import Entity.Customer;
import Services.SuperBo;

import java.util.LinkedList;
import java.util.List;

public interface CustomerBo extends SuperBo {
     List<CustomerDto> customerList = initializeCustomerList();

    private static List<CustomerDto> initializeCustomerList() {
        List<Customer> entityList = CustomerDaoimpl.getAll();
        List<CustomerDto> tempDtoList = new LinkedList<>();
        for (Customer customer : entityList) {

            tempDtoList.add(new CustomerDto(
                    customer.getId(),
                    customer.getEmail(),
                    customer.getName(),
                    customer.getPhone()
            ));
        }
        return tempDtoList;
    }
    static CustomerDto isInList(int custId) {
        for (CustomerDto customerDto : customerList) {
            if (custId == (customerDto.getId())) {
                return customerDto;
            }
        }
        return null;
    }

    boolean saveCustomer(CustomerDto customerDto);

    void updateCustomerList(CustomerDto customerDto);
}

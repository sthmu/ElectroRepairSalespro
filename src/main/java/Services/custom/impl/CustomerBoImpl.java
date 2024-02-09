package Services.custom.impl;

import Dao.custom.impl.CustomerDaoimpl;
import DTO.CustomerDto;
import Entity.Customer;
import Services.custom.CustomerBo;
import javafx.scene.control.Alert;

import java.util.LinkedList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    private static List<CustomerDto> customerList = initializeCustomerList();

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

//    public static String getGeneratedId() {
//        int lastCodeNum = 0;
//        for (CustomerDto customerDto : customerList) {
//            int custNum = Integer.parseInt(customerDto.getId().substring(1, 5));
//            if (custNum > lastCodeNum) {
//                lastCodeNum = custNum;
//            }
//            System.out.println(custNum);
//        }
//        String LastCode = (++lastCodeNum) + "";
//
//        for (int i = (LastCode).length(); i < 4; i++) {
//            LastCode = "0" + LastCode;
//        }
//
//        return "C" + LastCode;
//    }

    @Override
    public boolean saveCustomer(CustomerDto customerDto) {
        Customer customerEntity = new Customer(
                0,
                customerDto.getName(),
                customerDto.getEmail(),
                customerDto.getPhone()
        );
        boolean isSaved = CustomerDaoimpl.save(customerEntity);
        if (isSaved) {
            // new Alert(Alert.AlertType.INFORMATION, "Customer Successfully Saved!").show();
            customerDto.setId(customerEntity.getId());
            updateCustomerList(customerDto);

            return true;
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Customer Failed to be Saved!").show();
            return false;
        }
    }

    private void updateCustomerList(CustomerDto customerDto) {
        CustomerDto customerFromList = isInList(customerDto);
        if (customerFromList == null) {
            customerList.add(customerDto);
        } else {
            customerFromList.setPhone(customerDto.getPhone());
            customerFromList.setName(customerDto.getName());
        }
    }

    public static CustomerDto isInList(CustomerDto customer) {

        for (CustomerDto customerDto : customerList) {
            if (customer.getId() == (customerDto.getId())) {
                return customerDto;
            }
        }
        for (CustomerDto customerDto : customerList) {
            if (customer.getEmail().equalsIgnoreCase(customerDto.getEmail())) {
                return customerDto;
            }
        }
        return null;
    }

    public static CustomerDto isInList(int custId) {
        for (CustomerDto customerDto : customerList) {
            if (custId == (customerDto.getId())) {
                return customerDto;
            }
        }
        return null;
    }

    public static CustomerDto isInList(String email) {
        for (CustomerDto customerDto : customerList) {
            if (email.equalsIgnoreCase(customerDto.getEmail())) {
                return customerDto;
            }
        }
        return null;
    }
}

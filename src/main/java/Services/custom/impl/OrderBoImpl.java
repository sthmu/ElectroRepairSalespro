package Services.custom.impl;

import Dao.custom.impl.OrderDaoImpl;
import DTO.OrderDto;
import Entity.CategoryItem;
import Entity.Customer;
import Entity.Orders;
import javafx.scene.control.Alert;

import java.util.LinkedList;
import java.util.List;

public class OrderBoImpl {

    private static List<OrderDto> orderList = initializeOrderList();

    private static List<OrderDto> initializeOrderList() {
        List<Orders> entityList = OrderDaoImpl.getAll();
        List<OrderDto> tempDtoList = new LinkedList<>();
        for (Orders orders : entityList) {

            tempDtoList.add(new OrderDto(
                    orders.getOrderId(),
                    orders.getDate(),
                    orders.getCustomer().getId(),
                    orders.getItem().getCatItemCode(),
                    orders.getStatus(),
                    orders.getDescription()
            ));
        }
        return tempDtoList;
    }

    private OrderDto isInList(OrderDto order) {

        for (OrderDto orderDto : orderList) {
            if (order.getOrderId() == (orderDto.getOrderId())) {
                return orderDto;
            }
        }
        return null;
    }

    public boolean placeOrder(OrderDto order) {
        return saveOrder(order);
    }

    private void sendEmail(OrderDto order) {
    }

    public boolean saveOrder(OrderDto order) {
        Customer customer=new Customer();
        customer.setId(order.getCustId());

        CategoryItem categoryItem=new CategoryItem();
        categoryItem.setCatItemCode(order.getItemCategoryCode());

        Orders ordersEntity =new Orders(
                order.getDate(),
                customer,
                categoryItem,
                "Processing",
                order.getDescription()
        );
        boolean isSaved = OrderDaoImpl.save(ordersEntity);
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Order Successfully Placed!").show();
            order.setOrderId(ordersEntity.getOrderId());
            updateOrderList(order);

            return true;
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Item Failed to be Saved!").show();
            return false;
        }
    }

    private void updateOrderList(OrderDto order) {
        OrderDto orderFromList = isInList(order);
        if (orderFromList == null) {
            orderList.add(order);
        } else {
            orderFromList.setDescription(order.getDescription());
            orderFromList.setStatus(order.getStatus());
        }

    }


}

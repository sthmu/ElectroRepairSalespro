package Services.custom.impl;

import DTO.OrderDto;
import Dao.custom.impl.OrderDaoImpl;
import Entity.CategoryItem;
import Entity.Customer;
import Entity.Orders;
import javafx.scene.control.Alert;

import java.util.List;

public class OrderBoImpl implements Services.custom.OrderBo {



    @Override
    public OrderDto isInList(OrderDto order) {

        for (OrderDto orderDto : orderList) {
            if (order.getOrderId() == (orderDto.getOrderId())) {
                return orderDto;
            }
        }
        return null;
    }

    @Override
    public boolean placeOrder(OrderDto order) {
        return saveOrder(order);
    }

    private void sendEmail(OrderDto order) {
    }

    @Override
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

    @Override
    public List<OrderDto> getAll() {
        return orderList;
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

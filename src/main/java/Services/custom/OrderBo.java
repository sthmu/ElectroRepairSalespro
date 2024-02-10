package Services.custom;

import DTO.OrderDto;
import Dao.custom.impl.OrderDaoImpl;
import Entity.Orders;
import Services.SuperBo;

import java.util.LinkedList;
import java.util.List;

public interface OrderBo extends SuperBo {
     static final List<OrderDto> orderList = initializeOrderList();
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

    static OrderDto isInList(int orderId){
        for(OrderDto orderDto:orderList){
            if(orderId==(orderDto.getOrderId())){
                return orderDto;
            }
        }
        return null;
    }
    OrderDto  isInList(OrderDto order);

    boolean placeOrder(OrderDto order);

    boolean saveOrder(OrderDto order);

    List<OrderDto> getAll();
}

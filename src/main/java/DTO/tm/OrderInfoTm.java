package DTO.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderInfoTm extends RecursiveTreeObject<OrderInfoTm> {
    private int orderId;
    private String date;
    private String name;
    private String itemName;
}

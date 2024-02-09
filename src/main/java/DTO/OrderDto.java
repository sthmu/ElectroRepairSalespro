package DTO;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDto {

    private Integer orderId=null;
    private String date;
    private int custId;
    private String itemCategoryCode;
    private String status;
    private String description;

    public OrderDto(String date, int custId, String itemCategoryCode, String status, String description) {
        this.date = date;
        this.custId = custId;
        this.itemCategoryCode = itemCategoryCode;
        this.status = status;
        this.description = description;
    }
}

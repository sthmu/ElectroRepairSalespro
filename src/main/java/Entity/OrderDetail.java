package Entity;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class OrderDetail {
    @EmbeddedId
    private OrderDetailsKey id;

    @ManyToOne
            @MapsId()
    Item item;

    private int qty;
    private double unitPrice;

}

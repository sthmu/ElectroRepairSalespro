package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    private String orderId;

    @Column(name ="date")
    private String date;
    @ManyToOne
    @JoinColumn(name="id")
    private String custId;
    private List<OrderDetail> list;
}

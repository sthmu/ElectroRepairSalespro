package Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int orderId;

    @Column(name ="date")
    private String date;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name="item_code")
    private CategoryItem item;

    private Boolean status;

    @Column(name="description")
    private String description;

    public Orders(String date, Customer customer, CategoryItem item, Boolean status, String description) {
        this.date = date;
        this.customer = customer;
        this.item = item;
        this.status = status;
        this.description = description;
    }
}

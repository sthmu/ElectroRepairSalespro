package Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name="categoryitem")
public class CategoryItem {
    @Id
    private String catItemCode;
    @Column(name = "title")
    private String catItemName;
    @Column(name = "Category")
    private String category;
    @Column(name="imgUrl")
    private String imgUrl;

}

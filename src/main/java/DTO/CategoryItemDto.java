package DTO;

import javafx.scene.image.Image;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryItemDto {
    private String catItemCode;
    private String catItemName;

    public CategoryItemDto(String catItemCode) {
        this.catItemCode = catItemCode;
    }

    private Image image;
    private String category;

}

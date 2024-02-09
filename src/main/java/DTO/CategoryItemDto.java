package DTO;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@AllArgsConstructor
@Getter
@Setter
@ToString

public class CategoryItemDto {
    private String catItemCode;
    private String catItemName;

    private Image image;
    private String category;

}

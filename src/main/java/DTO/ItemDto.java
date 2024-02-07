package DTO;

import javafx.scene.image.Image;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ItemDto {
    private String code;
    private String Title;
    private String description;
    private Image image;
    private String category;



}

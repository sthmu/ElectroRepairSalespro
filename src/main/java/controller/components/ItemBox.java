package controller.components;

import DTO.CategoryItemDto;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

public class ItemBox extends VBox {
    @Getter
    @Setter
    private CategoryItemDto item;

    public ItemBox(CategoryItemDto ItemDto) {
        super();
        this.item = ItemDto;
    }

}

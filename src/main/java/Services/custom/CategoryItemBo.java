package Services.custom;

import DTO.CategoryItemDto;
import Dao.custom.impl.CategoryItemDaoImpl;
import Entity.CategoryItem;
import Services.SuperBo;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

public interface CategoryItemBo extends SuperBo {
     static List<CategoryItemDto> itemList = initializeItemList();
    static List<CategoryItemDto> initializeItemList() {

        List<CategoryItem> entityList = CategoryItemDaoImpl.getAll();
        List<CategoryItemDto> tempModelList = new LinkedList<>();
        for (CategoryItem item : entityList) {

            tempModelList.add(new CategoryItemDto(
                    item.getCatItemCode(),
                    item.getCatItemName(),
                    new Image(item.getImgUrl()),
                    item.getCategory()
            ));
        }
        return tempModelList;
    }

    static CategoryItemDto isInList(CategoryItemDto item) {
        for(CategoryItemDto itemInList:itemList){
            if(item.getCatItemCode().equalsIgnoreCase(itemInList.getCatItemCode())){
                return itemInList;
            }
        }
        return null;
    }
    static CategoryItemDto isInList(String code){
        return isInList(new CategoryItemDto(code));
    }
    boolean saveItem(CategoryItemDto item);

    void updateItemList(CategoryItemDto item);

    void removeFromItemList(String code);

    void deleteCategoryItem(String catItemCode);
}

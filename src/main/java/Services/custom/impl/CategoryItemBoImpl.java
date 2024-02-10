package Services.custom.impl;

import Dao.DaoFactory;
import Dao.DaoType;
import Dao.custom.CategoryItemDao;
import Dao.custom.impl.CategoryItemDaoImpl;
import DTO.CategoryItemDto;
import Entity.CategoryItem;
import Services.custom.CategoryItemBo;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

public class CategoryItemBoImpl implements CategoryItemBo {

    private CategoryItemDao categoryItemDao= DaoFactory.getInstance().getDao(DaoType.CATEGORY_ITEM_DAO);


    private static List<CategoryItemDto> itemList = initializeItemList();
    private static List<CategoryItemDto> initializeItemList() {

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

    public CategoryItemDto isInList(CategoryItemDto item) {

        for(CategoryItemDto itemInList:itemList){
            if(item.getCatItemCode().equalsIgnoreCase(itemInList.getCatItemCode())){
                return itemInList;
            }
        }
        return null;
    }
    public static CategoryItemDto isInList(String code) {

        for(CategoryItemDto itemInList:itemList){
            if(code.equalsIgnoreCase(itemInList.getCatItemCode())){
                return itemInList;
            }
        }
        return null;
    }
    @Override
    public boolean saveItem(CategoryItemDto item) {
        boolean isSaved = CategoryItemDaoImpl.save(new CategoryItem(item.getCatItemCode(),item.getCatItemName(), item.getCategory(), item.getImage().getUrl()));
        if (isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Item Successfully Saved!").show();

            updateItemList(item);

            return true;
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Item Failed to be Saved!").show();
            return false;
        }
    }

    @Override
    public void updateItemList(CategoryItemDto item) {
        CategoryItemDto itemFromList= isInList(item);
        if(itemFromList==null){
            itemList.add(item);
        }
        else{
            itemFromList.setCatItemName(item.getCatItemName());
            itemFromList.setImage(item.getImage());
            itemFromList.setCategory(item.getCategory());
        }

    }
    public static List<CategoryItemDto> getItemList() {
        return itemList;
    }





    @Override
    public void removeFromItemList(String code){
        for(CategoryItemDto itemInList:itemList){
            if(code.equalsIgnoreCase(itemInList.getCatItemCode())){
                itemList.remove(itemInList);
            }
        }

    }

    @Override
    public void deleteCategoryItem(String catItemCode) {

    }


    public static String getGeneratedCode() {
        int lastCodeNum=0;
        for(CategoryItemDto item:itemList){
            int curCode=Integer.parseInt(item.getCatItemCode().substring(1,5));
            if(curCode>lastCodeNum){
                lastCodeNum=curCode;
            }
            System.out.println(curCode);
        }
        String LastCode=(++lastCodeNum)+"";

        for(int i=(LastCode).length();i<4;i++){
            LastCode="0"+LastCode;
        }

        return "C"+LastCode;
    }
}

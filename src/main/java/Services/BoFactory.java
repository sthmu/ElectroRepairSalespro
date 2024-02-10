package Services;

import Services.custom.impl.CategoryItemBoImpl;
import Services.custom.impl.CustomerBoImpl;
import Services.custom.impl.OrderBoImpl;

public class BoFactory {
    private BoFactory(){}

    private static BoFactory boFactory;
    public static BoFactory getInstance(){
        return boFactory!=null?boFactory:(boFactory=new BoFactory());
    }
    public <T extends SuperBo>T getBo(BoType type){
        switch(type){
            case CATEGORY_ITEM_BO:return (T)new CategoryItemBoImpl();
            case ORDER_BO:return (T) new OrderBoImpl();
            case CUSTOMER_BO:return (T) new CustomerBoImpl();

        }
        return null;
    }

}

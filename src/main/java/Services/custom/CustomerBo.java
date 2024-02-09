package Services.custom;

import DTO.CustomerDto;
import javafx.fxml.Initializable;

public interface CustomerBo {
    boolean saveCustomer(CustomerDto customerDto);

}

package DIContainer;

import BusinessLogic.BusinessLogicController;
import DataAccess.Repository;

public class DIContainer {
    private static DIContainer container;

    private DIContainer() {
    }

    public static DIContainer getInstance() {
        if (container == null) container = new DIContainer();
        return container;
    }

    public BusinessLogicController createBean(){
        return new BusinessLogicController(Repository.getInstance());
    }
}

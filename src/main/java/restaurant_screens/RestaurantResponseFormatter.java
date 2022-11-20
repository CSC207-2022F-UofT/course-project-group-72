package restaurant_screens;

import restaurant_use_case.RestaurantResponseModel;

public class RestaurantResponseFormatter implements RestaurantPresenter{

    private final IFrame view;

    public RestaurantResponseFormatter(IFrame view) {
        this.view = view;
    }

    @Override
    public RestaurantResponseModel prepareSuccessView(RestaurantResponseModel unformatted) {
        unformatted.setOperation("Successfully " + unformatted.getOperation() + " " +
                unformatted.getRestaurant().getName());
        view.refresh(unformatted.getRestaurant());
        return unformatted;
    }

    @Override
    public RestaurantResponseModel prepareFailView(String error) {
        throw new RestaurantInteractionFailed(error);
    }
}

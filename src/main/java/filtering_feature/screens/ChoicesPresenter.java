package filtering_feature.screens;

/**
 * The interface for the presenter so that interactors do not need to know which presenter they are using
 */
public interface ChoicesPresenter {
    ChoicesResponseModel prepareSuccessView(ChoicesResponseModel responseModel);

    ChoicesResponseModel prepareFailView(String error);

}
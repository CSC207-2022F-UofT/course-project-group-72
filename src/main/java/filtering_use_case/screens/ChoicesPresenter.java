package filtering_use_case.screens;

public interface ChoicesPresenter {
    ChoicesResponseModel prepareSuccessView(ChoicesResponseModel responseModel);

    ChoicesResponseModel prepareFailView(String error);

}
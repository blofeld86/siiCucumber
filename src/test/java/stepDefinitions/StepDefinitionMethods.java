package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ReqBuild;

public class StepDefinitionMethods {
    @Given("Preparing data for creating a new post")
    public void preparingDataForCreatingANewPost() {
        ReqBuild.prepareDataToCreateProject();
    }

    @When("Creating new post")
    public void creatingNewPost() {
        ReqBuild.creatingNewProject();
    }

    @Then("User can see the response with the project details")
    public void userCanSeeTheResponseWithTheProjectDetails() {
        ReqBuild.verifyCreatedPost();
    }

    @Given("Preparing data for a GET request")
    public void preparingDataForAGETRequest() {
        ReqBuild.prepareDataForGetRequest();
    }

    @When("Trying to get new created post")
    public void tryingToGetNewCreatedPost() {
        ReqBuild.getNewProject();
    }

    @Then("User successfully got the right project")
    public void userSuccessfullyGotTheRightProject() {
        ReqBuild.verifyGID();
    }

    @Given("Preparing data for a PUT request")
    public void preparingDataForAPUTRequest() {
        ReqBuild.preparingDataForPutRequest();
    }

    @When("Updating a post on a name value")
    public void updatingAPostOnANameValue() {
        ReqBuild.updatingRequest();
    }

    @Then("User see that the {string} on the post is changed")
    public void userSeeThatTheOnThePostIsChanged(String arg0, DataTable dataTable) {
        ReqBuild.verifyPutMethodResponse(arg0,dataTable);
    }

    @Given("Preparing data for a DELETE request")
    public void preparingDataForADELETERequest() {
        ReqBuild.prepareDataForDeletingPost();
    }

    @When("Deleting previously created post")
    public void deletingPreviouslyCreatedPost() {
        ReqBuild.deletingNewPost();
    }

    @Then("Post is correctly deleted")
    public void postIsCorrectlyDeleted() {
        ReqBuild.verifyingDeleting();
    }

    @Then("Can not get a post because it's already deleted")
    public void canNotGetAPostBecauseItSAlreadyDeleted() {
        ReqBuild.verifyGetPostAfterDeleting();
    }
}

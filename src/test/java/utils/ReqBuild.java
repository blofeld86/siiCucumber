package utils;

import data.DataStore;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import model.JsonPropertiesModel;
import model.JsonPropertiesReader;
import org.apache.http.HttpStatus;

import java.io.File;

import static io.restassured.RestAssured.given;
import static java.lang.System.getProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ReqBuild {

    private static Response response;
    private static ResponseSpecBuilder resSpecBld = new ResponseSpecBuilder();
    private static RequestSpecBuilder reqSpecBld = new RequestSpecBuilder();


    private static JsonPropertiesModel jpm = JsonPropertiesReader.readModel();

    public static RequestSpecification prepareDataToCreateProject() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        reqSpecBld.setContentType(ContentType.JSON);
        reqSpecBld.setBaseUri(jpm.getBaseUrl());
        reqSpecBld.setBasePath(jpm.getPostPath());
        RequestSpecification rs = reqSpecBld.build();
        log.info(">>>>>>>>>> Successfully prepared the data for creating a new post");
        return rs;
    }

    public static Response creatingNewProject(){
        response =
                given().
                        spec(prepareDataToCreateProject()).
                        pathParam(jpm.getWorkspaceIdName(),jpm.getWorkspaceID()).
                        body(new File(getProperty("user.dir")+jpm.getJsonExOne())).
                        auth().
                        oauth2(jpm.getToken()).
                when().
                        post().
                then().
                        extract().
                        response();
        DataStore.GID = response.body().jsonPath().get(jpm.getGidJsonPath());
        log.info(">>>>>>>>>> Successfully created a new post");
        return response;
    }

    public static void verifyCreatedPost(){
        resSpecBld.expectStatusCode(HttpStatus.SC_CREATED);
    }

    //#######################################################//

    public static RequestSpecification prepareDataForGetRequest() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        reqSpecBld.setContentType(ContentType.JSON);
        reqSpecBld.setBaseUri(jpm.getBaseUrl());
        reqSpecBld.setBasePath(jpm.getPutGetDeletePath() + DataStore.GID);
        RequestSpecification rs = reqSpecBld.build();
        log.info(">>>>>>>>>> Successfully prepared the data for getting a new post");
        return rs;
    }

    public static void getNewProject(){
        response = given().
                          spec(prepareDataForGetRequest()).
                          auth().
                          oauth2(jpm.getToken()).
                   when().
                          get();
        log.info(">>>>>>>>>>> Successfully got a new post");
    }

    public static void verifyGID(){
        resSpecBld.expectStatusCode(HttpStatus.SC_OK);
        String expectedGID = response.body().jsonPath().get(jpm.getGidJsonPath());
        assertThat(DataStore.GID,equalTo(expectedGID));
    }

    public static void verifyGetPostAfterDeleting(){
        resSpecBld.expectStatusCode(HttpStatus.SC_NOT_FOUND);
        log.info(">>>>>>>>>> Successfully verified that the new post no longer exist");
    }

    //###################################################################

    public static RequestSpecification preparingDataForPutRequest(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        reqSpecBld.setContentType(ContentType.JSON);
        reqSpecBld.setBaseUri(jpm.getBaseUrl());
        reqSpecBld.setBasePath(jpm.getPutGetDeletePath() + DataStore.GID);
        RequestSpecification rs = reqSpecBld.build();
        log.info(">>>>>>>>>> Successfully prepared data for the updating the new post");
        return rs;
    }

    public static Response updatingRequest(){
        response = given().
                           spec(preparingDataForPutRequest()).
                           body(new File(getProperty("user.dir")+jpm.getJsonExTwo())).
                           auth().
                           oauth2(jpm.getToken()).
                   when().
                           put().
                   then().
                           extract().
                           response();
        DataStore.NAME = response.body().jsonPath().get(jpm.getNameJsonPath());
        log.info(">>>>>>>>>> Successfully updated the new post");
        return response;
    }


    public static void verifyPutMethodResponse(String name, DataTable table){
        resSpecBld.expectStatusCode(HttpStatus.SC_OK);
        var body = table.cells();
        assertEquals(DataStore.NAME,body.get(1).get(0));
    }

    //################################################################//

    public static RequestSpecification prepareDataForDeletingPost(){
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        reqSpecBld.setContentType(ContentType.JSON);
        reqSpecBld.setBaseUri(jpm.getBaseUrl());
        reqSpecBld.setBasePath(jpm.getPutGetDeletePath() + DataStore.GID);
        RequestSpecification rs = reqSpecBld.build();
        log.info(">>>>>>>>>> Successfully prepared data for the deleting the new post");
        return rs;
    }

    public static void deletingNewPost(){
        response = given().
                           spec(prepareDataForDeletingPost()).
                           auth().
                           oauth2(jpm.getToken()).
                    when().
                           delete();
        log.info(">>>>>>>>>> Successfully deleted the new post");

    }

    public static void verifyingDeleting(){
        response.then().statusCode(HttpStatus.SC_OK);
    }



}



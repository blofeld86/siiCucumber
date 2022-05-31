package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JsonPropertiesReader {

    private static JsonPropertiesModel jsonModel;

    public static JsonPropertiesModel readModel(){
        try {
            ObjectMapper om = new ObjectMapper();
            jsonModel = om.readValue(new File(System.getProperty("user.dir")+"\\src\\main\\jsonResources\\properties.json"),JsonPropertiesModel.class);
            log.info("Successfully loaded properties: "+ jsonModel);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return jsonModel;
    }
}

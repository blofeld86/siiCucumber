package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
public class JsonPropertiesModel {

    private boolean active;
    private String baseUrl;
    private String workspaceIdName;
    private String workspaceID;
    private String postPath;
    private String putGetDeletePath;
    private String token;
    private String jsonExOne;
    private String jsonExTwo;
    private String gidJsonPath;
    private String nameJsonPath;
    private String actualName;
}

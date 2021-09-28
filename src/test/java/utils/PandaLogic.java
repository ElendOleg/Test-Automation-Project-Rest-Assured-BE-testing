package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class PandaLogic {
    private static final String BASE_URL = "https://api.pandascore.co";
    private static RequestSpecification REQ_SPEC;

    private String token = "Bearer nx-MFo26YgQkahiP5JcD6fuDtD0KCQlIxJs3ka5ZXSgNto81K0M";

    private PandaLogic() {
        REQ_SPEC = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Authorization",token)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static PandaLogic authorizeToPandascope() {
        return new PandaLogic();
    }

    public static RequestSpecification getReqSpec(){
        return REQ_SPEC;
    }


}

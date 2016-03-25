package tpv.rest;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRestService {
    protected String _corsHeaders;
//    protected ServiceQuotaChecker checker = null;

    private static final ObjectMapper SORTED_MAPPER = new ObjectMapper();
    static {
        SORTED_MAPPER.configure(SerializationConfig.Feature.SORT_PROPERTIES_ALPHABETICALLY, true);
    }

    protected Response makeCORS(ResponseBuilder req) {
        ResponseBuilder rb = req
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE, PUT")
                .header("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization, Authentication")
                .header("Access-Control-Allow-Credentials", "true");

        return rb.build();
    }

    protected Response ok(Object obj) throws IOException {
        if(obj instanceof byte[]) {
            return Response.ok(new ByteArrayInputStream((byte[]) obj)).build();
        }
        else {
            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(obj);
            return ok(jsonResponse);
        }
    }

    protected Response ok(String str) {
        ResponseBuilder responseBuilder;

//        try {
//            if (checker != null) {
//                checker.check();
//            }

            responseBuilder = Response.ok(str);
        /*} catch (OverQuotaLimitException e) {
            responseBuilder = Response
                    .status(403)
                    .type("application/json")
                    .entity(e.getMessage());
        }*/

        return makeCORS(responseBuilder);
    }

    protected Response okIgnoringFieldNames(Object obj, String ...ignorableFieldNames) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.getSerializationConfig().addMixInAnnotations(Object.class, PropertyFilterMixin.class);

        FilterProvider filters = new SimpleFilterProvider().addFilter("filter properties by name", SimpleBeanPropertyFilter.serializeAllExcept(ignorableFieldNames));
        ObjectWriter writer = mapper.writer(filters);
        String jsonResponse = writer.writeValueAsString(obj);
        return ok(jsonResponse);
    }

    protected Response error(String error) {
        Map<String, String> message = new HashMap<>();
        message.put("error", error);

        return makeCORS(
                Response.serverError()
                        .type(MediaType.APPLICATION_JSON)
                        .entity(message)
        );
    }

    protected Response error(Object error) {
        Map<String, Object> message = new HashMap<>();
        message.put("error", error);

        return makeCORS(
                Response.serverError()
                        .type("application/json")
                        .entity(message)
        );
    }

    protected Response unauthorized(Object error) {
        final ResponseBuilder responseBuilder = Response
                .status(Response.Status.UNAUTHORIZED)
                .type("application/json")
                .entity(error);


        return makeCORS(responseBuilder);
    }

    @OPTIONS
    public Response corsMyResource(@HeaderParam("Access-Control-Request-Headers") String requestH) {
        _corsHeaders = requestH;
        return makeCORS(Response.ok("OK"));
    }

//    public void setChecker(ServiceQuotaChecker checker) {
//        this.checker = checker;
//    }

    protected String convertNode(final JsonNode node) throws IOException {
        final Object obj = SORTED_MAPPER.treeToValue(node, Object.class);
        final String json = SORTED_MAPPER.writeValueAsString(obj);
        return json;
    }

    protected String getMuleHome() {
        final Map<String, String> getenv = System.getenv();
        final String muleHome = getenv.get("MULE_HOME");

        return muleHome;
    }
}

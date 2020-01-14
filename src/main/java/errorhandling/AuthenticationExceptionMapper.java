package errorhandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    ServletContext context;

    @Override
    public Response toResponse(AuthenticationException ex) {
        Logger.getLogger(AuthenticationExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);
        //Response.StatusType type = getStatusType(ex);
        ExceptionDTO err;

            err = new ExceptionDTO(403,ex.getMessage());
        
        return Response.status(403)
                .entity(gson.toJson(err))
                .type(MediaType.APPLICATION_JSON).
                build();
    }

    private Response.StatusType getStatusType(Throwable ex) {
        if (ex instanceof WebApplicationException) {
            return ((WebApplicationException) ex).getResponse().getStatusInfo();
        }
        return Response.Status.INTERNAL_SERVER_ERROR;
    }
    
}

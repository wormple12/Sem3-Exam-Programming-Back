package rest;

import dto.RecipeDTO;
import entities.Recipe;
import facades.RecipeFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;

@OpenAPIDefinition(
        info = @Info(
                title = "exam-restaurant-planner",
                version = "0.1",
                description = "Backend for the Programming Exam (16/01/2020)"
        ),
        tags = {
            @Tag(name = "Restaurant Planner resource", description = "API related to the Restaurant Planner")
        },
        servers = {
            @Server(
                    description = "For Local host testing",
                    url = "http://localhost:8080/exam-restaurant-planner"
            ),
            @Server(
                    description = "Server API",
                    url = "https://www.helvedesmaskine.dk/exam-restaurant-planner"
            )

        }
)
@Path("recipe")
public class RecipeResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final RecipeFacade FACADE = RecipeFacade.getRecipeFacade(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Operation(summary = "A welcome message that confirms the connection to the Restaurant Planner API",
            tags = {"Restaurant Planner resource"},
            responses = {
                @ApiResponse(responseCode = "200", description = "The user successfully connected"),
                @ApiResponse(responseCode = "400", description = "The server cannot or will not process the request")})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user", "admin"})
    @Operation(summary = "Fetches all recipes in the database",
            tags = {"All recipes endpoint"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Recipe.class))),
                @ApiResponse(responseCode = "200", description = "The requested recipes were returned"),
                @ApiResponse(responseCode = "400", description = "The server cannot or will not process the request and no resources were returned")})
    public List<RecipeDTO> getAllRecipes() {
        List<RecipeDTO> list = new ArrayList();
        List<Recipe> recipes = FACADE.findAll();
        recipes.forEach((r) -> {
            list.add(new RecipeDTO(r));
        });
        return list;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    @Operation(summary = "Recipe Creation", tags = {"Recipe endpoint"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Recipe Created"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public RecipeDTO createRest(Recipe entity) {
        Recipe created = FACADE.create(entity);
        return new RecipeDTO(created);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    @Operation(summary = "Recipe Editing", tags = {"Recipe endpoint"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Recipe Edited"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body to edit")
            })
    public RecipeDTO editRest(Recipe entity) {
        Recipe edited = FACADE.edit(entity);
        return new RecipeDTO(edited);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @RolesAllowed("admin")
    @Operation(summary = "Recipe Deletion", tags = {"Recipe endpoint"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Recipe Edited"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided to delete")
            })
    public RecipeDTO deleteRest(@PathParam("id") Long id) {
        Recipe deleted = FACADE.remove(id);
        return new RecipeDTO(deleted);
    }

}

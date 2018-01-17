package si.fri.tabletop.reviews.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.tabletop.reviews.Review;
import si.fri.tabletop.reviews.ReviewsBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log
public class ReviewsResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private ReviewsBean reviewBean;

    @GET
    public Response getReviews() {

        List<Review> reviews = reviewBean.getReviews(uriInfo);
        return Response.ok(reviews).build();
    }

    @GET
    @Path("/{reviewId}")
    public Response getReviews(@PathParam("reviewId") String reviewId) {

        Review review = reviewBean.getReview(reviewId);

        if (review == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(review).build();
    }

    @POST
    public Response addReview(Review review) {
        review = reviewBean.addReview(review);

        if (review.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(review).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(review).build();
        }
    }
}

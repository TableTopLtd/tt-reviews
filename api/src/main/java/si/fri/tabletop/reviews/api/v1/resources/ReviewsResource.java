package si.fri.tabletop.reviews.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.tabletop.reviews.Review;
import si.fri.tabletop.reviews.ReviewsBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Log
public class ReviewsResource {

    @Inject
    private ReviewsBean reviewsBean;

    @GET
    public Response getReviews() {

        List<Review> reviews = reviewsBean.getReviews();
        return Response.ok(reviews).build();
    }

    @POST
    public Response addReview(Review review) {
        review = reviewsBean.addReview(review);

        if (review.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(review).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(review).build();
        }
    }
}

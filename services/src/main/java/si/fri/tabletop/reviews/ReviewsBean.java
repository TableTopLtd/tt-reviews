package si.fri.tabletop.reviews;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import com.sun.org.apache.regexp.internal.RE;
import si.fri.tabletop.reviews.Review;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ReviewsBean {

    @Inject
    private EntityManager em;

    @Inject
    private ReviewsBean reviewBean;

    private Client httpClient;

    /*public static List<Review> getReviews(){
        return ReviewsDatabase.getReviews();
    }*/
    public static Review addReview(Review review) { return ReviewsDatabase.addReview(review); }

    public List<Review> getReviews(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, Review.class, queryParameters);

    }

    public Review getReview(String reviewId) {

        Review review = em.find(Review.class, reviewId);

        if (review == null) {
            throw new NotFoundException();
        }

        // TODO: Change when we have config server
        //if (restProperties.isMenuServiceEnabled()) {
        /*List<Menu> menus = orderBean.getMenus(placeId);
        order.setMenus(menus);*/
        //}

        return review;
    }

    public Review createOrder(Review review) {

        try {
            beginTx();
            em.persist(review);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return review;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}

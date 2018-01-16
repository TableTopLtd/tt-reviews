package si.fri.tabletop.reviews;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ReviewsBean {

    public static List<Review> getReviews(){
        return ReviewsDatabase.getReviews();
    }
    public static Review addReview(Review review) { return ReviewsDatabase.addReview(review); }
}

package si.fri.tabletop.reviews;


import java.util.ArrayList;
import java.util.List;

public class ReviewsDatabase {

    private static List<Review> reviews = new ArrayList<>();

    public static List<Review> getReviews() {
        return reviews;
    }

    public static Review getReview(String reviewId) {
        for (Review review : reviews) {
            if (review.getId().equals(reviewId))
                return review;
        }

        return null;
    }

    public static Review addReview(Review review) {
        reviews.add(review);

        return review;
    }

    public static void deleteReview(String reviewId) {
        for (Review review : reviews) {
            if (review.getId().equals(reviewId)) {
                reviews.remove(review);
                break;
            }
        }
    }
}


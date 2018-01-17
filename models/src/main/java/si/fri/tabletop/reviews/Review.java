package si.fri.tabletop.reviews;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "reviews")
@NamedQueries(value =
        {
                @NamedQuery(name = "Review.getAll", query = "SELECT r FROM reviews r"),
                @NamedQuery(name = "Review.findByPlace", query = "SELECT r FROM reviews r WHERE r.placeId = " +
                        ":placeId"),
                @NamedQuery(name = "Review.findByCustomer", query = "SELECT r FROM reviews r WHERE r.customerId = " +
                        ":customerId")
        })
@UuidGenerator(name = "idGenerator")

public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "place_id")
    private String placeId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "review_time")
    private Date reviewTime;

    private String comment;
    private int rate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}

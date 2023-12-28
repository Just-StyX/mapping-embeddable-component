package jsl.auction.demo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    @Access(AccessType.PROPERTY)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_on", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @Column(name = "last_modified", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    @Column(insertable = false, nullable = false, name = "initial_price")
    @ColumnDefault("1.0")
    private double initialPrice;

    @Column(name = "auction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuctionType auctionType;

    @Formula("concat(substring(description, 1, 12), '...')")
    private String shortDescription;

    @ColumnTransformer(read = "imperial_weight / 2.2046", write = "? * 2.2046")
    @Column(name = "imperial_weight")
    private double metricWeight;

    public void setName(String name) {
        this.name = !name.contains("auction") ? "auction " + name: name;
    }
}

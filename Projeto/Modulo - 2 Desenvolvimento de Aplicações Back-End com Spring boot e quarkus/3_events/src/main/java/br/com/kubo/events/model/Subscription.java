package br.com.kubo.events.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalTime;

@Entity
@Table(name = "tbl_subscription")
public class Subscription {

    @Id
    private SubscriptionId id;



    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalTime createAt;

    @Column(name = "level")
    private Integer level;

    @Column(name = "unique_id", nullable = false, length = 50)
    private String uniqueID;




    public SubscriptionId getId() {
        return id;
    }

    public void setId(SubscriptionId id) {
        this.id = id;
    }

    public LocalTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalTime createAt) {
        this.createAt = createAt;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }
}

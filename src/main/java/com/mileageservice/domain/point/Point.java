package com.mileageservice.domain.point;

import com.mileageservice.domain.user.User;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Entity
@DynamicUpdate
@Table(name = "TPT01MT")
public class Point {
    @Id
    @Column(name = "userId")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "userId")
    private User user;

    @Column
    private int totalPoint;     //총포인트

    @Column
    @UpdateTimestamp
    private Timestamp updDate;

    @Column
    @CreationTimestamp
    private Timestamp regDate;


    public void savePoint(UUID userId, int totalPoint,Timestamp regDate) {
        this.id = userId;
        this.totalPoint = totalPoint;
        this.regDate = regDate;
    }
}

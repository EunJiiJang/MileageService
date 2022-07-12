package com.mileageservice.domain.point;

import com.mileageservice.domain.user.User;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Entity
@DynamicUpdate
@Table(name = "TPT01MT")
public class Point{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID totalPointid;

    @OneToOne
    @JoinColumn(name = "userId")
    private User userId;

    @Column
    private int totalPoint;     //총포인트

    @Column
    @UpdateTimestamp
    private Timestamp updDate;

    @Column
    @CreationTimestamp
    private Timestamp regDate;




    public void updatePoint(UUID totalPointid,User userId, int totalPoint,Timestamp regDate) {
        this.totalPointid = totalPointid;
        this.userId = userId;
        this.totalPoint = totalPoint;
        this.regDate = regDate;
    }
    public void saveDefaultPoint(User userId, int totalPoint) {
        this.userId = userId;
        this.totalPoint = totalPoint;
    }
}

package com.mileageservice.domain.pointHistory;

import com.mileageservice.domain.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "TPT01HT")
public class PointHistory {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID pointId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    @Column
    private String pointType;

    @Column(columnDefinition = "BINARY(16)")
    private UUID serviceId;

    @Column
    private String pointStatus;

    @Column
    private int point;

    @Column
    @CreationTimestamp
    private Timestamp regDate;

    public void savePointHistory(User userId,String pointType,UUID serviceId,String pointStatus,int point){
        this.userId = userId;
        this.pointType = pointType;
        this.serviceId = serviceId;
        this.pointStatus = pointStatus;
        this.point = point;


    }
}

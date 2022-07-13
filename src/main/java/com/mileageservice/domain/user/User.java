package com.mileageservice.domain.user;

import com.mileageservice.domain.point.Point;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Table(name = "TMB01MT")
public class User {
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    public void user(UUID userId){
        this.userId = userId;
    }
}

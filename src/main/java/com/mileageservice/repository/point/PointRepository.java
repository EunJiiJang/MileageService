package com.mileageservice.repository.point;

import com.mileageservice.domain.point.Point;
import com.mileageservice.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PointRepository extends JpaRepository<Point, UUID> {
    Optional<Point> findByUserId(User userId);
}

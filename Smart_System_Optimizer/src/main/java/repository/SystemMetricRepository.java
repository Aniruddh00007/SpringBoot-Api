package repository;

import entity.SystemMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemMetricRepository extends JpaRepository<SystemMetric, Long> {

    SystemMetric findTopByOrderByTimestampDesc();
}
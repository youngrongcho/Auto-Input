package auto.input.repository;

import auto.input.entity.ReservationDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<ReservationDate, Long> {
}

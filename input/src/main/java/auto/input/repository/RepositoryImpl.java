package auto.input.repository;

import auto.input.entity.ReservationDate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class RepositoryImpl implements Repository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void insertDate(ReservationDate reservationDate) {

    }
}

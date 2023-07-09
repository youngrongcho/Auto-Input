package auto.input.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ReservationDate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rdSeq;
    private String date;
    private int hour;
    private State rdState = State.NORMAL;
    private String rdTypeCd;

    //rdState enum
    public enum State {
        BAN, LUNCH, NORMAL;
    }
}

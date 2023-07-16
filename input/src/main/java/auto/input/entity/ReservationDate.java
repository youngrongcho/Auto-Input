package auto.input.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ReservationDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rdSeq;
    private String date;
    private int hour;
    @Enumerated(EnumType.STRING)
    private State rdState = State.NORMAL;
    private String rdTypeCd;

    //rdState enum
    public enum State {
        BAN, LUNCH, NORMAL;
    }
}

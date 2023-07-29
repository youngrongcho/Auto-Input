package auto.input.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class Dto {
    //DB에 입력해야하는 <연도, 월, 시작 시간, 끝 시간, rdType, 공휴일>
    @NotNull
    @Min(2023)
    private int year;
    @NotNull
    @Range(min = 1, max = 12)
    private int month;
    @NotNull
    @Min(0)
    private int startHour;
    @NotNull
    @Max(23)
    private int endHour;
    @NotNull
    @NotBlank(message = "타입을 입력해주세요.")
    private String rdType;
    private List<String> holidays;
}

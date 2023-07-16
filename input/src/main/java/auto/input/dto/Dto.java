package auto.input.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class Dto {
    //DB에 입력해야하는 <연도, 월, 시작 시간, 끝 시간, rdType>
    @NotNull
    private int year;
    @NotNull
    private int month;
    @NotNull
    private int startHour;
    @NotNull
    private int endHour;
    @NotNull
    private String rdType;
}

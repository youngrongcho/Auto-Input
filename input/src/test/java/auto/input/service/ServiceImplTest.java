package auto.input.service;

import auto.input.entity.ReservationDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

class ServiceImplTest {
    @ParameterizedTest
    @DisplayName("월 마지막 날짜 구하기 테스트")
    @CsvSource(value = {"2023-06, 30", "2023-07, 31", "2023-02, 28", "2024-02, 29"})
    void getLastDayTest(String input, int expected){
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(input.substring(0,4));
        int month = Integer.parseInt(input.substring(5,7));
        calendar.set(year,  month - 1, 1);
        Assertions.assertEquals(expected, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    }

    @Test
    @DisplayName("날짜 포맷 변환하기 테스트")
    void makeDateTest(){
        LocalDateTime localDateTime = LocalDateTime.of(2023, 7, 31, 0, 0, 0);
        System.out.println(localDateTime);
        String result = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Assertions.assertEquals("2023-07-31 00:00:00", result);
    }

    @ParameterizedTest
    @DisplayName("요일 구하기 테스트")
    @CsvSource(value = {"2023-07-31, 월요일", "2023-08-15, 화요일", "2023-11-27, 월요일", "2023-12-24, 일요일"})
    void getDayTest(String input, String expected){
        int year = Integer.parseInt(input.substring(0,4));
        int month = Integer.parseInt(input.substring(5,7));
        int day = Integer.parseInt(input.substring(8));

        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, 0, 0, 0);
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        String dayOfWeekDisplayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);

        Assertions.assertEquals(expected, dayOfWeekDisplayName);
    }

    @Test
    @DisplayName("입력된 entity 개수 확인 테스트")
    void input() {
        //given
        int year = 2023;
        int month = 8;
        int startHour = 9;
        int endHour = 18;
        String rdType = "SKINCARE";
        int lastDay = 31;
        List<ReservationDate> result = new ArrayList<>();

        //when
        for (int i = 1; i <= lastDay; i++) {
            for (int j = startHour; j <= endHour; j++) {
                //entity 객체 생성
                ReservationDate reservationDate = ReservationDate.builder().rdTypeCd(rdType).build();
                LocalDateTime localDateTime = LocalDateTime.of(year, month, i, 0, 0, 0);
                reservationDate.setDate(localDateTime.toString());
                reservationDate.setHour(j);

                result.add(reservationDate);
            }
        }

        //then
        Assertions.assertEquals(lastDay * ( endHour - startHour + 1 ), result.size());
    }
}
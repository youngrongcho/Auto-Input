package auto.input.service;

import auto.input.dto.Dto;
import auto.input.entity.ReservationDate;
import auto.input.repository.Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Locale;

@org.springframework.stereotype.Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ServiceImpl implements Service {
    private final Repository repository;
    private static int lunchTime = 12;
    private static int endHour;

    @Override
    public void input(Dto dto) {
        int year = dto.getYear();
        int month = dto.getMonth();
        int startHour = dto.getStartHour();
        endHour = dto.getEndHour();
        String rdType = dto.getRdType();
        int lastDay = getLastDay(year, month);

        //각 날짜마다 시작 시간부터 종료 시간까지 for문 돌면서 repository insert.
        for (int i = 1; i <= lastDay; i++) {
            for (int j = startHour; j <= endHour; j++) {
                //entity 객체 생성
                ReservationDate reservationDate = ReservationDate.builder().rdTypeCd(rdType).build();
                LocalDateTime localDateTime = LocalDateTime.of(year, month, i, 0, 0, 0);
                String date = makeDate(localDateTime);
                reservationDate.setDate(date);
                reservationDate.setHour(j);

                String day = getDay(localDateTime);
                if (isWeekend(day) || isHoliday(dto, date)) {
                    reservationDate.setRdState(ReservationDate.State.BAN);
                } else {
                    if (isLunchTime(j)) {
                        reservationDate.setRdState(ReservationDate.State.LUNCH);
                    }
                    else if (isOver(j)) {
                        reservationDate.setRdState(ReservationDate.State.BAN);
                    }
                    else {
                        reservationDate.setRdState(ReservationDate.State.NORMAL);
                    }
                }
                repository.save(reservationDate);
            }
        }
    }

    private static boolean isOver(int j) {
        return j == endHour;
    }

    private static boolean isLunchTime(int j) {
        return j == lunchTime;
    }

    private static boolean isHoliday(Dto dto, String date) {
        return dto.getHolidays().contains(date.substring(0, 10));
    }

    private static boolean isWeekend(String day) {
        return day.equals("토요일") || day.equals("일요일");
    }

    private static String getDay(LocalDateTime localDateTime) {
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN));
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);
    }

    private static String makeDate(LocalDateTime localDateTime) {
        String date = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return date;
    }

    private static int getLastDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}

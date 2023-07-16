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

    @Override
    public void input(Dto dto) {
        int year = dto.getYear();
        int month = dto.getMonth();
        int startHour = dto.getStartHour();
        int endHour = dto.getEndHour();
        String rdType = dto.getRdType();
        int lastDay = getLastDay(year, month);

        //각 날짜마다 시작 시간부터 종료 시간까지 for문 돌면서 repository insert.
        for (int i = 1; i <= lastDay; i++) {
            for (int j = startHour; j <= endHour; j++) {
                //entity 생성
                ReservationDate reservationDate = ReservationDate.builder().build();
                reservationDate.setRdTypeCd(rdType);
                LocalDateTime localDateTime = LocalDateTime.of(year, month, i, 0, 0, 0);
                String date = makeDate(localDateTime);
                reservationDate.setDate(date);

                reservationDate.setHour(j);
                //주말이거나 공휴일이면 상태 ban
                String day = getDay(localDateTime);
                if (day.equals("토요일") || day.equals("일요일") || dto.getHolidays().contains(date.substring(0,10))) {
                    reservationDate.setRdState(ReservationDate.State.BAN);
                } else {
                    //점심시간이면 상태 lunch
                    int lunchTime = 12;
                    if (j == lunchTime) {
                        reservationDate.setRdState(ReservationDate.State.LUNCH);
                    }
                    //영업 종료시간이면 상태 ban
                    else if (j == endHour) {
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

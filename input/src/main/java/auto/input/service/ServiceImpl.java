package auto.input.service;

import auto.input.entity.ReservationDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@org.springframework.stereotype.Service
@Transactional
@Slf4j
public class ServiceImpl implements Service {
    @Override
    public void input() throws IOException {
        //DB에 입력해야하는 <연도, 월, 시작 시간, 끝 시간, rdType>을 콘솔로 받는다.
        //TODO 각 메서드 유효성 검사 추가하기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int year = getYear(br);
        int month = getMonth(br);
        int startHour = getStartHour(br);
        int endHour = getEndHour(br);
        String rdType = getType(br);

        //시작 시간부터 종료 시간까지 for문 돌면서 repository insert.
        for (int i = startHour; i <= endHour; i++) {
            //entity 생성
            ReservationDate reservationDate;

            //해당 연도-월의 모든 날짜에 대하여, 모든 시간을 input

            //주말이면 상태 ban
            //영업 종료시간이어도 상태 ban
            //점심시간이면 상태 lunch
        }
    }

    private static String getType(BufferedReader br) throws IOException {
        System.out.println("[🌽] 서비스 타입(rdType)을 입력해주세요. ex) SKINCARE ");
        String rdType = br.readLine().toUpperCase();
        System.out.printf("[🍀] 입력하신 서비스 타입 : %s \n\n", rdType);
        return rdType;
    }

    private static int getEndHour(BufferedReader br) throws IOException {
        System.out.println("[🌽] 종료 시간(endHour)을 입력해주세요. ex) 18 ");
        int endHour = Integer.parseInt(br.readLine());
        System.out.printf("[🍀] 입력하신 종료 시간 : %s시 \n\n", endHour);
        return endHour;
    }

    private static int getStartHour(BufferedReader br) throws IOException {
        System.out.println("[🌽] 시작 시간(startHour)을 입력해주세요. ex) 12 ");
        int startHour = Integer.parseInt(br.readLine());
        System.out.printf("[🍀] 입력하신 시작 시간 : %s시 \n\n", startHour);
        return startHour;
    }

    private static int getMonth(BufferedReader br) throws IOException {
        System.out.println("[🌽] 월(month)을 입력해주세요. ex) 7 ");
        int month = Integer.parseInt(br.readLine());
        System.out.printf("[🍀] 입력하신 월 : %s월 \n\n", month);
        return month;
    }

    private static int getYear(BufferedReader br) throws IOException {
        System.out.println("[🌽] 연도(year)를 입력해주세요. ex) 2023 ");
        int year = Integer.parseInt(br.readLine());
        System.out.printf("[🍀] 입력하신 연도 : %s년 \n\n", year);
        return year;
    }
}

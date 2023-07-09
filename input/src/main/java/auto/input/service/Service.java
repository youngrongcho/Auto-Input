package auto.input.service;

import java.io.IOException;

public interface Service {

    public void input() throws IOException;

    //entity 생성
    //해당 연도-월의 모든 날짜에 대하여, 모든 시간을 input

    //주말이면 상태 ban
    //영업 종료시간이어도 상태 ban
    //점심시간이면 상태 lunch

    //저장하여 repository insert
}

package auto.input.service;

import auto.input.dto.Dto;

import java.io.IOException;

public interface Service {
    public void input(Dto dto) throws IOException;
}

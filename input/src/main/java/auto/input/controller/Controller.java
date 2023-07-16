package auto.input.controller;

import auto.input.dto.Dto;
import auto.input.service.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inputs")
public class Controller {
    private final ServiceImpl service;

    @PostMapping
    public ResponseEntity input(@Valid @RequestBody Dto dto) {
        service.input(dto);
        return new ResponseEntity(HttpStatus.OK);
    }
}

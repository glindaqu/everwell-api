package ru.glindaquint.everwell.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.glindaquint.everwell.dto.responses.DataResponse;
import ru.glindaquint.everwell.services.AdviceService;

@RestController
@RequestMapping("/advice")
@RequiredArgsConstructor
public class AdviceController {

  @Autowired private final AdviceService adviceService;

  @GetMapping("/get")
  public ResponseEntity<DataResponse<String>> getRandomAdvice() {
    return ResponseEntity.ok(
        DataResponse.<String>builder().data(adviceService.getRandomAdvice()).build());
  }
}

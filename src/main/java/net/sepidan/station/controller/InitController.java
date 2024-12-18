package net.sepidan.station.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.sepidan.station.enums.AppMarket;
import net.sepidan.station.payload.request.InitFirstRequest;
import net.sepidan.station.payload.response.InitFirstResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "init")
@Tag(name = "Init", description = "initiate requests")
public class InitController {

  @PostMapping("first")
  public ResponseEntity<InitFirstResponse> first(@RequestBody @Valid InitFirstRequest request) {
    return ResponseEntity.ok(new InitFirstResponse("sdasda","asdasd"));
  }

  @GetMapping("second/{param}")
  public ResponseEntity<String> second(@PathVariable("param") AppMarket appMarket) {
    return ResponseEntity.ok("");
  }

}

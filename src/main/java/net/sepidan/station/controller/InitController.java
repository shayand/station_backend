package net.sepidan.station.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sepidan.station.dto.User;
import net.sepidan.station.enums.AppMarket;
import net.sepidan.station.payload.request.InitFirstRequest;
import net.sepidan.station.payload.response.InitFirstResponse;
import net.sepidan.station.utils.KeycloakUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "init")
@Tag(name = "Init", description = "initiate requests")
@AllArgsConstructor
@Slf4j
public class InitController {

  private final KeycloakUtil keycloakUtil;

  @PostMapping("first")
  public ResponseEntity<InitFirstResponse> first(@RequestBody @Valid InitFirstRequest request,
      JwtAuthenticationToken auth) {
    log.info("First request received");

    try {
      User user = new User();
      user.setUsername("user1");
      user.setPassword("user1@user1");
      user.setFirstName("user1");
      user.setLastName("user1");
      user.setPhone("+989355555555");

      keycloakUtil.createUser(user);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return ResponseEntity.ok(new InitFirstResponse("sdasda", "asdasd"));
  }

  @GetMapping("second/{param}")
  public ResponseEntity<String> second(@PathVariable("param") AppMarket appMarket) {
    return ResponseEntity.ok("");
  }

}

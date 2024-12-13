package net.sepidan.station.controller;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(type = SecuritySchemeType.HTTP, name = "bearer-key",
    description = "authorization with JWT token", scheme = "bearer",
    bearerFormat = "JWT")
public class BaseController {

}

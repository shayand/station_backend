package net.sepidan.station.persistent.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sepidan.station.persistent.repository.UserCallLogAttributeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCallLogAttributeService {

  private final UserCallLogAttributeRepository userCallLogAttributeRepository;
}

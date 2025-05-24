package net.sepidan.station.persistent.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sepidan.station.persistent.repository.UserCallLogRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCallLogService {

  private final UserCallLogRepository userCallLogRepository;

}

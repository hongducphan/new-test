package com.ducph.newtest.service.impl;

import com.ducph.newtest.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Override
    public boolean isAdmin() {
        var result = false;
        log.info("current value: {}", result);
        return result;
    }
}

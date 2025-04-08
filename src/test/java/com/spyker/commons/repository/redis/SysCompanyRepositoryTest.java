package com.spyker.commons.repository.redis;

import static org.junit.jupiter.api.Assertions.*;

import com.spyker.BaseTest;
import com.spyker.commons.entity.SysCompany;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SysCompanyRepositoryTest extends BaseTest {

    @Autowired SysCompanyRepository sysCompanyRepository;

    @Test
    public void add() {
        SysCompany sysCompany = new SysCompany();
        sysCompany.setCompanyName("xxxx");

        sysCompanyRepository.save(sysCompany);
    }
}

package com.spyker.commons.mapper;

import com.spyker.BaseTest;
import com.spyker.commons.entity.Person;
import com.spyker.commons.redisrepository.PersonTestRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PersonRepositoryTest extends BaseTest {

    @Autowired private PersonTestRepository personTestRepository;

    @Test
    public void test() {

        Person rand = new Person();

        rand.setFirstname("fname");
        rand.setLastname("lname");

        personTestRepository.save(rand);

        Optional<Person> or = personTestRepository.findById(rand.getId());

        if (or.isPresent()) {
            System.out.println(or.get());
        }
        personTestRepository.count();

        //        personTestRepository.delete(rand);
    }
}
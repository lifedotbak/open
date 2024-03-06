package com.spyker.commons.mapper;

import com.spyker.BaseTest;
import com.spyker.commons.entity.Person;
import com.spyker.commons.redisrepository.PersonTestRepository;

import jakarta.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;

import java.util.Deque;
import java.util.Optional;

public class PersonRepositoryTest extends BaseTest {

    @Autowired private PersonTestRepository personTestRepository;

    @Autowired Deque<Object> deque;

    @Resource(name = "redisTemplate")
    HashOperations hashOperations;

    @Test
    public void test() {

        hashOperations.put("x", "y", "z");

        deque.push("xxxxxxxxxxxx");

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
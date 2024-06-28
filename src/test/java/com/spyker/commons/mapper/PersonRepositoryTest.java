package com.spyker.commons.mapper;

import com.spyker.BaseTest;
import com.spyker.commons.entity.Person;
import com.spyker.commons.repository.redis.PersonRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PersonRepositoryTest extends BaseTest {

    //    @Autowired Deque<Object> deque;
    //
    //    @Resource(name = "redisTemplate")
    //    HashOperations hashOperations;

    @Autowired private PersonRepository personRepository;

    @Test
    public void test() {

        //        hashOperations.put("x", "y", "z");
        //
        //        deque.push("xxxxxxxxxxxx");

        Person rand = new Person();

        rand.setFirstname("fname");
        rand.setLastname("lname");

        personRepository.save(rand);

        Optional<Person> or = personRepository.findById(rand.getId());

        if (or.isPresent()) {
            System.out.println(or.get());
        }
        System.out.println(personRepository.count());

        //        personTestRepository.delete(rand);
    }
}
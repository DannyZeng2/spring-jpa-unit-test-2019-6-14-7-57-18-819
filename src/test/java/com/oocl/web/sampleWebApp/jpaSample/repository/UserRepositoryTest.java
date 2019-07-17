package com.oocl.web.sampleWebApp.jpaSample.repository;


import com.oocl.web.sampleWebApp.jpaSample.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static sun.security.krb5.Confounder.longValue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_should_return_user_when_the_user_exist() {
        //given
        User user = new User();
        user.setName("test");
        userRepository.save(user);
        //when
        User user2 = userRepository.findByName("test");
        //then
        Assertions.assertEquals("test", user2.getName());
    }

    @Test
    public void test_should_return_user_list_when_the_user_exist() {
        //given
        User user1 = new User("test1");
        User user2 = new User("test2");
        User user3 = new User("test3");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        //when
        List<User> list = userRepository.findUsersByName("test1");
        //then
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void test_should_desc_age_user_list_when_the_user_exist() {
        //given
        User user1 = new User(22,"Danny");
        User user2 = new User(23,"Leo");
        User user3 = new User(21,"Tony");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        //when
        List<User> list = userRepository.findByOrderByAgeDesc();
        //then
        Assertions.assertEquals(23,list.get(0).getAge());
        Assertions.assertEquals(22,list.get(1).getAge());
        Assertions.assertEquals(21,list.get(2).getAge());
    }
    @Test
    public void test_should_return_user_list_ignore_case_when_the_user_exist() {
        //given
        User user1 = new User("test1");
        User user2 = new User("test2");
        User user3 = new User("test1");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        //when
        List<User> list = userRepository.findUsersByNameIgnoreCase("TEST1");
        //then
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void test_should_delete_user_when_the_user_exist() {
        //given
        User user1 = new User("danny");
        User user2 = new User("kevin");
        User user3 = new User("lily");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        //when
        userRepository.deleteById(user2.getId());
        List<User> list = userRepository.findAll();
        //then
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(user1, list.get(0));
        Assertions.assertEquals(user3, list.get(1));
    }

    @Test
    public void test_delete_user_when_find_user_name() {
        //given
        User user1 = new User("danny");
        User user2 = new User("kevin");
        User user3 = new User("lily");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        //when
        userRepository.deleteByName("kevin");
        List<User> list = userRepository.findAll();
        //then
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(user1, list.get(0));
        Assertions.assertEquals(user3, list.get(1));
    }
}

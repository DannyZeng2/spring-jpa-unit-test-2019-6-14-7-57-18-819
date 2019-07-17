package com.oocl.web.sampleWebApp.jpaSample.repository;

import com.oocl.web.sampleWebApp.jpaSample.entity.SingleEntity;
import com.oocl.web.sampleWebApp.jpaSample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    List<User> findUsersByName(String name);
    List<User> findByOrderByAgeDesc();

    List<User> findUsersByNameIgnoreCase(String name);

    @Query(value = "delete from User where name = ?1",nativeQuery = true)
    @Modifying
    void deleteByName(String name);
}

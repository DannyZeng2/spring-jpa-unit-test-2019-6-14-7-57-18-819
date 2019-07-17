package com.oocl.web.sampleWebApp.jpaSample.repository;

import com.oocl.web.sampleWebApp.jpaSample.entity.SingleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SingleRepositoryTest {

  @Autowired
  private SingleRepository singleRepository;

  @Test
  public void test_should_return_single_when_the_single_exist() {
    //given
    SingleEntity singleEntity = new SingleEntity();
    singleEntity.setName("test");
    singleRepository.save(singleEntity);

    //when
    List<SingleEntity> singleList = singleRepository.findAll();

    //then
    Assertions.assertEquals(1, singleList.size());
    Assertions.assertEquals("test", singleList.get(0).getName());
  }

  @Test
  public void test_should_return_exception_when_the_name_smaller_than_64() {
    //given
    SingleEntity singleEntity = new SingleEntity();
    String name = "";
    for(int i=0;i<63;i++) {
      name += "a";
    }
    singleEntity.setName(name);
    singleRepository.save(singleEntity);

    //when
    List<SingleEntity> singleList = singleRepository.findAll();


    singleRepository.save(singleEntity);
    Assertions.assertEquals(name, singleList.get(0).getName());
  }

  @Test
  public void test_should_return_exception_when_the_name_larger_than_64() {
    //given
    SingleEntity singleEntity = new SingleEntity();
    String name = "";
    for(int i=0;i<65;i++) {
      name += "a";
    }

    singleEntity.setName(name);
    singleRepository.save(singleEntity);
    Assertions.assertThrows(Exception.class, ()->{
      singleRepository.findAll();});
  }

}


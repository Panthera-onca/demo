package com.example.demo.dal;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.bo.userBean;


public interface UserRepository extends CrudRepository<userBean, Integer> {

}
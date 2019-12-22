package com.harroshan.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.harroshan.user.models.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

}

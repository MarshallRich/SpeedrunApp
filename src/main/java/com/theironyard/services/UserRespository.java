package com.theironyard.services;

import org.springframework.data.repository.CrudRepository;
import com.theironyard.entities.User;

/**
 * Created by MacLap on 3/14/16.
 */
public interface UserRespository extends CrudRepository<User, Integer> {
    User findFirstByName(String username);
}

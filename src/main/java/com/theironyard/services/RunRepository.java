package com.theironyard.services;

import com.theironyard.entities.Run;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by MacLap on 3/14/16.
 */
public interface RunRepository extends CrudRepository<Run, Integer> {
}

package com.theironyard.services;

import com.theironyard.entities.Run;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by MacLap on 3/14/16.
 */
public interface RunRepository extends PagingAndSortingRepository<Run, Integer> {
    Page<Run> findAll(Pageable pageable);
}

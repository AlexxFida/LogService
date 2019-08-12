package com.fida.repositories;

import com.fida.models.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface LogRepository extends PagingAndSortingRepository<Log, Long> {

    List<Log> findAllByDateTimeAfterAndDateTimeBefore(Long start, Long end, Pageable pageable);

    List<Log> findAllByDateTimeAfterAndDateTimeBeforeAndSource(Long start, Long end, String source, Pageable pageable);

    @Query(value = "SELECT DISTINCT source FROM log", nativeQuery = true)
    List<String> getSources();

    List<Log> findAllBySource(String source);

    List<Log> findAllByDateTimeAfterAndSource(Long dateTime, String source);
}

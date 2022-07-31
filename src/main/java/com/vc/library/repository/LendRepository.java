package com.vc.library.repository;

import com.vc.library.LendStatus;
import com.vc.library.entity.Lend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shankar.s
 */

@Repository
public interface LendRepository extends JpaRepository<Lend, Long> {

    List<Lend> findAllByCreatedByAndStatusIn(Long createdBy, List<LendStatus> status);
}

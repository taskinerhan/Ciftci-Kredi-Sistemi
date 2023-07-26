package com.cks.ciftcikredisistemi.repository;

import com.cks.ciftcikredisistemi.entity.ciftci.Ciftci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiftciYonetimiRepository extends JpaRepository<Ciftci,Long> {
     Ciftci findCiftciById(Long id);


}

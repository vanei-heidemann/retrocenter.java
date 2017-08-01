package com.javanei.retrocenter.datafile.persistence;

import com.javanei.retrocenter.datafile.entity.DatafileReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatafileReleaseDAO extends JpaRepository<DatafileReleaseEntity, Long> {
}

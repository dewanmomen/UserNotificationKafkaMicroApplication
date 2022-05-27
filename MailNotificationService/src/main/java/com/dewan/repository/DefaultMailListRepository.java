package com.dewan.repository;

import com.dewan.entity.DefaultMailListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultMailListRepository extends JpaRepository<DefaultMailListEntity, Long> {

    DefaultMailListEntity findDefaultMailListEntityByUserid(Long userid);
}

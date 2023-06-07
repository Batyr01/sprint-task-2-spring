package com.projecct.sprinttask2.repository;

import com.projecct.sprinttask2.model.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestModel, Long> {

    List<RequestModel> findAllByHandledEquals(boolean handled);

}

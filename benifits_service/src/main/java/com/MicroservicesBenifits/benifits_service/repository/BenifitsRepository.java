package com.MicroservicesBenifits.benifits_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MicroservicesBenifits.benifits_service.model.Benifits;



@Repository
public interface BenifitsRepository extends JpaRepository<Benifits, Long> {

}

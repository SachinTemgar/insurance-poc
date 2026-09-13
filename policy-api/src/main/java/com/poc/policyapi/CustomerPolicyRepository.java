package com.poc.policyapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerPolicyRepository extends JpaRepository<CustomerPolicy, Integer> {

    List<CustomerPolicy> findByCustomerId(String customerId);
}

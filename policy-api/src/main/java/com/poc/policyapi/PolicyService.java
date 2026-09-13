package com.poc.policyapi;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PolicyService {

    private final CustomerPolicyRepository policyRepository;
    private final ProductRepository productRepository;

    public PolicyService(CustomerPolicyRepository policyRepository,
                         ProductRepository productRepository) {
        this.policyRepository = policyRepository;
        this.productRepository = productRepository;
    }

    public List<PolicyResponse> findAll() {
        return toResponses(policyRepository.findAll());
    }

    public List<PolicyResponse> findByCustomerId(String customerId) {
        return toResponses(policyRepository.findByCustomerId(customerId));
    }

    private List<PolicyResponse> toResponses(List<CustomerPolicy> policies) {

        Map<Integer, String> productNames = new HashMap<>();
        for (Product product : productRepository.findAll()) {
            productNames.put(product.getProductId(), product.getName());
        }

        return policies.stream()
                .map(policy -> new PolicyResponse(
                        policy.getCustomerName(),
                        policy.getCustomerId(),
                        policy.getProductId(),
                        productNames.getOrDefault(policy.getProductId(), "Unknown")
                ))
                .collect(Collectors.toList());
    }
}

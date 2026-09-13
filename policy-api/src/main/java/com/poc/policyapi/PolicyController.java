package com.poc.policyapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
@Tag(name = "Customer Policies", description = "Sold policies per customer")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping
    @Operation(summary = "Get all sold policies",
               description = "Returns every customer policy with its product name")
    public List<PolicyResponse> getAllPolicies() {
        return policyService.findAll();
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "Get policies for one customer",
               description = "Returns all policies bought by the given customer ID")
    public ResponseEntity<List<PolicyResponse>> getPoliciesByCustomer(
            @PathVariable String customerId) {

        List<PolicyResponse> policies = policyService.findByCustomerId(customerId);

        if (policies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(policies);
    }
}

package org.example;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MerchantRepository extends MongoRepository<Merchant, String> {
    Optional<Merchant> findByEmail(String email);
}

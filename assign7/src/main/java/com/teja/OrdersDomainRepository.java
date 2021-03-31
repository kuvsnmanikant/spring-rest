package com.teja;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersDomainRepository extends MongoRepository<orders, String> {

}

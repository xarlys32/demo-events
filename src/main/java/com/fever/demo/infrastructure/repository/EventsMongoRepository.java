package com.fever.demo.infrastructure.repository;

import com.fever.demo.infrastructure.entity.Document.BasePlanDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventsMongoRepository extends MongoRepository<BasePlanDocument, String> {
}

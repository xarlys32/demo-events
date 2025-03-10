package com.fever.demo.infrastructure.repository;

import com.fever.demo.infrastructure.entity.Document.BasePlanDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EventsMongoRepository extends MongoRepository<BasePlanDocument, String> {

    @Query("{ 'sell_mode': 'online', 'plan': { $elemMatch: { 'plan_start_date': { $gte: ?0 }, 'plan_end_date': { $lte: ?1 } } } }")
    List<BasePlanDocument> findByPlanBaseDates(String planStartDate, String planEndDate);
}

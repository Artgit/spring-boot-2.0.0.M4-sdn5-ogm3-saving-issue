package com.decisionwanted.domain.repository.decision.characteristic.value.history;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.decisionwanted.domain.model.decision.characteristic.value.history.HistoryValue;
import com.decisionwanted.domain.repository.BaseRepository;

@Repository
public interface HistoryValueRepository extends BaseRepository<HistoryValue> {
	
	@Query("MATCH (v:Value)-[rvhv:CONTAINS]->(hv:HistoryValue)-[ru:CREATED_BY]->(u:User) WHERE v.id = {valueId} RETURN ru, u, rvhv, v, hv ORDER BY hv.createDate DESC")
	List<HistoryValue> findAllByValueId(@Param("valueId") Long valueId);

	HistoryValue getById(Long historyValueId);

}
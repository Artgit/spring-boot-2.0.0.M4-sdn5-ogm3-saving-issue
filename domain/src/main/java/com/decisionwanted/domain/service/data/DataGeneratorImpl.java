package com.decisionwanted.domain.service.data;

import java.util.Collections;

import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DataGeneratorImpl implements DataGenerator {

	final static Logger logger = LoggerFactory.getLogger(DataGeneratorImpl.class);

	private static final int DELETE_BATCH_SIZE = 1000;

	@Autowired
	private Session session;


	@Override
	public void cleanDb() {
		logger.info("Cleaning database");
		long deletedNodesCount = 0;
		do {
			Result result = session.query("MATCH (n) WITH n LIMIT " + DELETE_BATCH_SIZE + " DETACH DELETE n RETURN count(n) as count", Collections.emptyMap());
			deletedNodesCount = (long) result.iterator().next().get("count");
			logger.info("Deleted " + deletedNodesCount + " nodes...");
		} while (deletedNodesCount > 0);
	}


}
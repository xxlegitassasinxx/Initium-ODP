package com.universeprojects.miniup.server.dao;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Key;
import com.universeprojects.cacheddatastore.CachedDatastoreService;
import com.universeprojects.cacheddatastore.CachedEntity;
import com.universeprojects.miniup.server.domain.AffectorProcess;
import com.universeprojects.miniup.server.exceptions.DaoException;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class AffectorProcessDao extends OdpDao<AffectorProcess> {
	private static final Logger log = Logger.getLogger(ClassName.class.getName());

	public AffectorProcessDao(CachedDatastoreService datastore) {
		super(datastore);
	}

	@Override
	protected Logger getLogger() {
		return log;
	}

	@Override
	public AffectorProcess get(Key key) {
		CachedEntity entity = getCachedEntity(key);
		return entity == null ? null : new AffectorProcess(entity);
	}

	@Override
	public List<AffectorProcess> findAll() throws DaoException {
		return buildList(findAllCachedEntities(AffectorProcess.KIND), AffectorProcess.class);
	}

	@Override
	public List<AffectorProcess> get(List<Key> keyList) throws DaoException {
		if (keyList == null || keyList.isEmpty()) {
			return Collections.emptyList();
		}

		return buildList(getDatastore().get(keyList), AffectorProcess.class);
	}

}

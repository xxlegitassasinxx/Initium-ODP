package com.universeprojects.miniup.server.dao;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Key;
import com.universeprojects.cacheddatastore.CachedDatastoreService;
import com.universeprojects.cacheddatastore.CachedEntity;
import com.universeprojects.miniup.server.domain.Project;
import com.universeprojects.miniup.server.exceptions.DaoException;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class ProjectDao extends OdpDao<Project> {
	private static final Logger log = Logger.getLogger(ClassName.class.getName());

	public ProjectDao(CachedDatastoreService datastore) {
		super(datastore);
	}

	@Override
	protected Logger getLogger() {
		return log;
	}

	@Override
	public Project get(Key key) {
		CachedEntity entity = getCachedEntity(key);
		return entity == null ? null : new Project(entity);
	}

	@Override
	public List<Project> findAll() throws DaoException {
		return buildList(findAllCachedEntities(Project.KIND), Project.class);
	}

	@Override
	public List<Project> get(List<Key> keyList) throws DaoException {
		if (keyList == null || keyList.isEmpty()) {
			return Collections.emptyList();
		}

		return buildList(getDatastore().get(keyList), Project.class);
	}

}

package com.universeprojects.miniup.server.dao;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Key;
import com.universeprojects.cacheddatastore.CachedDatastoreService;
import com.universeprojects.cacheddatastore.CachedEntity;
import com.universeprojects.miniup.server.domain.Group;
import com.universeprojects.miniup.server.exceptions.DaoException;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class GroupDao extends OdpDao<Group> {
	private static final Logger log = Logger.getLogger(ClassName.class.getName());

	public GroupDao(CachedDatastoreService datastore) {
		super(datastore);
	}

	@Override
	protected Logger getLogger() {
		return log;
	}

	@Override
	public Group get(Key key) {
		CachedEntity entity = getCachedEntity(key);
		return entity == null ? null : new Group(entity);
	}

	@Override
	public List<Group> findAll() throws DaoException {
		return buildList(findAllCachedEntities(Group.KIND), Group.class);
	}

	@Override
	public List<Group> get(List<Key> keyList) throws DaoException {
		if (keyList == null || keyList.isEmpty()) {
			return Collections.emptyList();
		}

		return buildList(getDatastore().get(keyList), Group.class);
	}

}

package org.johnnybionic.service;

import org.johnnybionic.dao.CoffeeShopDao;
import org.johnnybionic.domain.CoffeeShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Service implementation that uses the MongoDB driver version of the DAO.
 * There should be one service into which the DAO is injected using profiles,
 * but this caused problems for unit tests (see 'profile-problems.txt'), so instead
 * there are two services, and it's these that are selected by profile.
 * 
 * @author johnny
 *
 */
@Profile("mongodriver")
@Service
public class CoffeeShopDriverDaoService implements CoffeeShopService {

	@Autowired
	@Qualifier(value="mongodriver")
	private CoffeeShopDao dao;
	
	@Override
	public CoffeeShop findById(String coffeeShopId) {
		return dao.findById(coffeeShopId);
	}

	@Override
	public CoffeeShop findByCoordinates(double longitude, double latitude) {
		return dao.findByCoordinates(longitude, latitude);
	}

}

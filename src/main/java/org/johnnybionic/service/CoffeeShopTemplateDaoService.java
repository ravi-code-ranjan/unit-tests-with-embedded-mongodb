package org.johnnybionic.service;

import org.johnnybionic.dao.CoffeeShopDao;
import org.johnnybionic.domain.CoffeeShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Service implementation that uses the MongoTemplate version of the DAO. There
 * should be one service into which the DAO is injected using profiles, but this
 * caused problems for unit tests (see 'profile-problems.txt'), so instead there
 * are two services, and it's these that are selected by profile.
 * 
 * This version uses the DAO that uses Spring's MongoTemplate.
 * 
 * @author johnny
 *
 */

@Profile("mongotemplate")
@Service
public class CoffeeShopTemplateDaoService implements CoffeeShopService {

    @Autowired
    @Qualifier(value = "mongotemplate")
    private CoffeeShopDao dao;

    @Override
    public CoffeeShop findById(final String coffeeShopId) {
        return dao.findById(coffeeShopId);
    }

    @Override
    public CoffeeShop findByCoordinates(final double longitude, final double latitude) {
        return dao.findByCoordinates(longitude, latitude);
    }

}

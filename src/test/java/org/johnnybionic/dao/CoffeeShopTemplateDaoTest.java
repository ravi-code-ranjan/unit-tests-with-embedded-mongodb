package org.johnnybionic.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.johnnybionic.database.AbstractDatabaseTest;
import org.johnnybionic.domain.CoffeeShop;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Tests the DAO that uses Spring's MongoTemplate.
 * 
 * See "profile-problems.txt" as to why profiles caused problems with the
 * testing, and required a workaround.
 * 
 * @author johnny
 *
 */

// @ActiveProfiles(inheritProfiles=true, profiles={"mongotemplate"})
public class CoffeeShopTemplateDaoTest extends AbstractDatabaseTest<CoffeeShop> {

    private static final String COFFEE_SHOP_ID = "57907ce15f55e14ae0b90ac9";
    private static final String NAME = "Catherine's";
    private static final String NAME_BY_LOCATION = "Caravan";
    private static final double LONGITUDE = -0.125133d;
    private static final double LATITUDE = 51.5357461d;

    @Autowired
    @Qualifier("mongotemplate")
    private CoffeeShopDao dao;

    @Before
    public void loadData() {
        loadSampleData("src/test/resources/coffee-shops.json", COFFEESHOPS_COLLECTION);
        createIndex(COFFEESHOPS_COLLECTION, LOCATION);
    }

    @After
    public void deleteData() {
        removeCollection(COFFEESHOPS_COLLECTION);
    }

    @Test
    public void thatFindByIdWorks() {

        final CoffeeShop findById = dao.findById(COFFEE_SHOP_ID);
        assertNotNull(findById);
        assertEquals(COFFEE_SHOP_ID, findById.getId());
        assertEquals(NAME, findById.getName());

    }

    @Test
    public void thatFindByLocationWorks() {
        final CoffeeShop findByCoordinates = dao.findByCoordinates(LONGITUDE, LATITUDE);
        assertNotNull(findByCoordinates);
        assertEquals(NAME_BY_LOCATION, findByCoordinates.getName());

    }
}

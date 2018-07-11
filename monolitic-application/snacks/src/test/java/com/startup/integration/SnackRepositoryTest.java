package com.startup.integration;

import com.startup.infrastructure.persistence.model.Ingredient;
import com.startup.infrastructure.persistence.repository.SnackRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.springframework.jdbc.datasource.init.ScriptUtils.executeSqlScript;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class SnackRepositoryTest {

    @Autowired
    private SnackRepository snackRepository;

    @Autowired
    protected DataSource dataSource;

    @Before
    public void before() throws Exception {
        executeSqlScript(dataSource.getConnection(),
                new ClassPathResource("test_int.sql"));
    }

    @After
    public void tearDown() throws SQLException {
        executeSqlScript(dataSource.getConnection(),
                new ClassPathResource("delete.sql"));
    }

    @Test
    public void Should_Return_Ingredients() {
        List<Ingredient> ingredients = snackRepository.findAllIngredients();
        assertEquals(5, ingredients.size());
    }

}

package com.startup.e2e;


import com.startup.StartupApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.sql.DataSource;

import java.sql.SQLException;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.springframework.jdbc.datasource.init.ScriptUtils.executeSqlScript;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = StartupApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class SnackE2ETest {

    private static String INGREDIENTS = "/snack-api/ingredients";

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected DataSource dataSource;

    @Before
    public void before() throws Exception {
        executeSqlScript(dataSource.getConnection(),
                new ClassPathResource("test_e2e.sql"));
    }

    @After
    public void tearDown() throws SQLException {
        executeSqlScript(dataSource.getConnection(),
                new ClassPathResource("delete.sql"));
    }

    @Test
    public void Should_Return_Ingredients() throws Exception {
        MvcResult result = get(status().is(200));
        assertThat(result.getResponse().getContentAsString(), hasJsonPath("$.[*].id", hasSize(5)));
    }

    private MvcResult get(ResultMatcher status) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.get(INGREDIENTS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status)
                .andReturn();
    }

}

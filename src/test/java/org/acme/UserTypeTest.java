package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

@QuarkusTest
class UserTypeTest {

    @Inject
    UserTypeTestBean testBean;

    @Test
    void testQuery() {
        testBean.createEntity();

        assertFalse(testBean.getByQuery().isEmpty());
    }


}
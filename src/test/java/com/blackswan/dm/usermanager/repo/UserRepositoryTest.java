package com.blackswan.dm.usermanager.repo;

import com.blackswan.dm.usermanager.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback
    void testSavingUser() {
        User user = new User("Davy", "David", "Mbaimbai");
        User savedUser = userRepository.saveAndFlush(user);
        assertNotNull(savedUser);
        assertEquals("Davy", savedUser.getUsername());
        assertEquals("David", savedUser.getFirstName());
        assertEquals("Mbaimbai", savedUser.getLastName());
    }

    @Test
    @Rollback
    void testFindUserById() {
        User user = new User("Davy", "David", "Mbaimbai");
        User dbUser = userRepository.saveAndFlush(user);
        User savedUser = userRepository.getById(dbUser.getId());
        assertNotNull(savedUser);
        assertEquals(dbUser.getId(), savedUser.getId());
        assertEquals("Davy", savedUser.getUsername());
        assertEquals("David", savedUser.getFirstName());
        assertEquals("Mbaimbai", savedUser.getLastName());
    }
}

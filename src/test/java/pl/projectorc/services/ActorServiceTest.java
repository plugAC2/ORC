package pl.projectorc.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.projectorc.OrcApplication;
import pl.projectorc.repositories.ActorRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest(classes = OrcApplication.class)
class ActorServiceTest {

    @Resource
    private ActorRepository actorRepository;

    @Test
    void shouldGetAllActors() {

    }

}
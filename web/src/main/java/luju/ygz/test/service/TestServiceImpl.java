package luju.ygz.test.service;

import luju.ygz.test.repository.TestRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl {

    @Autowired
    private TestRepositoryImpl testRepository;

    public Object testQuery() {
        return testRepository.testQuery();
    }
}

package tr.org.ji.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import tr.org.ji.Entity.Role;
import tr.org.ji.repository.RoleRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("DataLoader is running ... ");

        if (!roleRepository.existsByName(ROLE_USER)) {
            Role userRole = new Role();
            userRole.setName(ROLE_USER);
            roleRepository.save(userRole);
        }
        if (!roleRepository.existsByName(ROLE_ADMIN)) {
            Role adminRole = new Role();
            adminRole.setName(ROLE_ADMIN);
            roleRepository.save(adminRole);
        }

    }
}

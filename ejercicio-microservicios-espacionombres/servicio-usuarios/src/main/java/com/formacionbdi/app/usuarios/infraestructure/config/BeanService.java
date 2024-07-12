package com.formacionbdi.app.usuarios.infraestructure.config;

import com.formacionbdi.app.usuarios.domain.ports.dao.RoleDao;
import com.formacionbdi.app.usuarios.domain.ports.dao.UserDao;
import com.formacionbdi.app.usuarios.domain.ports.repository.RoleRepository;
import com.formacionbdi.app.usuarios.domain.ports.repository.UserRepository;
import com.formacionbdi.app.usuarios.domain.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanService {

    @Bean
    public DeleteRoleService deleteRoleService(RoleRepository roleRepository) {
        return new DeleteRoleService(roleRepository);
    }

    @Bean
    public DeleteUserService deleteUserService(UserRepository userRepository) {
        return new DeleteUserService(userRepository);
    }

    @Bean
    public SaveRoleService saveRoleService(RoleRepository roleRepository) {
        return new SaveRoleService(roleRepository);
    }

    @Bean
    public SaveUserService saveUserService(UserRepository userRepository) {
        return new SaveUserService(userRepository);
    }

    @Bean
    public UpdateRoleService updateRoleService(RoleRepository roleRepository, RoleDao roleDao) {
        return new UpdateRoleService(roleRepository, roleDao);
    }

    @Bean
    public UpdateUserService updateUserService(UserRepository userRepository, UserDao userDao) {
        return new UpdateUserService(userRepository, userDao);
    }
}

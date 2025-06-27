package com.log.service;

import com.log.model.UsersModel;
import com.log.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registerUser (String name, String email, String password) {
        if (email == null || password == null) {
            return null;
        }else {
            if (usersRepository.findFirstByEmail ( email ).isPresent ()) {
                System.out.println("Email already exists");
                return null;
            }
            UsersModel usersModel = new UsersModel ();
            usersModel.setName (name);
            usersModel.setEmail (email);
            usersModel.setPassword (password);
            return usersRepository.save(usersModel);
        }
    }

    public UsersModel authenticate (String email, String password) {
        return usersRepository.findByEmailAndPassword ( email, password ). orElse ( null );
    }
}

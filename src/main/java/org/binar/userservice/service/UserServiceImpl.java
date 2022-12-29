package org.binar.userservice.service;


import lombok.extern.slf4j.Slf4j;
import org.binar.userservice.repository.UsersMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersMovieRepository userRepo;

    public UserServiceImpl(){

    }

    //Service Menambahkan user baru
    @Override
    public void addNewUser(String email, String password, String username) {
        try {
            userRepo.insertUserToDb(email, password, username);
        }catch (Exception e){
            log.error("ERROR has been found! because : {}", e.getMessage());
        }
    }

    //Service Mengupdate user
    @Override
    public void updateUser(String email, String password, String username) {
        try{
            userRepo.updateUserToDb(email, password, username);
        }catch (Exception e){
            log.error("ERROR has been found! because : {}", e.getMessage());
        }
    }

    //Service Menghapus user
    @Override
    public void deleteUser(String username) {
        try{
            userRepo.deleteUserFromDb(username);
        } catch (Exception e){
            log.error("ERROR has been found! because : {}", e.getMessage());
        }
    }

}

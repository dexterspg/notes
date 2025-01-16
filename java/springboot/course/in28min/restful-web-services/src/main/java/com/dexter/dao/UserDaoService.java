package com.dexter.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dexter.entity.User;

/**
 * UserDaoService
 */
@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<User>();
    private static int userCount= 0;

    static{
        users.add(new User(++userCount,"Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++userCount,"Jim", LocalDate.now().minusYears(20)));
    }

    public List<User> findAll(){
        return users;
    }


    public User findOne(int id){
        return users.stream().filter(user -> user.getId().equals(id) ).findFirst().orElse(null);
    }

   public User save(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id){
        users.removeIf(x -> x.getId().equals(id));
    }
}

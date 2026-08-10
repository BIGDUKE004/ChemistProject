package ng.Chemist.Data.repositories;

import ng.Chemist.Data.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    private static int count;
    private static List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {
        users.add(user);
        count++;
        return user;
    }

    @Override
    public void delete(User user) {
        users.remove(user);
        count--;
    }

    @Override
    public void deleteAll() {
        this.users.clear();
        this.count = 0;
    }

    @Override
    public long count() {
        return this.count;
    }

    @Override
    public User findByName(String name) {
        for(User user : users){
            if(user.getUserName().equalsIgnoreCase(name)){
                return user;
            }
        }
        return null;
    }

}

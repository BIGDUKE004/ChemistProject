package ng.Chemist.Data.repositories;

import ng.Chemist.Data.model.User;

public interface UserRepository {
    User save(User user);
    void delete(User user);
    void deleteAll();
    long count();
    User findByName(String name);
}

package StProject;

import org.springframework.data.repository.CrudRepository;

interface  UserDataAccesObject extends CrudRepository<User,Integer> {
     User findByEmail(String email);

}

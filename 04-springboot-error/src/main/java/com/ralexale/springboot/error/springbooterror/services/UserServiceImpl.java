package com.ralexale.springboot.error.springbooterror.services;

// import com.ralexale.springboot.error.springbooterror.models.domain.Role;
import com.ralexale.springboot.error.springbooterror.models.domain.User;
// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
// import java.util.NoSuchElementException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  List<User> users;

  @Override
  public List<User> findAll() {
    return users;
  }

  // @Override
  // public User findById(String id) {
  //   User user = null;

  //   for (User userTemp : users) {
  //     if (userTemp.getId().equals(id)) {
  //       user = userTemp;
  //     }
  //   }

  //   // try {
  //   //   user =
  //   //     users
  //   //       .stream()
  //   //       .filter(userTemp -> userTemp.getId().equals(id))
  //   //       .findFirst()
  //   //       .orElseThrow();
  //   // } catch (NoSuchElementException e) {
  //   //   throw new NoSuchElementException("not found user with the id: " + id);
  //   // }

  //   return user;
  // }

  @Override
  public Optional<User> findById(String id) {
    // Optional<User> user = users
    //   .stream()
    //   .filter(userTemp -> userTemp.getId().equals(id))
    //   .findFirst();

    // for (User userTemp : users) {
    //   if (userTemp.getId().equals(id)) {
    //     user = userTemp;
    //   }
    // }

    return users
      .stream()
      .filter(userTemp -> userTemp.getId().equals(id))
      .findFirst();
  }
}

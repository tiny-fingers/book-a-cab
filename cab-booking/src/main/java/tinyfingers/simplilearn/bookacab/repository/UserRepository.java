package tinyfingers.simplilearn.bookacab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tinyfingers.simplilearn.bookacab.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

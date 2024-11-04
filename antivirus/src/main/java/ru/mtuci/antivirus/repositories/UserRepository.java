package ru.mtuci.antivirus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mtuci.antivirus.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}

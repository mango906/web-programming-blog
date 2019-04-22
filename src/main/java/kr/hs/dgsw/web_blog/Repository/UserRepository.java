package kr.hs.dgsw.web_blog.Repository;

import kr.hs.dgsw.web_blog.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccount(String account);

    Optional<User> findById(Long id);
}

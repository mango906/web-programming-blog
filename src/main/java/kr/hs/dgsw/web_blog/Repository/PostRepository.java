package kr.hs.dgsw.web_blog.Repository;

import kr.hs.dgsw.web_blog.Domain.Post;
import kr.hs.dgsw.web_blog.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long Id);
}

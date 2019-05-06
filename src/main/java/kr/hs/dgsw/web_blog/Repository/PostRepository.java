package kr.hs.dgsw.web_blog.Repository;

import kr.hs.dgsw.web_blog.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findTopByUserIdOrderByIdDesc(Long userId);
}

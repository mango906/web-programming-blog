package kr.hs.dgsw.web_blog.Service;

import kr.hs.dgsw.web_blog.Domain.Post;
import kr.hs.dgsw.web_blog.Domain.User;
import kr.hs.dgsw.web_blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web_blog.Repository.PostRepository;
import kr.hs.dgsw.web_blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post get(Long userId) {
        return this.postRepository
                .findTopByUserIdOrderByIdDesc(userId)
                .orElse(null);
    }

    @Override
    public List<PostUsernameProtocol> get() {
        List<Post> postList = this.postRepository.findAll();
        List<PostUsernameProtocol> pList = new ArrayList<>();
        postList.forEach(post -> {
            Optional<User> found = this.userRepository.findById(post.getUserId());
            String username = (found.isPresent()) ? found.get().getName() : null;
            pList.add(new PostUsernameProtocol(post, username));
        });
        return pList;
    }

    @Override
    public PostUsernameProtocol add(Post post) {
        Post added = this.postRepository.save(post);
        String username = this.userRepository.findById(added.getUserId())
                .map(found -> found.getName())
                .orElse(null);
        return new PostUsernameProtocol(added, username);
    }

    @Override
    public Post update(Post post, Long id) {
        Optional<Post> found = postRepository.findById(id);
        if (found.isPresent()) {
            found.get().setTitle(post.getTitle());
            found.get().setContent(post.getContent());
//            found.get().setPictures(post.getPictures());
            return this.postRepository.save(found.get());
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Post> found = postRepository.findById(id);
        if (found.isPresent()) {
            postRepository.delete(found.get());
            return true;
        }
        return false;
    }

    @Override
    public Post getById(Long id) {
        Optional<Post> found = postRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        }
        return null;
    }
}

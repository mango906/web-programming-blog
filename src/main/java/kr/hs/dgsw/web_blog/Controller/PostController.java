package kr.hs.dgsw.web_blog.Controller;

import kr.hs.dgsw.web_blog.Domain.Post;
import kr.hs.dgsw.web_blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web_blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/addPost")
    public Post add(@RequestBody Post post) {
        return this.postService.add(post);
    }

    @GetMapping("/getPost")
    public List<PostUsernameProtocol> get() {
        return this.postService.get();
    }

    @PutMapping("/updatePost/{id}")
    public Post update(@RequestBody Post post, @PathVariable Long id) {
        return this.postService.update(post, id);
    }

    @DeleteMapping("/deletePost/{id}")
    public boolean delete(@PathVariable Long id) {
        return this.postService.delete(id);
    }
}

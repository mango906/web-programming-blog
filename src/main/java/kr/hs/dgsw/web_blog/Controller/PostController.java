package kr.hs.dgsw.web_blog.Controller;

import kr.hs.dgsw.web_blog.Domain.Post;
import kr.hs.dgsw.web_blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web_blog.Protocol.ResponseType;
import kr.hs.dgsw.web_blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/addPost")
    public ResponseFormat add(@RequestBody Post post) {
        try {
            return new ResponseFormat(
                    ResponseType.POST_ADD,
                    this.postService.add(post)
            );
        } catch (Exception e) {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }
    }

    @GetMapping("/getPostById")
    public ResponseFormat get(Long userId) {
        try {
            return new ResponseFormat(
                    ResponseType.POST_ID_GET,
                    this.postService.get(userId),
                    userId
            );
        } catch (Exception e) {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }
    }

    @GetMapping("/getPost")
    public ResponseFormat get() {
        try {
            return new ResponseFormat(
                    ResponseType.POST_GET,
                    this.postService.get()
            );
        } catch (Exception e) {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }

//        return this.postService.get();
    }

    @PutMapping("/updatePost/{id}")
    public ResponseFormat update(@RequestBody Post post, @PathVariable Long id) {
        try {
            return new ResponseFormat(
                    ResponseType.POST_UPDATE,
                    this.postService.update(post, id)
            );
        } catch (Exception e) {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }
//        return this.postService.update(post, id);
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseFormat delete(@PathVariable Long id) {
        try {
            return new ResponseFormat(
                    ResponseType.POST_DELETE,
                    this.postService.delete(id)
            );
        } catch (Exception e) {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }
//        return this.postService.delete(id);
    }
}

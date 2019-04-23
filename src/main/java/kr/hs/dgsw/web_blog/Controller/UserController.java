package kr.hs.dgsw.web_blog.Controller;

import kr.hs.dgsw.web_blog.Domain.User;
import kr.hs.dgsw.web_blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web_blog.Protocol.ResponseType;
import kr.hs.dgsw.web_blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseFormat list() {
        return new ResponseFormat(
                ResponseType.POST_ADD,
                "Hello"
        );
    }

    @GetMapping("/getUser/{account}")
    public ResponseFormat get(@PathVariable String account) {
        User user = this.userService.get(account);
        try {
            return new ResponseFormat(
                    ResponseType.USER_GET,
                    user,
                    user.getAccount()
            );
        } catch (Exception e) {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }

    }

    @PostMapping("/addUser")
    public ResponseFormat add(@RequestBody User user) {
        try {
            return new ResponseFormat(
                    ResponseType.USER_ADD,
                    this.userService.add(user)
            );
        } catch (Exception e) {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }
    }

    @PutMapping("/updateUser")
    public ResponseFormat update(@RequestBody User user) {
        try {
            User u = this.userService.update(user);
            return new ResponseFormat(
                    ResponseType.USER_UPDATE,
                    u,
                    u.getId()
            );
        } catch (Exception e) {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }
    }

    @DeleteMapping("/deleteUser/{account}")
    public ResponseFormat delete(@PathVariable String account) {
        try {
            return new ResponseFormat(
                    ResponseType.USER_DELETE,
                    this.userService.delete(account)
            );
        } catch (Exception e) {
            return new ResponseFormat(
                    ResponseType.FAIL,
                    null
            );
        }
    }

}

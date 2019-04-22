package kr.hs.dgsw.web_blog.Controller;

import kr.hs.dgsw.web_blog.Domain.User;
import kr.hs.dgsw.web_blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser/{account}")
    public User list(@PathVariable String account) {
        return this.userService.get(account);
    }

    @PostMapping("/addUser")
    public User add(@RequestBody User user){
        return this.userService.add(user);
    }

    @PutMapping("/updateUser")
    public User update(@RequestBody User user){
        return this.userService.update(user);
    }

    @DeleteMapping("/deleteUser/{account}")
    public boolean delete(@PathVariable String account){
        return this.userService.delete(account);
    }

}

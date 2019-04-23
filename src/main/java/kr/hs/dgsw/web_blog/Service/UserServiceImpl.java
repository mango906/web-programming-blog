package kr.hs.dgsw.web_blog.Service;

import kr.hs.dgsw.web_blog.Domain.User;
import kr.hs.dgsw.web_blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User add(User user) {
        Optional<User> found = userRepository.findByAccount(user.getAccount());
        if (found.isPresent()) return null;
        return this.userRepository.save(user);
    }

    @Override
    public User get(String account){
        Optional<User> found = userRepository.findByAccount(account);
        if (found.isPresent()) {
            return found.get();
        }
        return null;
    }

    @Override
    public User update(User user) {
        String account = user.getAccount();
        Optional<User> found = userRepository.findByAccount(user.getAccount());
        if(found.isPresent()){
            found.get().setAccount(user.getAccount());
            found.get().setPassword(user.getPassword());
            found.get().setName(user.getName());
            found.get().setEmail(user.getEmail());
            found.get().setPhone(user.getPhone());
            found.get().setProfilePath(user.getProfilePath());
            return found.get();
        }
        return null;
    }

    @Override
    public boolean delete(String account) {
        Optional<User> found = userRepository.findByAccount(account);
        if(found.isPresent()){
            this.userRepository.delete(found.get());
            return true;
        }
        return false;
    }
}

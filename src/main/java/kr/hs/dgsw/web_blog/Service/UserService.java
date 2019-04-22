package kr.hs.dgsw.web_blog.Service;

import kr.hs.dgsw.web_blog.Domain.User;

public interface UserService {
    public User add(User user);

    public User get(String account);

    public User update(User user);

    public boolean delete(String account);
}

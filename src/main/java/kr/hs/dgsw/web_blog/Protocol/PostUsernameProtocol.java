package kr.hs.dgsw.web_blog.Protocol;

import kr.hs.dgsw.web_blog.Domain.Post;
import lombok.Data;

@Data
public class PostUsernameProtocol extends Post{
    private String username;

    public PostUsernameProtocol(Post p, String username) {
        super(p);
        this.username = username;
    }
}

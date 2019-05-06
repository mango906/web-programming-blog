package kr.hs.dgsw.web_blog.Service;

import kr.hs.dgsw.web_blog.Domain.Post;
import kr.hs.dgsw.web_blog.Protocol.PostUsernameProtocol;

import java.util.List;

public interface PostService {

    public Post get(Long Id);

    public List<PostUsernameProtocol> get();

    public PostUsernameProtocol add(Post post);

    public Post update(Post post, Long id);

    public boolean delete(Long id);

    public Post getById(Long Id);
}

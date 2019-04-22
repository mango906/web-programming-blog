package kr.hs.dgsw.web_blog.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private String originalName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String storedPath;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    @UpdateTimestamp
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified;

    public Post(Post p) {
        this.id = p.getId();
        this.userId = p.getUserId();
        this.title = p.getTitle();
        this.content = p.getContent();
        this.originalName = p.getOriginalName();
        this.storedPath = p.getStoredPath();
        this.created = p.getCreated();
        this.modified = p.getModified();
    }
}

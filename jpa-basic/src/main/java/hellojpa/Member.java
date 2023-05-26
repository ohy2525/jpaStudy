package hellojpa;
import javax.persistence.*;
import java.util.Date;
@Entity
public class Member {

    @Id
    private String id;

    @Column(name = "name")
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Member() {
    }
}

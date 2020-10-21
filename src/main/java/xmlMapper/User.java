package xmlMapper;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Auther zhaoxin
 * @Date 2020/10/17 10:11
 */
@XmlRootElement
public class User {

    private String name;
    private String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

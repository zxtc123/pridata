package xmlMapper;

/**
 * @Auther zhaoxin
 * @Date 2020/10/17 11:25
 */
public class City {
    private Integer id;
    private String name;

    public City(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

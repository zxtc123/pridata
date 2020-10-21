package xmlMapper;

import java.util.List;

/**
 * @Auther zhaoxin
 * @Date 2020/10/17 11:24
 */
public class Province {
    private Integer id;
    private String name;
    private List cities;

    public Province(Integer id, String name, List cities) {
        this.id = id;
        this.name = name;
        this.cities = cities;
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

    public List getCities() {
        return cities;
    }

    public void setCities(List cities) {
        this.cities = cities;
    }
}

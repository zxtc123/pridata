package xmlMapper;

import cn.hutool.core.util.XmlUtil;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther zhaoxin
 * @Date 2020/10/17 10:10
 */
public class Demo1 {

    @Test
    public void test() throws Exception {
        /***************模拟查询数据库的结果********************/
        City c1 = new City(1, "长春市");
        City c2 = new City(2, "吉林市");
        City c3 = new City(3, "松原市");
        City c4 = new City(4, "四平市");
        City c5 = new City(5, "通化市");

        List<City> cities = new ArrayList<>();
        cities.add(c1);
        cities.add(c2);
        cities.add(c3);
        cities.add(c4);
        cities.add(c5);

        Province p = new Province(1, "吉林省", cities);
        /***************模拟查询数据库的结果********************/

        /***************如何进行转换**************************/

        //1 创建XStream实例对象
        XStream xStream = new XStream();
        /*
         * 2 为xml格式的标签设置别名:
         * 	alias(String name, Class type)
         * 		* name:表示别名
         * 		* type:指定要为哪个JavaBean起别名
         */
        xStream.alias("province", Province.class);
        xStream.alias("city", City.class);

        /*
         * 3 为xml格式的标签设置属性
         * 	useAttributeFor(Class definedIn, String fieldName)
         * 		* definedIn:指定要为哪个JavaBean设置属性
         * 		* fieldName:指定要设置哪个属性
         */
        xStream.useAttributeFor(Province.class, "id");
        xStream.useAttributeFor(Province.class, "name");
        xStream.useAttributeFor(City.class, "id");
        xStream.useAttributeFor(City.class, "name");

        //4 直接进行转换xml
        String xml = xStream.toXML(p);
        System.out.println(xml);
    }
}

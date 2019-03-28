import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xuecheng.test.freemarker.model.Student;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * @Author: 98050
 * @Time: 2019-03-28 15:28
 * @Feature:
 */
public class FreemarkerTest {

    /**
     * 基于模板文件进行静态化
     * @throws IOException
     * @throws TemplateException
     */
    @Test
    public void testGenerateHtml() throws IOException, TemplateException {
        //1.创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //2.设置模板路径
        String classpath = this.getClass().getResource("/").getPath();
        configuration.setDirectoryForTemplateLoading(new File(classpath + "/templates/"));
        //3.设置字符集
        configuration.setDefaultEncoding("utf-8");
        //4.加载模板
        Template template = configuration.getTemplate("test1.ftl");
        //5.数据模型
        Map<String,Object> map = getData();
        //6.静态化
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        //7.静态化内容
        System.out.println(content);
        InputStream inputStream = IOUtils.toInputStream(content);
        //8.输出文件
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/test1.html"));
        int copy = IOUtils.copy(inputStream, fileOutputStream);
    }

    /**
     * 基于模板字符串生成静态文件
     * @throws IOException
     * @throws TemplateException
     */
    @Test
    public void testGenerateHtmlByString() throws IOException, TemplateException {
        //1.创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //2.模板内容
        String templateString="" +
                "<html>\n" +
                "    <head></head>\n" +
                "    <body>\n" +
                "    名称：${name}\n" +
                "    </body>\n" +
                "</html>";
        //3.模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template", templateString);
        configuration.setTemplateLoader(stringTemplateLoader);
        //4.得到模板
        Template template = configuration.getTemplate("template","utf-8");
        //5.数据模型
        Map<String,Object> map = new HashMap<>();
        map.put("name", "李四");
        //6.静态化
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        //7.静态化内容
        System.out.println(content);
        InputStream inputStream = IOUtils.toInputStream(content);
        //8.输出文件
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/test2.html"));
        int copy = IOUtils.copy(inputStream, fileOutputStream);
    }

    private Map<String, Object> getData() {
        Map<String,Object> map = new HashMap<>();
        //向数据模型放数据
        map.put("name","李四");
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMondy(1000.86f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMondy(200.1f);
        stu2.setAge(19);
        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus",stus);
        //准备map数据
        HashMap<String,Student> stuMap = new HashMap<>();
        stuMap.put("stu1",stu1);
        stuMap.put("stu2",stu2);
        //向数据模型放数据
        map.put("stu1",stu1);
        //向数据模型放数据
        map.put("stuMap",stuMap);
        return map;
    }
}

package com.daotest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired private StudentService studentService;

    /*
    @RequestMapping("/search/one")
    public String searchOne(@RequestParam("name") String name,
                            ModelMap modelMap) {
        Student student = studentService.getStudentByName(name);
        modelMap.addAttribute("student", student);

        return "searchone";
    }
    */

    @RequestMapping("/search/one")
    public String searchOne(@RequestParam("name") String name,
                            ModelMap modelMap) {

        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        StudentMapper studentMapper = context.getBean("studentmapper", StudentMapper.class);
        Student student = studentMapper.selectByName(name);

        modelMap.addAttribute("student", student);

        ((ConfigurableApplicationContext) context).close();

        return "searchone";
    }

    @RequestMapping("/search/all")
    public String searchAll(ModelMap modelMap) {
        List<Student> studentList = studentService.getStudents();
        modelMap.addAttribute("students", studentList);

        return "searchall";
    }

}

package com.daotest;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class SearchController {


    @Resource private StudentService studentService;

    @RequestMapping("/search/one")
    public String searchOne(@RequestParam("name") String name,
                            ModelMap modelMap) {
        Student student = studentService.getStudentByName(name);
        modelMap.addAttribute("student", student);

        return "searchone";
    }



    /*
    @RequestMapping("/search/all")
    public String searchAll(ModelMap modelMap) {
        List<Student> studentList = studentService.getStudents();
        modelMap.addAttribute("students", studentList);

        return "searchall";
    }
    */

}

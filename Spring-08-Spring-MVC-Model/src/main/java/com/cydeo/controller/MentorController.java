package com.cydeo.controller;

import com.cydeo.model.Mentor;
import com.cydeo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/mentor")
public class MentorController {
    @RequestMapping("/list") //localhost:8080/mentor/list
    public String showTable(Model model) {

        List<Mentor> mentorList = new ArrayList<>();

        mentorList.add(new Mentor("Mike", "Smith", 45, Mentor.Gender.MALE));
        mentorList.add(new Mentor("Tom", "Hanks", 65, Mentor.Gender.MALE));
        mentorList.add(new Mentor("Ammy", "Bryan", 35, Mentor.Gender.FEMALE));

      model.addAttribute("mentors",mentorList);
        return "mentor/mentor-list";
    }
}

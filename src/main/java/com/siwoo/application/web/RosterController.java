package com.siwoo.application.web;

import com.siwoo.application.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/roster")
public class RosterController {

    private List<Member> members = new ArrayList<>();

    public RosterController() {
        members.add(new Member("John","Lennon"));
        members.add(new Member("Paul","McCartney"));
        members.add(new Member("George","Harrison"));
        members.add(new Member("Ringo","Starr"));
    }

    @GetMapping("/list")
    public void list(Model model){
        model.addAttribute(members);
    }

    @GetMapping("/member")
    public void member(@RequestParam("id") Integer id, Model model){
        model.addAttribute(members.get(id));
    }
}

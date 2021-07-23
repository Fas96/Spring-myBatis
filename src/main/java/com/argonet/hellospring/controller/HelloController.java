package com.argonet.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(@RequestParam("name") String name, Model model){
      model.addAttribute("data",name);
      return "hello";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello"+name;
    }

    @GetMapping("Hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello api = new Hello();
        api.setName(name);
        return api;

    }
    static class Hello{
        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

package com.projecct.sprinttask2.controller;


import com.projecct.sprinttask2.model.CourseModel;
import com.projecct.sprinttask2.model.RequestModel;
import com.projecct.sprinttask2.repository.CourseRepository;
import com.projecct.sprinttask2.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = "/")
    public String indexPage(Model model){
        List<RequestModel> requestModelList = requestRepository.findAll();
        model.addAttribute("requests", requestModelList);
        return "index";
    }


    @GetMapping(value = "/new-requests")
    public String newRequests(Model model){
        List<RequestModel> requestModelList = requestRepository.findAllByHandledEquals(false);
        model.addAttribute("requests", requestModelList);
        return "new-requests";
    }


    @GetMapping(value = "/finished-requests")
    public String finishedRequests(Model model){
        List<RequestModel> requestModelList = requestRepository.findAllByHandledEquals(true);
        model.addAttribute("requests", requestModelList);
        return "finished-requests";
    }


    @GetMapping(value = "/add-request")
    public String addRequestPage(Model model){
        List<CourseModel> courseModelList = courseRepository.findAll();
        model.addAttribute("courses", courseModelList);

        return "add-request";
    }


    @PostMapping(value = "/add-request")
    public String addRequest(
            @RequestParam(name = "userName") String userName,
            @RequestParam(name = "courseName") CourseModel courseModel,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "commentary") String commentary
    ){
        RequestModel request = new RequestModel();
        request.setUserName(userName);
        request.setCourseModel(courseModel);
        request.setPhone(phone);
        request.setCommentary(commentary);
        request.setHandled(false);

        requestRepository.save(request);
        return "redirect:/";
    }


    @GetMapping(value = "/details/{requestId}")
    public String requestDetails(@PathVariable(name = "requestId") Long id, Model model) {

        RequestModel request = requestRepository.findById(id).orElse(null);
        model.addAttribute("request", request);

        return "details";
    }


    @PostMapping(value = "handle-request")
    public String handleRequest(@RequestParam(name = "id") Long id){
        RequestModel request = requestRepository.findById(id).orElse(null);

        request.setHandled(true);
        requestRepository.save(request);

        return "redirect:/";
    }


    @PostMapping(value = "delete-request")
    public String deleteRequest(@RequestParam(name = "id") Long id){
        requestRepository.deleteById(id);

        return "redirect:/";
    }




}

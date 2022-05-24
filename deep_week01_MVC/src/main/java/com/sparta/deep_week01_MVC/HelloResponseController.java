package com.sparta.deep_week01_MVC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello/response")      //얘가 먼저 오고 그 뒤에 이 안에있는 GetMapping이 붙어야한다?
public class HelloResponseController {
    // [Response header]
// Location: http://localhost:8080/hello.html
    @GetMapping("/html/redirect")
    public String htmlFile() {
        return "redirect:/hello.html";
    }

    // [Response header]
// Content-Type: text/html
// [Response body]
// * resources/templates/hello.html 의 text 내용
// <!DOCTYPE html>
// <html>
// <head><meta charset="UTF-8"><title>By Templates engine</title></head>
// <body>Hello, Spring 정적 웹 페이지!!</body>
// </html>
    @GetMapping("/html/templates")      //templates 안에있는 hello.html을 찾는다
    public String htmlTemplates() {
        return "hello";
    }

    // [Response header]
// Content-Type: text/html
// [Response body]
// <!DOCTYPE html>
// <html>
// <head><meta charset="UTF-8"><title>By @ResponseBody</title></head>
// <body>Hello, Spring 정적 웹 페이지!!</body>
// </html>
    @GetMapping("/body/html")       //헤더 부분 말고 바디 부분에 직접 html을 때려박는다.
    @ResponseBody                   //이 친구가 있으면 view를 통해 내려주는 것이 아닌(템플릿 엔진을 통해??) 바로 바디에 때려박는다.
    public String helloStringHTML() {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head><meta charset=\"UTF-8\"><title>By @ResponseBody</title></head>" +
                "<body> Hello, 정적 웹 페이지!!</body>" +
                "</html>";
    }

    // [Response header]
// Content-Type: text/html
// [Response body]
// * resources/templates/hello-visit.html 의 text 내용
    @GetMapping("/html/dynamic")
    public String helloHtmlFile(Model model) {
        visitCount++;
        model.addAttribute("visits", visitCount);
// resources/templates/hello-visit.html
        return "hello-visit";
    }

    private static long visitCount = 0;

    // [Response header]
// Content-Type: text/html
// [Response body]
// {"name":"BTS","age":28}
    @GetMapping("/json/string")
    @ResponseBody
    public String helloStringJson() {
        return "{\"name\":\"BTS\",\"age\":28}";
    }

    // [Response header]
// Content-Type: application/json
// [Response body]
// {"name":"BTS","age":28}
    @GetMapping("/json/class")
    @ResponseBody
    public Star helloJson() {
        return new Star("BTS", 28);
    }
}
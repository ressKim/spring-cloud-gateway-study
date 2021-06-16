package com.study.firsttestservice;

import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service/")
@Slf4j
public class FirstServiceController {

    Environment env;

    @Autowired
    public FirstServiceController(Environment env) {
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First service.";
    }


    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info("header = {}", header);
        return "Hello message in first service";
    }

    //두개 이상 실행하고 check 호출시 port 가 번갈아가며 실행되는것을 확인 해 볼 수 있다

    @GetMapping("/check")
    public String check(HttpServletRequest request){
        log.info("Server port = {}", request.getServerPort());
        return String.format("Hi, there. This is a message from first service on port %s."
        , env.getProperty("local.server.port"));

    }
}
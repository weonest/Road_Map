package naver.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {

    @RequestMapping("/map")
    public String getMap() {
        return "mainMap";
    }

    @RequestMapping("/signup")
    public String sign() {
        return "signup";
    }
}

package map.kyonggiyo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;

@Controller
public class MapController {

    @RequestMapping("/map")
    public String getMap() {
        return "mainMap";
    }

//    @RequestMapping("/map")
//    public String mapList() {
//        List<>
//    }
}

package naver.map.controller;

import naver.map.domain.Map;
import naver.map.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MapController {


    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @RequestMapping("/map")
    public String list(Model model) {
        List<Map> maps = mapService.findMaps();
        model.addAttribute("maps", maps);
        System.out.println(maps.toString());
        return "mainMap";
    }



//    @RequestMapping("/map")
//    public ModelAndView content() {
//        List<Map> maps = mapService.findMaps();
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("mainMap");
//        mv.addObject("maps", maps);
//        return mv;
//    }

}

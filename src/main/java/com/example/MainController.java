package com.example;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MainController
{

    @PostMapping(value = "/activities/simplify", produces = "application/vnd.galvanize.detailed+json")
    @JsonView(Views.DetailedView.class)
    public List<ActivitiesDTO> detailedPostController(@RequestBody Map<String, ArrayList<Activities>> body)
    {
        List<ActivitiesDTO> activitiesDTOS = new ArrayList<>();

        for(Activities acts : body.get("activities"))
        {

            activitiesDTOS.add(new ActivitiesDTO(acts));
        }

        return activitiesDTOS;

    }

    @PostMapping(value = "/activities/simplify", produces = "application/vnd.galvanize.compact+json")
    @JsonView(Views.CompactView.class)
    public List<ActivitiesDTO> compactPostController(@RequestBody Map<String, List<Activities>> body)
    {
        List<ActivitiesDTO> activitiesDTOS = new ArrayList<>();

        for(Activities acts : body.get("activities"))
        {

            activitiesDTOS.add(new ActivitiesDTO(acts));
        }

        return activitiesDTOS;

    }


}

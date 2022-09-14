package com.example;

import com.fasterxml.jackson.annotation.JsonView;

public class ActivitiesDTO {


    @JsonView(Views.DetailedView.class)
    int userId;

    @JsonView(Views.CompactView.class)
    String user;
@JsonView(Views.DetailedView.class)
    String email;
    @JsonView(Views.CompactView.class)
    String date;
    @JsonView(Views.CompactView.class)
    String statusText;


    public ActivitiesDTO(Activities acts)
    {
        userId = acts.getUser().getId();
        user = acts.getUser().getUsername();
        email = parseEmail(acts);
        date = acts.getStatus().getDate();
        statusText = acts.getStatus().getText();
    }

    public String parseEmail(Activities act)
    {
        for(Email e : act.getUser().emails)
        {
            if (e.isPrimary())
            {
                return e.getAddress();
            }
        }
        return "";
    }
}

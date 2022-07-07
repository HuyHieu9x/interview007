package com.techvify.interview.payLoad.request;

import com.techvify.interview.validation.title.TitleNameExists;
import javax.validation.constraints.NotBlank;

public class TitleRequest {
    @NotBlank(message = "{title.name.not-blank}")
    @TitleNameExists
    private String name;

    public TitleRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "image",
        "imageType",
        "nutrition"
})
@Data
public class Result {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("image")
    public String image;
    @JsonProperty("imageType")
    public String imageType;
    @JsonProperty("nutrition")
    public Nutrition nutrition;
}

package Response;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "nutrients"
})
@Data
public class Nutrition {
    @JsonProperty("nutrients")
    public List<Nutrient> nutrients = new ArrayList<Nutrient>();
}

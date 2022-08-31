package Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "amount",
        "unit"
})
@Data
public class Nutrient {
    @JsonProperty("name")
    public String name;
    @JsonProperty("amount")
    public Double amount;
    @JsonProperty("unit")
    public String unit;
}

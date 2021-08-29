package guru.springframework.spring5recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {


    private String id;

    private String recipeId;

    @NotBlank
    @Size(min = 3, max = 255)
    private String description;

    @NotNull
    @Min(1)
    private BigDecimal amount;

    @NotNull
    private UnitOfMeasureCommand uom;
}

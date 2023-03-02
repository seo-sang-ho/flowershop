package university.flowershop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FlowerForm {

    @NotEmpty
    private String name;

    @NotEmpty
    private String flowerKind;

    @NotNull
    private int price;

    @NotNull
    private int stockQuantity;

    @NotEmpty
    private String description;


}

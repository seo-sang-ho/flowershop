package university.flowershop.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import university.flowershop.domain.item.Accessory;
import university.flowershop.service.AccessoryService;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccessoryController {

    private final AccessoryService accessoryService;

    @GetMapping("/accessory/new")
    public String createFlowerForm(Model model) {
        model.addAttribute("accessoryForm", new AccessoryForm());
        return "products/addAccessoryForm";
    }

    @PostMapping("/accessory/new")
    public String createAccessory(@Valid AccessoryForm form) {

        Accessory accessory = new Accessory();
        accessory.setPrdNum(form.getPrdNum());
        accessory.setName(form.getName());
        accessory.setPrice(form.getPrice());
        accessory.setStockQuantity(form.getStockQuantity());
        accessory.setDescription(form.getDescription());

        accessoryService.saveAccessory(accessory);
        return "redirect:/loginIndex";
    }

    @GetMapping("/accessory/findAll")
    public String findAccessory(Model model) {
        List<Accessory> accessory = accessoryService.findAccessory();
        model.addAttribute("accessory", accessory);
        return "products/prodAccessory";
    }

    @GetMapping("/accessory/{accessoryId}")
    public String getProductDetail(@PathVariable Long accessoryId, Model model) {
        Accessory accessory = accessoryService.findOne(accessoryId);
        model.addAttribute("accessory", accessory);
        return "products/accessoryDetail";
    }

}

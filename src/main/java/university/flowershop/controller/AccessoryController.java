package university.flowershop.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import university.flowershop.domain.Cart;
import university.flowershop.domain.CartItem;
import university.flowershop.domain.item.Accessory;
import university.flowershop.domain.item.Flower;
import university.flowershop.repository.FlowerRepository;
import university.flowershop.service.AccessoryService;
import university.flowershop.service.FlowerService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccessoryController {

    private final AccessoryService accessoryService;

    @GetMapping("/accessory/new") // 꽃 상품 추가 화면으로 간다
    public String createFlowerForm(Model model) {
        model.addAttribute("accessoryForm", new AccessoryForm());
        return "products/addAccessoryForm";
    }

    @PostMapping("/accessory/new") // 꽃 상품 추가 로직 실행
    public String createAccessory(@Valid AccessoryForm form, BindingResult bindingResult, Model model) {

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

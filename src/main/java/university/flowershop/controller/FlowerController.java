package university.flowershop.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import university.flowershop.domain.item.Flower;
import university.flowershop.repository.FlowerRepository;
import university.flowershop.service.FlowerService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FlowerController {

    private final FlowerRepository flowerRepository;
    private final FlowerService flowerService;

    @GetMapping("/flower/new") // 꽃 상품 추가 화면으로 간다
    public String createFlowerForm(Model model) {
        model.addAttribute("flowerForm", new FlowerForm());
        return "products/addFlowerForm";
    }

    @PostMapping("/flower/new") // 꽃 상품 추가 로직 실행
    public String createFlower(@Valid FlowerForm form, BindingResult bindingResult, Model model) {

        Flower flower = new Flower();
        flower.setPrdNum(form.getPrdNum());
        flower.setName(form.getName());
        flower.setPrice(form.getPrice());
        flower.setStockQuantity(form.getStockQuantity());
        flower.setDescription(form.getDescription());

        flowerService.saveFlower(flower);
        return "redirect:/loginIndex";
    }

    @GetMapping("/flower/findAll")
    public String findFlowers(Model model) {
        List<Flower> flowers = flowerService.findFlowers();
        model.addAttribute("flowers", flowers);
        return "products/prodFlower";
    }

    @GetMapping("/flowers/{flowerId}")
    public String getProductDetail(@PathVariable Long flowerId, Model model) {
        Flower flowers = flowerService.findOne(flowerId);
        model.addAttribute("flowers", flowers);
        return "products/flowerDetail";
    }

}

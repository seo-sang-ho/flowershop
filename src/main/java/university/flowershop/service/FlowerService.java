package university.flowershop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.flowershop.domain.item.Flower;
import university.flowershop.repository.FlowerRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FlowerService {

    @Autowired
    private FlowerRepository flowerRepository;

    //상품등록
    @Transactional
    public void saveFlower(Flower flower) {
        flowerRepository.save(flower);
    }

    //상품 전체 조회
    public List<Flower> findFlowers() {
        return flowerRepository.findAll();
    }

    //상품 아이디 조회
    public Flower findOne(Long id) {
        return flowerRepository.findById(id);
    }

    //상품 수정  id description name price stockQuantity
    @Transactional
    public void updateFlower(Long id, String name, int price, int stockQuantity, String description) {
        Flower flower = flowerRepository.findById(id);
        flower.setName(name);
        flower.setPrice(price);
        flower.setStockQuantity(stockQuantity);
        flower.setDescription(description);
    }


    //상품 삭제
    @Transactional
    public void deleteFlower(Long id) {
        flowerRepository.deleteById(id);
    }

    //상품 검색 기능
    @Transactional
    public List<Flower> searchFlower(String keyword) {
        return flowerRepository.Search(keyword);
    }
}

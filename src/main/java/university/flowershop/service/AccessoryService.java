package university.flowershop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.flowershop.domain.item.Accessory;
import university.flowershop.domain.item.Flower;
import university.flowershop.repository.AccessoryRepository;
import university.flowershop.repository.FlowerRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccessoryService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    //상품등록
    @Transactional
    public void saveAccessory(Accessory accessory) {
        accessoryRepository.save(accessory);
    }

    //상품 전체 조회
    public List<Accessory> findAccessory() {
        return accessoryRepository.findAll();
    }

    //상품 아이디 조회
    public Accessory findOne(Long id) {
        return accessoryRepository.findById(id);
    }

    //상품 수정  id description name price stockQuantity
    @Transactional
    public void updateAccessory(Long id, String name, int price, int stockQuantity, String description) {
        Accessory accessory = accessoryRepository.findById(id);
        accessory.setName(name);
        accessory.setPrice(price);
        accessory.setStockQuantity(stockQuantity);
        accessory.setDescription(description);
    }


    //상품 삭제
    @Transactional
    public void deleteAccessory(Long id) {
        accessoryRepository.deleteById(id);
    }

    //상품 검색 기능
    @Transactional
    public List<Accessory> searchAccessory(String keyword) {
        return accessoryRepository.Search(keyword);
    }


    @PostConstruct
    public void initAccessory() {
        Accessory accessory = new Accessory();
        accessory.setPrdNum("3");
        accessory.setName("물뿌리개");
        accessory.setPrice(2000);
        accessory.setDescription("물뿌리개입니다.");
        accessory.setStockQuantity(20);

        accessoryRepository.save(accessory);

        log.info("소품 상품 추가되는 로그");
    }
}

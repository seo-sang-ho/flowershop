package university.flowershop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.flowershop.domain.Notice;
import university.flowershop.repository.NoticeRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public void saveNotice(Notice notice) {
        noticeRepository.save(notice);
    }

    public List<Notice> showNotice() {
        return noticeRepository.findAll();
    }

    public void updateNotice(Long id, String title, String content) {
        Notice notice = noticeRepository.findById(id);
        notice.setTitle(title);
        notice.setContent(content);

        noticeRepository.save(notice);
    }

    public void deleteNotice(Notice notice) {
        noticeRepository.delete(notice);
    }
}

package com.library.cosmart_joybox.book.service;

import com.library.cosmart_joybox.book.entity.BookEntry;
import com.library.cosmart_joybox.book.entity.Pickup;
import com.library.cosmart_joybox.book.entity.PickupEntry;
import com.library.cosmart_joybox.book.entity.SubjectEntry;
import com.library.cosmart_joybox.constant.Messages;
import com.library.cosmart_joybox.exception.EmptyResultException;
import com.library.cosmart_joybox.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    @Autowired
    private RestTemplate restTemplate;

    public List<BookEntry> getBookListBySubject(String subject)
            throws ServiceException, EmptyResultException {

        var enrichedUrl = getEnrichedUrl(subject);
        ResponseEntity<SubjectEntry> response = restTemplate.getForEntity(enrichedUrl, SubjectEntry.class);

        if (!response.getStatusCode().equals(HttpStatus.OK))
            throw new ServiceException(Messages.REST_API_CALL_ERROR.get());

        if (Objects.isNull(response.getBody()))
            throw new EmptyResultException(Messages.EMPTY_DATA_ERROR.get());

        if (CollectionUtils.isEmpty(response.getBody().getWorks()))
            throw new EmptyResultException(Messages.EMPTY_DATA_ERROR.get());

        return response.getBody().getWorks();
    }

    public PickupEntry savePickupSchedule(PickupEntry entry) {
        Pickup.addPickupSchedule(entry);
        return entry;
    }

    public List<PickupEntry> getPickupSchedules() {
        return Pickup.getPickupSchedules();
    }

    private String getEnrichedUrl(String subject) {
        return "https://openlibrary.org/subjects/" + subject + ".json";
    }
}

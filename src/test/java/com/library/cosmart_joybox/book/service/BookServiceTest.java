package com.library.cosmart_joybox.book.service;

import com.library.cosmart_joybox.book.entity.BookEntry;
import com.library.cosmart_joybox.book.entity.SubjectEntry;
import com.library.cosmart_joybox.exception.EmptyResultException;
import com.library.cosmart_joybox.exception.ServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks private BookService service;

    @Mock private RestTemplate restTemplate;

    private BookEntry getBookEntry() {
        BookEntry entry = new BookEntry();
        entry.setTitle("test book");
        entry.setEdition("edition 1");
        return entry;
    }

    private SubjectEntry getSubjectEntry() {
        SubjectEntry entry = new SubjectEntry();
        entry.setName("test subject");
        entry.setWorkCount(1L);
        BookEntry bookEntry = new BookEntry();
        bookEntry.setTitle("test book");
        bookEntry.setEdition("edition 1");

        entry.setWorks(List.of(bookEntry));
        return entry;
    }

    private ResponseEntity<SubjectEntry> getOpenLibraryResponse(HttpStatus status, SubjectEntry entry) {
        return ResponseEntity.status(status).body(entry);
    }

    @Nested
    class getBookListBySubject {
        @Test
        void when_get_book_list_by_subject_success() throws ServiceException, EmptyResultException {
            when(restTemplate.getForEntity(anyString(), eq(SubjectEntry.class)))
                    .thenReturn(getOpenLibraryResponse(HttpStatus.OK, getSubjectEntry()));

            var response = service.getBookListBySubject("test subject");

            verify(restTemplate, times(1)).getForEntity(anyString(), eq(SubjectEntry.class));
            Assertions.assertEquals("test book", response.get(0).getTitle());
        }

        @Test
        void when_get_book_list_by_subject_success_error() {
            when(restTemplate.getForEntity(anyString(), eq(SubjectEntry.class)))
                    .thenReturn(getOpenLibraryResponse(HttpStatus.INTERNAL_SERVER_ERROR, getSubjectEntry()));

            Assertions.assertThrows(ServiceException.class, () -> service.getBookListBySubject("test subject"));

            verify(restTemplate, times(1)).getForEntity(anyString(), eq(SubjectEntry.class));
        }

        @Test
        void when_get_book_list_by_subject_success_return_null() {
            when(restTemplate.getForEntity(anyString(), eq(SubjectEntry.class)))
                    .thenReturn(getOpenLibraryResponse(HttpStatus.OK, null));

            Assertions.assertThrows(EmptyResultException.class, () -> service.getBookListBySubject("test subject"));

            verify(restTemplate, times(1)).getForEntity(anyString(), eq(SubjectEntry.class));
        }

        @Test
        void when_get_book_list_by_subject_success_return_empty() {
            var mockResponse = getOpenLibraryResponse(HttpStatus.OK, getSubjectEntry());
            mockResponse.getBody().setWorks(List.of());
            when(restTemplate.getForEntity(anyString(), eq(SubjectEntry.class)))
                    .thenReturn(mockResponse);

            Assertions.assertThrows(EmptyResultException.class, () -> service.getBookListBySubject("test subject"));

            verify(restTemplate, times(1)).getForEntity(anyString(), eq(SubjectEntry.class));
        }
    }
}
package com.nguyenphucthienan.springwebapp.bootstrap;

import com.nguyenphucthienan.springwebapp.model.Author;
import com.nguyenphucthienan.springwebapp.model.Book;
import com.nguyenphucthienan.springwebapp.model.Publisher;
import com.nguyenphucthienan.springwebapp.repository.AuthorRepository;
import com.nguyenphucthienan.springwebapp.repository.BookRepository;
import com.nguyenphucthienan.springwebapp.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, PublisherRepository publisherRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Publisher collins = new Publisher("Harper Collins", "St. Peter Street");
        publisherRepository.save(collins);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", collins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2ee Development without EJB", "6789", collins);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}

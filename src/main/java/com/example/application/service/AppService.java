package com.example.application.service;

import com.example.application.backend.entity.Personel;
import com.example.application.repository.PersonelRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AppService {
    private final PersonelRepository personelRepository;

    public AppService(PersonelRepository personelRepository){
        this.personelRepository = personelRepository;
    }

    public List<Personel>findAll(){
        return personelRepository.findAll();
    }

    public List<Personel> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return personelRepository.findAll();
        }
        else{
            return personelRepository.search(stringFilter);
        }
    }

    public long countContacts() {
        return personelRepository.count();
    }

    public void deleteContact(Personel personel) {
        personelRepository.delete(personel);
    }

    public void saveContact(Personel personel) {
        if (personel == null) {
            System.err.println("Personel is null. Are you sure you have connected your form to the application?");
            return;
        }
        personelRepository.save(personel);
    }

    @PostConstruct
    public void populateTestData() {
        if (personelRepository.count() == 0) {
            List<Personel> personelList = Stream.of(
                    new Personel("Zeynep", "Hazneci", "123456789"),
                    new Personel("Ece", "Hazneci", "987654321"),
                    new Personel("Mustafa", "Hazneci", "456789123"),
                    new Personel("Oğuz", "Hazneci", "789123456"),
                    new Personel("Barış", "Şahin", "321654987"),
                    new Personel("Buket", "Kara", "192837465"),
                    new Personel("Gökay", "Özsoy", "564738291"),
                    new Personel("Aslıhan", "Özçelik", "837465192"),
                    new Personel("Tugay", "Aksungur", "271839465"),
                    new Personel("Emin", "Tuncer", "111837621")

            ).collect(Collectors.toList());

            personelRepository.saveAll(personelList);
        }
    }
}

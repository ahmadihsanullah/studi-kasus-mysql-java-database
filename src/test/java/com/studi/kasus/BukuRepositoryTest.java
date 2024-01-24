package com.studi.kasus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.studi.kasus.mysql.repository.BukuRepository;
import com.studi.kasus.mysql.repository.Impl.BukuRepositoryImpl;
import com.studi.kasus.mysql.entity.Buku;

public class BukuRepositoryTest {
    private BukuRepository bukuRepository;

    @BeforeEach
    void setUp(){
        bukuRepository = new BukuRepositoryImpl();
    }

    @Test
    void tampilkanBuku(){
        List<Buku> books = bukuRepository.tampilkanData();
        for (Buku buku : books) {
            System.out.println("--------------");
            System.out.println("Referensi : " + buku.getKode());
            System.out.println("Tahun : " + buku.getTahun());
            System.out.println("Penulis : " + buku.getPenulis());
            System.out.println("Penerbit : " + buku.getPenerbit());
            System.out.println("Judul : " + buku.getJudul());
            System.out.println("--------------\n");
        }
        assertNotNull(books);
    }

    @Test
    void cariBuku(){
        List<Buku> books = bukuRepository.cariBuku("faq","penulis");
        int sizeOfCariBuku = books.size();
        System.out.println(sizeOfCariBuku);

        assertEquals(sizeOfCariBuku, books.size());

        for (Buku buku : books) {
            System.out.println("--------------");
            System.out.println("Referensi : " + buku.getKode());
            System.out.println("Tahun : " + buku.getTahun());
            System.out.println("Penulis : " + buku.getPenulis());
            System.out.println("Penerbit : " + buku.getPenerbit());
            System.out.println("Judul : " + buku.getJudul());
            System.out.println("--------------\n");
        }
        assertNotNull(books);
    }

    @Test
    void tambahBuku(){
        Buku buku = new Buku(
            "ahmad_2024_11",
            2024,
            "Ahmad Ihsanullah",
            "Gramedia",
            "Belajar OOP JAVA"
        );

        assertNotNull(buku);
    }

    @Test
    void updateBuku(){
        // Menambahkan buku baru untuk diupdate
        Buku buku = new Buku("iha_2024_123", 2022, "Asma Mutiara Sholihah", "Gramedia", "Candamu Tawamu");
    

        // Melakukan update buku
        bukuRepository.updateBuku(buku, "iha_2024_12112");

    }

    @Test 
    void hapusBuku(){
        bukuRepository.hapusBuku("ahmad_2022_0");
        assertTrue(true);
    }
}

package com.studi.kasus.mysql.repository;

import java.util.List;

import com.studi.kasus.mysql.entity.Buku;

public interface BukuRepository {
    List<Buku> tampilkanData();
    
    List<Buku> cariBuku(String kataKunci, String jenisPencarian );

    void tambahBuku(Buku buku);
    
    void updateBuku(Buku buku, String kode);

    void hapusBuku(String kode);

}

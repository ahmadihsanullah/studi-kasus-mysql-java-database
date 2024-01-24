package com.studi.kasus.mysql.entity;
import java.time.Year;

public class Buku {
    private String kode;
    private Year tahun;
    private String penulis;
    private String penerbit;
    private String judul;
    

    public Buku(String judul) {
        this.judul = judul;
    }

    public Buku(String penerbit, String judul) {
        this.penerbit = penerbit;
        this.judul = judul;
    }

    public Buku(String penulis, String penerbit, String judul) {
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.judul = judul;
    }

    public Buku(){}

    public Buku(int tahun, String penulis, String penerbit, String judul) {
        this.tahun = Year.of(tahun);
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.judul = judul;
    }

    public Buku(String kode, int tahun, String penulis, String penerbit, String judul) {
        this.kode = kode;
        this.tahun = Year.of(tahun);
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.judul = judul;
    }
    
    public String getKode() {
        return kode;
    }
    public void setKode(String kode) {
        this.kode = kode;
    }
    public String getPenulis() {
        return penulis;
    }
    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
    public String getPenerbit() {
        return penerbit;
    }
    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
    public Year getTahun() {
        return tahun;
    }
    public void setTahun(int tahun) {
        this.tahun = Year.of(tahun);
    }
    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((kode == null) ? 0 : kode.hashCode());
        result = prime * result + ((tahun == null) ? 0 : tahun.hashCode());
        result = prime * result + ((penulis == null) ? 0 : penulis.hashCode());
        result = prime * result + ((penerbit == null) ? 0 : penerbit.hashCode());
        result = prime * result + ((judul == null) ? 0 : judul.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Buku other = (Buku) obj;
        if (kode == null) {
            if (other.kode != null)
                return false;
        } else if (!kode.equals(other.kode))
            return false;
        if (tahun == null) {
            if (other.tahun != null)
                return false;
        } else if (!tahun.equals(other.tahun))
            return false;
        if (penulis == null) {
            if (other.penulis != null)
                return false;
        } else if (!penulis.equals(other.penulis))
            return false;
        if (penerbit == null) {
            if (other.penerbit != null)
                return false;
        } else if (!penerbit.equals(other.penerbit))
            return false;
        if (judul == null) {
            if (other.judul != null)
                return false;
        } else if (!judul.equals(other.judul))
            return false;
        return true;
    }

}

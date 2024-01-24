package com.studi.kasus.mysql.repository.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import com.studi.kasus.mysql.entity.Buku;
import com.studi.kasus.mysql.repository.BukuRepository;
import com.studi.kasus.mysql.utils.ConnectionUtil;

public class BukuRepositoryImpl implements BukuRepository {

    @Override
    public List<Buku> tampilkanData() {
       try(Connection connection = ConnectionUtil.getDataSource().getConnection()){
            String sql = "SELECT * FROM books";
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sql)){
                    List<Buku> books = new ArrayList<>();
                    while (resultSet.next()) {
                        books.add(new Buku(
                            resultSet.getString("kode"),
                            resultSet.getInt("tahun"),
                            resultSet.getString("penulis"),
                            resultSet.getString("penerbit"),
                            resultSet.getString("judul")
                        ));
                    }
                    return books;
                }
            }
       } catch (SQLException e) {
            throw new RuntimeException(e);
       }
    }

    @Override
    public List<Buku> cariBuku(String kataKunci, String jenisPencarian) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()){
            String sql;
            if ("tahun".equalsIgnoreCase(jenisPencarian)) {
                sql = "SELECT * FROM books WHERE tahun = ?";
            } else if ("judul".equalsIgnoreCase(jenisPencarian)) {
                sql = "SELECT * FROM books WHERE judul LIKE ?";
            } else if ("penerbit".equalsIgnoreCase(jenisPencarian)) {
                sql = "SELECT * FROM books WHERE penerbit LIKE ?";
            } else if ("penulis".equalsIgnoreCase(jenisPencarian)) {
                sql = "SELECT * FROM books WHERE penulis LIKE ?";
            } else if ("kode".equalsIgnoreCase(jenisPencarian)) {
                sql = "SELECT * FROM books WHERE kode LIKE ?";
            } else {
                throw new IllegalArgumentException("Jenis pencarian tidak valid");
            }

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, "%"+kataKunci+"%");
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    List<Buku> books = new ArrayList<>();
                    while (resultSet.next()) {
                        books.add(
                            new Buku(
                            resultSet.getString("kode"),
                            resultSet.getInt("tahun"),
                            resultSet.getString("penulis"),
                            resultSet.getString("penerbit"),
                            resultSet.getString("judul")
                            )
                        );
                    }
                    return books;
                }
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
    }

    @Override
    public void tambahBuku(Buku buku) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()){
            String sql = "INSERT INTO books (kode, tahun, penulis, penerbit, judul) VALUES (?,?,?,?,?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, buku.getKode());
                preparedStatement.setInt(2,buku.getTahun().getValue());
                preparedStatement.setString(3,buku.getPenulis());
                preparedStatement.setString(4, buku.getPenerbit());
                preparedStatement.setString(5, buku.getJudul());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBuku(Buku buku, String kode) {
        try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            // Mengecek apakah buku dengan kode yang diberikan ada di database
            String selectSql = "SELECT * FROM books WHERE kode = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
                selectStatement.setString(1, kode);
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Jika buku dengan kode yang diberikan ditemukan, maka update data
                        String updateSql = "UPDATE books SET kode = ?, tahun = ?, penulis = ?, penerbit = ?, judul = ? WHERE kode = ?";
                        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                            updateStatement.setString(1, buku.getKode());
                            updateStatement.setInt(2, buku.getTahun().getValue());
                            updateStatement.setString(3, buku.getPenulis());
                            updateStatement.setString(4, buku.getPenerbit());
                            updateStatement.setString(5, buku.getJudul());
                            updateStatement.setString(6, kode); // Menggunakan kode awal untuk kondisi WHERE
                            updateStatement.executeUpdate();
                            System.out.println("Buku berhasil diupdate");
                        }
                    } else {
                        // Jika buku dengan kode yang diberikan tidak ditemukan, Anda mungkin ingin menangani situasi ini sesuai kebutuhan
                        System.out.println("Buku dengan kode " + kode + " tidak ditemukan.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    

    @Override
    public void hapusBuku(String kode) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()){
            String sql = "DELETE FROM books WHERE kode = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, kode);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }
    }
    
}

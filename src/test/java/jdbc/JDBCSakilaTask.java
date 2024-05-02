package jdbc;

import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCSakilaTask extends JDBCParent {

    /* Sakila veritabanını kullanarak bir JDBC taskı oluşturabiliriz. Sakila,
    MySQL örnek veritabanlarından biridir ve film kiralama işlemlerini simüle eder.
    Bu veritabanında film, oyuncu, müşteri ve kiralama gibi tablolar bulunmaktadır.

     Bir JDBC taskı oluşturmak için aşağıdaki adımları izleyebiliriz:

     1.Veritabanına Bağlanma: Sakila veritabanına JDBC üzerinden bağlanacağız.
     2.Film Listesini Almak: Veritabanından film listesini alacak ve konsola yazdıracağız.
     3.Yeni Bir Müşteri Eklemek: Veritabanına yeni bir müşteri ekleyeceğiz.
     4.Yeni müşteri kaydı sorgulama: Müşteri ekledikten sonra eklenen müşteriyi yazdırınız.
     5.Bağlantıyı Kapatma: Son olarak, JDBC bağlantısını kapatacağız.
     Bu adımları içeren bir Java programı oluşturalım:
 */
    @Test
    public void test1() {
        try {
            // SQL sorgusu oluşturma ve yürütme (Film Listesi)
            statement = connection.createStatement();

            // Film Listesi sorgusu ve yazdırma
            String filmSql = "SELECT film_id, title, release_year FROM film";
            try (ResultSet rs = statement.executeQuery(filmSql)) {
                System.out.println("\u001B[32m" + "Film Listesi:" + "\u001B[0m");
                while (rs.next()) {
                    int id = rs.getInt("film_id");
                    String title = rs.getString("title");
                    int releaseYear = rs.getInt("release_year");
                    System.out.println("Film ID: " + id + ", Başlık: " + title + ", Yayın Yılı: " + releaseYear);
                }
            }

            // create_date sütununa varsayılan bir değer atama
            String sqlAlterTable = "ALTER TABLE customer MODIFY COLUMN create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP";
            statement.executeUpdate(sqlAlterTable);

            // Yeni müşteri ekleme işlemi
            String firstName = "Oğuzhan";
            String lastName = "Aydın";
            String email = "oguzhan@techno.com";
            int addressId = 44;
            int active = 1;

            String customerInsertSql = "INSERT INTO customer (store_id, first_name, last_name, email, address_id, active) " +
                    "VALUES (1, '" + firstName + "', '" + lastName + "', '" + email + "', " + addressId + ", " + active + ")";
            statement.executeUpdate(customerInsertSql);
            System.out.println("\u001B[31m" + "\nYeni müşteri eklendi." + "\u001B[0m");

            // Yeni eklenen müşteriyi almak için SQL sorgusu oluşturma ve yürütme
            String newCustomerSql = "SELECT * FROM customer ORDER BY customer_id DESC LIMIT 1";
            try (ResultSet newCustomerRs = statement.executeQuery(newCustomerSql)) {
                System.out.println("\u001B[33m" + "\nYeni Eklenen Müşteri Bilgisi:" + "\u001B[0m");
                if (newCustomerRs.next()) {
                    int id = newCustomerRs.getInt("customer_id");
                    String newFirstName = newCustomerRs.getString("first_name");
                    String newLastName = newCustomerRs.getString("last_name");
                    String newEmail = newCustomerRs.getString("email");
                    int newAddressId = newCustomerRs.getInt("address_id");
                    int newActive = newCustomerRs.getInt("active");
                    System.out.println("\nMüşteri ID: " + id + ", Ad: " + newFirstName + ", Soyad: " + newLastName + ", E-posta: " + newEmail + ", Adres ID: " + newAddressId + ", Aktif: " + newActive);
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println("\u001B[32m" + "\nİşlem tamamlandı." + "\u001B[0m");
    }
}

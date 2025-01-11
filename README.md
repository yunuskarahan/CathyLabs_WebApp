# CathyLabs Web Test Otomasyon Framework

Bu proje, Selenium WebDriver ve Gauge framework kullanılarak geliştirilmiş bir web test otomasyon çerçevesidir. BDD (Davranış Odaklı Geliştirme) prensiplerine uygun şekilde tasarlanmıştır ve web uygulamalarını etkili bir şekilde test etmek için ölçeklenebilir bir yapı sunmaktadır.

## Proje Özellikleri

- **Selenium WebDriver**: Tarayıcı otomasyonu için kullanılmıştır.
- **Gauge Framework**: BDD senaryolarını tanımlamak ve çalıştırmak için kullanılmıştır.
- **Platformlar Arası Test Desteği**: Chrome, Firefox gibi farklı tarayıcılarda test yapabilme.
- **Sayfa Nesne Modeli (POM)**: Kodun sürdürülebilirliğini ve yeniden kullanılabilirliğini artırmak için uygulanmıştır.
- **Raporlama**: Test sonuçlarını ayrıntılı bir şekilde görüntülemek için raporlama araçları entegre edilmiştir.

---

## Gereksinimler

Projenin çalıştırılabilmesi için aşağıdaki araçların sisteminizde yüklü olması gerekmektedir:

1. **Java** (JDK 8 veya üstü)
2. **Maven** (Yapı otomasyonu ve bağımlılık yönetimi için)
3. **Gauge** (Test framework)
    - Gerekli Gauge eklentilerini yüklemek için:
      ```bash
      gauge install java
      gauge install html-report
      ```

4. **WebDriver** (ChromeDriver, GeckoDriver vb.)

---

## Kurulum ve Çalıştırma

1. **Projeyi Klonlayın**:
   ```bash
   git clone https://github.com/yunuskarahan/CathyLabs_WebApp.git
   cd CathyLabs_WebApp
   ```

2. **Bağımlılıkları Yükleyin**:
   ```bash
   mvn clean install
   ```

3. **Testleri Çalıştırın**:
    - Varsayılan ortamda tüm testleri çalıştırmak için:
      ```bash
      mvn test
      ```
    - Belirli bir tag'e sahip senaryoları çalıştırmak için:
      ```bash
      mvn test -DspecsDir=specs -Dtags=@SenaryoTag
      ```

4. **Raporlara Erişim**:
    - Test çalıştırıldıktan sonra HTML raporları şu dizinde bulunur:
      ```
      reports/html-report/index.html
      ```

---

## Proje Yapısı

- **`src/test/java`**: Test sınıfları ve adım implementasyonları.
- **`specs`**: Gauge senaryo dosyaları.
- **`env`**: Ortam dosyaları (test, staging vb.).
- **`pom.xml`**: Maven yapılandırma ve bağımlılık dosyası.
- **`reports`**: Test raporlarının oluşturulduğu dizin.

---

## Ek Özellikler

- **Senaryo Etiketleme**: Test senaryolarını `@Tag` kullanarak kategorize etme.
- **Çoklu Tarayıcı Desteği**: Farklı tarayıcılarda test çalıştırma yeteneği.
- **Raporlama**: Test yürütme sonuçlarını detaylı bir şekilde incelemek için HTML raporlama.

---

## Sorun Giderme

- **`NullPointerException` Hatası**: Projenin gerekli bağımlılıklarının yüklü olduğundan ve doğru Gauge eklentilerinin kurulu olduğundan emin olun.
- **Tarayıcı Başlatılamıyor**: İlgili WebDriver sürümünüzün, kullandığınız tarayıcı ile uyumlu olduğundan emin olun.
- **Test Çalıştırılamıyor**: `mvn clean install` komutuyla bağımlılıkları güncelleyip tekrar deneyin.

---
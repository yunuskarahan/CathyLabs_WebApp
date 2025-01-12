My Account Ekrani Senaryolari
=====================
tags:regresyon,myaccount

     
My Account ekraninda Transfer Money,Add Money ve Edit Account Butonlari Gorunur mu Kontrol Et
----------------
tags:transferMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*My Account Sayfasında Trasnfer Money ,Add Money ve Edit Account Butonlari Gorunur mu Kontrol Et

My Account  Transfer Money Popup Kontrolu
----------------
tags:transferMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*Trasnfer Money Butonuna Tıkla
*Transfer Money Popup'inda Sender Account, Receiver Account ,Amount ve Send Butonu Var mi Kontrol Et


My Account  Transfer Money Amount Tutar Girmeden Send Butonu Aktif Olmaması
------------
tags:transferMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*Trasnfer Money Butonuna Tıkla
*Transfer Money Popup'inda Send Buton Aktif Degil mi Kontol Et

My Account  Transfer Money Amount 0 Tutarı Girildiginde Basarili Islem Yapamaması
------------------
tags:transferMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*My Account Ekranında Amount Tutarı İlk Hesap Tutarı Olarak Hafızaya Kaydedilir.
*Trasnfer Money Butonuna Tıkla
*Transfer Amount Alanına "0" girilir.
*Transfer Money Popup'ında Send Butonuna Tıklanır.
*My Account Ekranında Amount Tutarı Son Hesap Tutarı Olarak Hafızaya Kaydedilir.
*Para Transferi Sonrası Hesap Bakiyesi Transfer Tutarı "0" Kadar Eksilmelidir.



My Account  Transfer Money Amount Girilien Tutar Bakiyeden Girilen Tutar Kadar Dusmeli
------------------
tags:transferMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*My Account Ekranında Amount Tutarı İlk Hesap Tutarı Olarak Hafızaya Kaydedilir.
*Trasnfer Money Butonuna Tıkla
*Transfer Amount Alanına "100" girilir.
*Transfer Money Popup'ında Send Butonuna Tıklanır.
*My Account Ekranında Amount Tutarı Son Hesap Tutarı Olarak Hafızaya Kaydedilir.
*Para Transferi Sonrası Hesap Bakiyesi Transfer Tutarı "100" Kadar Eksilmelidir.


My Account  Transfer Money Amount Girilen Tutar Transaction Kaydında Görülmelidir.
------------------
tags:transferMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*Trasnfer Money Butonuna Tıkla
*Transfer Amount Alanına "100" girilir.
*Transfer Money Popup'ında Send Butonuna Tıklanır.
*Transaction Alanında Son Isleme Ait Bilgilerde Transfer Edilen Tutar "100.00" oldugu Gorulmelidir.



My Account  Transfer Money Amount Eksi Deger Kabul Edilmemeli.
------------------
tags:transferMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*Trasnfer Money Butonuna Tıkla
*Transfer Money Popup'ında Receiver'da Testinium-3 Secilir.
*Transfer Amount Alanına "-100" girilir.
*Transfer Money Popup'ında Send Butonuna Tıklanır.
*Transaction Alanında Son Isleme Ait Bilgilerde Transfer Yapılacak Receeiver Adı "Testinium-3" oldugu Gorulmelidir. 



Add Money Ekranında Card Number,Card Holder ,Expiry Date,CVV,Amount Inputlari ve Add Buton Gorulmelidir.
-------------
tags:addMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*Add Money Butonuna Tıklanır.
*Add Money Popup'ında Card Number,Card Holder,Expriy Date ,Cvv,Amount Inputlari ve Add Buton Gorulmedilir.

Add Money Ekranında Card Number,Card Holder ,Expiry Date,CVV,Amount Inputlarin Zorunlu Olmasi
----------
tags:addMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*Add Money Butonuna Tıklanır.
*Add Money Popup'ında Add Butonuna Tiklanir.
*Add Money Popup'ında Card Number,Card Holder,Expriy Date ,Cvv,Amount Inputlarin Zorunlu Alan Oldugu Gorulmelidir.

Add Money Ekranında Basarili Para Yukleme
----------
tags:addMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*My Account Ekranında Amount Tutarı İlk Hesap Tutarı Olarak Hafızaya Kaydedilir.
*Add Money Butonuna Tıklanır.
*Add Money Popup'ında Card Number Alanina "1234 1234 1234 1234" Girilir.
*Add Money Popun'da Card Holder '"testinium"' , Expriy Date '"1026"' , Cvv '"110"' ve Amount '"100"' Degerleri Girilir
*My Account Ekranında Amount Tutarı Son Hesap Tutarı Olarak Hafızaya Kaydedilir.
*Para Yukleme İslemi Sonrası Hesap Bakiyesi Yukleme Tutarı "100" Kadar Artmalıdır.
*Transaction Alanında Son Isleme Ait Bilgilerde Transfer Yapılacak Receeiver Adı "ykaraha1" oldugu Gorulmelidir.


Add Money Ekranında Gecersiz Tarihte Uyari Vermesi
----------
tags:addMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*Add Money Butonuna Tıklanır.
*Add Money Popup'ında Expriy Date Alanina "1300" Girilir.
*Add Money Popup'ında Add Butonuna Tiklanir.
*Add Money Popup'ında Expriy Date Inputunun Altında Uyarı Mesajı Gorulmelidir.

Add Money Ekranında Cvv alanı 3 karakterden fazla Kabul Etmemesi
----------
tags:addMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*Add Money Butonuna Tıklanır.
*Add Money Popup'ında Cvv Date Alanina "1234" Girilir.
*Add Money Popup'ında Cvv Alanina 3 Karakterden Fazla Girildiginde Uyarı Mesajı Gorulmelidir.


Add Money Ekranında Amount Alanı Text Kabul Etmemesi
----------
tags:addMoney
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*Add Money Butonuna Tıklanır.
*Add Money Popup'ında Amount  Alanina "abcda" Girilir.
*Add Money Popup'ında Add Butonuna Tiklanir.
*Add Money Popup'ında Amount Inputunun Altında Uyarı Mesajı Gorulmelidir.

Edit Acoount Ekraninda Account Name Ve Update Butonu Gorulmelidir.
----------
tags:editAccount
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*Edit Account  Butonuna Tiklanir.
*Edit Account Popup'ında Account Name İnputu ve Update Buton Gorulmedilir.


Edit Acoount Ekraninda Account Name Inputunda Guncel Account Ismi Yazılı Olmalıdır.
----------
tags:editAccount
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*Edit Account  Butonuna Tiklanir.
*Edit Account Popup'ında Account Name İnputunda Guncel Olan  "ykaraha1" Yazili mi Kontol Et.

Edit Acoount Ekraninda Account Name Guncellemelidir.
----------
tags:editAccount
*Catchylabs Uygulamasına ykaraha1 Kullanıcı Adı ile Giris Yapılır.
*Edit Account  Butonuna Tiklanir.
*Edit Account Popup'ında Account Name İnputunda Guncel Olan  "ykaraha1" Yazili mi Kontol Et.
*Edit Account Popup'ında Account Name İnputuna "ykaraha2" girilir.
*Edit Account Popup'ında Update  Butonuna Tiklanir.
*My Account Ekraninda Account Name "ykaraha2"'mi Kontrol Et.
*Edit Account  Butonuna Tiklanir.
*Edit Account Popup'ında Account Name İnputuna "ykaraha1" girilir.
*Edit Account Popup'ında Update  Butonuna Tiklanir.
*My Account Ekraninda Account Name "ykaraha1"'mi Kontrol Et.
# Path-Planning-with-Q-learning-in-java

## ÖZET
Bu projenin amacı ***Q-learning*** kullanarak **rasgele oluşturulmuş** bir kare labirentte başlangıç ve bitiş noktaları arasındaki en kazançlı/kısa yolu hesaplamaktır. 
**Engeller %33 oranında** rasgele dağıtılmış olup, **pekiştirmeli öğrenme** ile programın engelden geçmeden yolu bulabilmesi amaçlanmıştır. Engel / geçit durumu bir txt dosyaya ve arayüze aktarılmış, algoritmanın bulduğu en kısa yol yine arayüzde gösterilmiştir.

## YÖNTEM
Seçimlerin doğru yapılması ve hesapla butonuna basılması sonrası, Q-learning algoritması r ve q matrislerini oluşturur. R matrisi her bir durum-aksiyon bilgisini içinde 
tutar. Geçit yoksa -1, var ise 0, engel ise -10(ceza), bitiş ise +100(ödül) ile hazırlanan r matrisine göre rasgele bir başlangıç noktası seçen algoritma 1000 tekrar ile 
durumu inceler. Olası durumlardaki aldığı ölül ve cezaları q matrisine kaydeder ve tecrübe kazanır. Algoritma tamamlandığında q mantrisindeki tecrübelerinden yola 
çıkarak başlangıç noktasından itibaren en uygun buludugu diğer adımı seçerek bitişe ulaşır. 


![image](https://user-images.githubusercontent.com/121980906/212645663-df5e1160-621d-450a-b9ca-1ffc633d5a1e.png)

![image](https://user-images.githubusercontent.com/121980906/212645824-27ed51ae-f2e4-4ffb-a6bf-af0a346837d6.png)


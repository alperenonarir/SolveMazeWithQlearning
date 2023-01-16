# Path-Planning-with-Q-learning-in-java

Uygulama, iki farklı kenardaki karenin birbirine en kısa yoldan kovuşmasını konu alır. Ferhat Şirin için dağları delemez, engeller onun için çok güçlüdür. Bu yüzden
q learning algoritması ile engelsiz bir yoldan kavuşmak zorundalar. Eğer araları tamamen tıkalıysa kaderlerine boyun eğmek zorundadırlar, aksi takdirde q learning onlar
için en doğru yolu bulacak.

## Başlangıç:
<img width="1106" alt="Screenshot_6" src="https://user-images.githubusercontent.com/43906043/175356156-0e233f8b-201f-475c-b8b2-764d3a7d1107.png">

Mavi ve yeşil kare başlangıçta en yukarıdalardır. Seçim ekranı yardımıyla kareleri istenilen koordinatlara çeker ve hesapla butonuyla yol çizdirilir.

## Hesaplama:
<img width="1133" alt="Screenshot_5" src="https://user-images.githubusercontent.com/43906043/175356193-fbf1b4f3-b433-4491-bb6e-c85457a18f64.png">

Yol hesaplanır ve gri çizgilerle gösterilir.

<img width="1002" alt="Screenshot_8" src="https://user-images.githubusercontent.com/43906043/175356229-eab77469-a87b-4a3a-89c3-e82de8bc49f5.png">

## Hata:

<img width="1105" alt="Screenshot_7" src="https://user-images.githubusercontent.com/43906043/175356217-fb865ebf-5b1d-436d-9674-3e024c2f62b8.png">

Mavi ve yeşil kareler kırmızı engellerin üstüne gelemezler. Kullanıcı böyle bir durumda farklı bir kare(beyaz) seçmek zorunda.


<img width="965" alt="Screenshot_9" src="https://user-images.githubusercontent.com/43906043/175356242-a141d0f0-3210-4bd4-b888-97db101d1191.png">

Uygulama her seferinde rastgele engeller oluşturur ve bu engeller aynı zamanda masaüstüne bir txt dosyasına koordinat bilgisi şeklinde yazdırılır. Kırmızı kareler
"K", Beyaz kareler "B" ile ifade edilmiştir.

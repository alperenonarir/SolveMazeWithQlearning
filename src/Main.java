
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;







public class Main extends JPanel{
   static QLearning q2;
   static boolean hesapYapildiMi=false;
    
    static int bas;     //başlangıç karesinin kaçıncı satırda olduğunu alan değişken.
    static int bit;     // bitiş(hedef) karesinin kaçıncı satırda olduğunu alan değişken.

    static  int cols=50;
    static  int rows=50;
    
    static  int Xekseni=300;      //ekranda nerede görünecek vs. bir takım hesaplamalarda kullanılıyor.
    static  int Yekseni=60;
    
    static  int hücreBoyutu=15; 
    
    static int Matris[][]= new int[rows][cols];   //50x50 matris
      
          @Override
    public void paint(Graphics g){
      super.paintComponent(g);
          
        for(int i=0;i<rows+1;i++)
        g.drawLine(Xekseni, Yekseni+i*hücreBoyutu, Xekseni+cols*hücreBoyutu, Yekseni+i*hücreBoyutu);       //satırları çizer. 51 tane yatay çizgi çizecek
        
         for(int i=0;i<cols+1;i++)
        g.drawLine(Xekseni+i*hücreBoyutu, Yekseni, Xekseni+i*hücreBoyutu, Yekseni+rows*hücreBoyutu);       // sğtunları çizer. 51 tane dikey çizgi çizecek 
        
     
    
    
         //duvarları cizdirrrr  *****************
     for(int i=0;i<cols;i++)
    {
     for(int j=0;j<rows;j++){
         
         if(Matris[i][j]==1)
         {
         g.setColor(Color.RED);
         g.fillRect(Xekseni+j*hücreBoyutu, Yekseni+i*hücreBoyutu, hücreBoyutu, hücreBoyutu);   //kırmızı renkteki duvarları çizecek
         }
         
        }
    }
     
     /// yol cizimi 
    if(hesapYapildiMi){
        // i j indisine göre kacıncı state oldugu hesapla  örn 0 0  state 0 ,,, 0  1 state 1 ,,
                           // baslangıc state numarası hesapla 
                           int baslangicStateNum = rows * (bas-1) ;
                           int x,y;
                           int sonrakistate=baslangicStateNum;
                           for(int i=0; i<rows*3; i++){
                               sonrakistate =  q2.sonrakiDurum(sonrakistate);
                               x = sonrakistate/rows;
                               y = sonrakistate - x * rows;
                               g.setColor(Color.gray);
                               g.fillRect(Xekseni+y*hücreBoyutu, Yekseni+x*hücreBoyutu, hücreBoyutu, hücreBoyutu);   //kırmızı renkteki duvarları çizecek
                               
                           }
                            
        
    }
         //BASLANGİC KARESİ
         g.setColor(Color.BLUE);
         g.fillRect(Xekseni,Yekseni+(bas-1)*hücreBoyutu, hücreBoyutu,hücreBoyutu);      //başlangıç karesini boyar
         
         //BİTİS KARESİ
         g.setColor(Color.green);
         g.fillRect(Xekseni+(cols-1)*hücreBoyutu,Yekseni+(bit-1)*hücreBoyutu, hücreBoyutu,hücreBoyutu);  //bitiş karesini boyar
     
     
    }
    
   
    
    public static void main(String[] args) {
        
 
        
        
        
        
        
       Random rand = new Random();

       
    
    for(int i=0;i<cols;i++)
    {
        for(int j=0;j<rows;j++){

            Matris[i][j]=rand.nextInt(3);  // 0,1 ve 2 rakamları çeker. 1 olduğunda ortalama/yaklaşık %33 ihtimalle duvar olacak.
            
        }
    }

        JFrame frame = new JFrame("MATRİS");   //frame bizim ana ekranımız. 50x50 matrisimiz burada gösterilir. bu kapanırsa program biter. (*x)
        frame.setSize(1400,900);
      
        Main m=new Main();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //(*x) ekran kapandığında programın bitirilmesini sağlar. sadece bu frame için kullandık, diğerlerinde kullanmadık. 
        frame.setLocationRelativeTo(null);                     // frame açıldığında merkeze ortalayacak şekilde gösterilir.
        
        frame.add(m);
        frame.setVisible(true);

        
        
        JFrame f=new JFrame("Seçim Ekranı");    //baslangıç ve bitiş karelermizin değerlerini bu frame'deki spinnerlardan alacağız.
        
        
        JLabel jl1=new JLabel("Başlangıç: ");
        JLabel jl2=new JLabel("Bitiş: ");
        
        jl1.setBounds(100,50, 100,30);
        jl2.setBounds(200,50, 100,30);
        
    SpinnerModel value =  
             new SpinnerNumberModel(25, //direkt gözüken değer  
                1, // minimum değer  
                50, // maksimum değer  
                1); // artış/azalış miktarı  
    JSpinner spinner = new JSpinner(value); 
    
        SpinnerModel value2 =  
             new SpinnerNumberModel(25, //direkt gözüken değer  
                1, //minimum değer 
                50, //maksimum değer 
                1); 
    JSpinner spinner2 = new JSpinner(value2); 
    
            spinner.setBounds(100,100,50,30);
            spinner2.setBounds(200,100,50,30);
            
            f.add(spinner);   
            f.add(spinner2); 
            f.add(jl1);
            f.add(jl2);
            
            f.setSize(400,300);    
            f.setLayout(null);    
            f.setVisible(true);     

            JButton sec = new JButton("seç");
            sec.setBounds(150, 150, 60, 30);
            f.add(sec);
            

                JButton hesapla = new JButton("hesapla");  // bu buton hesaplamalrın yapılmasını tetikleyen buton olacak.
                 hesapla.setBounds(140,200,80,40);
                 hesapla.setBackground(Color.orange);
                f.add(hesapla);
            
                  
                
    sec.addActionListener(new ActionListener () {    // seçim butonu harekete geçiriliyor.
      @Override
      public void actionPerformed(ActionEvent e)
      {
          
        String baslangic = spinner.getValue().toString();  //spinnerlardaki karelerin hangi satırda olduğu bilgisini kullanıcıdan alacak.
        String bitis = spinner2.getValue().toString();

        System.out.println("baslangic: "+baslangic+"\nbitis: "+bitis);
         bas=Integer.parseInt(baslangic);
         bit=Integer.parseInt(bitis);
        
           if(Matris[bas-1][0]!=1 && Matris[bit-1][rows-1]!=1){     // engel yoksa kareleri matrise çizdir.
            frame.repaint();
          }
        
           else{                                                // kullanıcının seçtiği karenin konumunda engel varsa hata mesajı verecek ve kareleri çizdirmeyecek. 
               System.out.println("uyarı meşazııııı");          
               JOptionPane.showMessageDialog(null, "engele takıldınız. lütfen farklı konumlar seçiniz.", "hata!", JOptionPane.PLAIN_MESSAGE);
           }
           }
    });
     
    
        hesapla.addActionListener(new ActionListener () {
      @Override
      public void actionPerformed(ActionEvent e)
      {
          System.out.println("bu buton hesaplama butonudur...................................");
          
          if(Matris[bas-1][0]==1 || Matris[bit-1][rows-1]==1){
              System.out.println("hooop hemşerimmm engel var");
              System.out.println(Matris[bas-1][0]+ "---------"+ Matris[bit-1][rows-1]);
          }
          else{
              System.out.println("yol Yabdı. eskiden neydi öyle çakıllı taşlı engelli yollar");
              System.out.println(Matris[bas-1][0]+ "---------"+ Matris[bit-1][rows-1]);
              
              
              
              //buraya hesaplaya basıldığında algoritmanın çalışacağı kodlar yazılabilir.
              System.out.println("engel olmadığı için bu kısma kodlar yazılabilir.");
              System.out.println("algoritma çalışsınnnnnnnnnnnnn.");

              // baslangıc bitisi isaretle
              Matris[bas-1][0]=88 ; Matris[bit-1][rows-1]=99; // 88 99 un mantıgı yok sadece baslangıc bitis işaretlendi rakamlarla
             // 0 2 gecit
             // 1 duvar
             // 88 baslangıc 99 bitis
             
             
          
              
                           q2 = new QLearning(Matris,cols,rows);
                           q2.init(Matris);  // r matrisi burda yazdırılıyor 25 25 de uzun südüğü için iptal et
                           q2.hesaplaQ();
                           //q2.printQ();     // q matrisi bastırıyor //
                           //q2.printPolicy(); // state yazdırıyor   //
                           hesapYapildiMi=true;
                           frame.repaint();
                           
                           
              System.out.println("ıslem tamamlandi");
              
          }

      }
    });
      
        
        //engel txt'ye yazdır matrisin koordinat ve renklerini
try {
    
      FileWriter myWriter = new FileWriter("C:\\Users\\ALPEREN\\Documents\\Projeler\\SolveMazeWithQlearning\\engel.txt");
   

      for(int k=0;k<rows;k++){
          for(int t=0;t<rows;t++){
              if(Matris[k][t]==1)
      myWriter.write("("+k+","+t+","+"K"+")\n");
      else
          myWriter.write("("+k+","+t+","+"B"+")\n");        
          }
      }
      
      myWriter.close();
     
    } catch (IOException e) {
      System.out.println("Dosyayı yazdiramadin. başarısız oldun.");
      e.printStackTrace();
    }
    
    }  
        
    
    
}

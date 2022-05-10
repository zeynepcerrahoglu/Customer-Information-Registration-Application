package proje2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //DoublyLinkedList oluşturduk
        DoublyLinkedList liste = new DoublyLinkedList();

        //kullanıcıdan veri alacak olan keyboard nesnesi oluşturuldu
        Scanner keyboard = new Scanner(System.in);

        //kullanıcının yapmak istediği işlemleri alacak olan devam değişkeni
        int devam;

        //kullanıcıya sunulacak seçenekler
        System.out.println();
        System.out.println("1-Dosyadan okuma yapmak.");
        System.out.println("2-Klavyeden müşteri verileri girmek.");
        System.out.println("3-İstenilen müşterinin bilgilerini almak.");
        System.out.println("4-Müşteriyi listeden çıkartmak.");
        System.out.println("5-Kayıtları alfabetik sırayla yazdırmak.(A-Z)");
        System.out.println("6-Kayıtları alfabetik sıraya ters olacak şekilde yazdırmak.(Z-A)");
        System.out.println("7-Çıkış.");

        //kullanıcıya yapmak istediği işlem sorulurken devamMi metodu kullanılacaktır
        //islemlere başlamadan önce sorulur ve devam değişkeni belirlenir
        devam = devamMi(keyboard);

        //devam değişkeni yedi ise while döngüsüne hiç girilmeden program sonlandırılır
        if (devam == 7)
            System.out.println("Program sonlandı.");

        //eğer seçilen işlem yedi den küçükse while döngüsü içine girilir
        //yedi ise program sonlanır
        while (devam < 7)
        {
            //customer.txt isimli dosyadan okuma yapar ve cift bağlı liste oluşturur
            if (devam == 1)
            {
                //Dosya okumak için yapılması gereken işlemler

                // Dosya okumak için oluşturulan nesne
                Scanner fileIn = null;
                try {
                    File file = new File("costumer.txt");
                    fileIn = new Scanner(file);
                }
                //Dosya okunmazsa catch blogu çalışır
                // ve sistemden çıkılır
                catch (FileNotFoundException e){
                    System.out.println("File not found");
                    System.exit(0);
                }

                //dosyada okuma yapılırken virgülden virgüle okuma yapılacaktır
                fileIn.useDelimiter(",");

                //dosyanın sonunda gelinene kadar veriler okunur
                //her satırdaki verilerden bir CustomerInfo tipinde bir değişken oluşturulur
                while (fileIn.hasNext())
                {
                    //ad soyad alınır
                    String adSoyad = fileIn.next();

                    //adres alınır
                    String adres = fileIn.next();

                    //telefonlar alınır
                    String telefonlar = fileIn.nextLine();

                    //telefonlar birbirlerinden ayrılır
                    String[] telefon =  telefonlar.split(",");

                    //ayrılan telefonlar teker teker telefonListesi isimli arraylist e eklenir
                    ArrayList<Object> telefonListesi =
                            new ArrayList<>(Arrays.asList(telefon).subList(1, (telefon.length - 1) + 1));

                    //CustomerInfo tipinde bir newCustomer nesnesi oluşturulur
                    CustomerInfo newCustomer = new CustomerInfo(adSoyad, adres, telefonListesi);

                    //Oluşturulan newCostumer nesnesi KayitEkleme metoduyla cift bağlı listeye eklenir
                    liste.KayitEkleme(newCustomer);

                }

                //Dosya okundu
                System.out.println("Dosya okundu.");

                //çift bağlı listede aynı ada ve soyada sahip iki kişi bulunmayacağından
                //dosyanın bir kez okunacağını varsayabiliriz
                System.out.println("Dosya okuma işlemi bir kez yapılabilir.");

            }
            //Klavyeden veri girerek çift bağlı listeye müşteri eklemek
            else if (devam == 2)
            {
                keyboard.nextLine();

                //ad ve soyad alınır
                System.out.print("Müşterinin adını ve soyadını giriniz:\n");
                String adSoyad = keyboard.nextLine();

                //adres alınır
                System.out.println("Müşterinin adres bilgisini giriniz:");
                String adres = keyboard.next();

                //telefon numaraları alınır
                //teker teker numaralar sorulur ve arrayList e eklenir

                System.out.println("Müşterinin kaç tane telefon numarası var:");
                int telefonNoSayisi = keyboard.nextInt();

                ArrayList<Object> telefonlar = new ArrayList<>();

                for (int i = 1; i <= telefonNoSayisi; i++)
                {
                    System.out.printf("%s telefonu giriniz:\n",i);
                    String telefon = keyboard.next();
                    telefonlar.add(telefon);
                }

                //CustomerInfo tipinde newCustomer nesnesi oluşturulur
                CustomerInfo newCustomer = new CustomerInfo(adSoyad.trim(), adres, telefonlar);

                //çift bağlı listeye eklenmesi için kayitEkleme metodu çağrılır
                liste.KayitEkleme(newCustomer);

                System.out.println("Kişi kayıtlara eklendi.");

            }
            //Klavyeden adı ve soyadı girilen müşterinin bilgilerini ekrana yazdırma
            else if (devam == 3)
            {
                //liste boşsa

                if(liste.isEmpty())
                    System.out.println("Kayıt yok.");

                //liste doluysa

                else {

                    keyboard.nextLine();

                    //ad ve soyad alınır
                    System.out.print("Müşterinin adını ve soyadını giriniz:\n");
                    String adSoyad = keyboard.nextLine();

                    //kayitBilgisiYazdirma metodu çağrılır
                    liste.kayitBilgisiYazdirma(adSoyad);
                }
            }
            //Klavyeden adı ve soyadı girilen müşteriyi cift bağlı listeden çıkartma
            else if (devam == 4)
            {
                //liste boşsa

                if(liste.isEmpty())
                    System.out.println("Kayıt yok.");

                //liste doluysa

                else
                {
                    keyboard.nextLine();

                    //ad ve soyad alınır
                    System.out.print("Müşterinin adını ve soyadını giriniz:\n");
                    String adSoyad = keyboard.nextLine();

                    //kayitSilme metodu çağrılır
                    liste.kayitSilme(adSoyad);
                }
            }
            //kayıtları artan alfabetik sırada yazdırma
            else if (devam == 5)
            {
                //liste boşsa

                if (liste.isEmpty())
                    System.out.println("Kayıt yok.");

                //liste doluysa

                else {
                    System.out.println("Kayıtlar(A-Z)");
                    System.out.println("-------------");
                    liste.alfabetikArtanSiraylaYazdirma();
                }
            }
            //kayıtları azalan alfabetik sırada yazdırma
            else
            {
                //liste boşsa

                if (liste.isEmpty())
                    System.out.println("Kayıt yok.");

                //liste doluysa

                else {
                    System.out.println("Kayıtlar(Z-A)");
                    System.out.println("-------------");
                    liste.alfabetikAzalanSiraylaYazdirma();
                }
            }

            //müşteriye başka yapacak işlemi olup olmadığını sormak için devamMı metodunu çağırırız
            devam = devamMi(keyboard);

            //devam değişkeni 7 ise programı sonlandırmak istemişlerdir
            if (devam == 7)
                System.out.println("Program sonlandı.");
        }
    }

    //While döngüsü başlarken ve while döngüsünün sonunda
    //müşteriye sorulacak olan hangi işlemle devam edileceği bilgisini alan ve bu bilgiyi döndüren metot
    //parametre olarak müşteriden bilgi almayı sağlayan Scanner tipindeki keyboard nesnesini alır
    public static int devamMi(Scanner keyboard)
    {
        System.out.println();
        System.out.println("Yapmak istediğiniz işlemi giriniz: ");
        System.out.println("İşlem: ");
        int devam = keyboard.nextInt();
        System.out.println();
        return devam;
    }
}

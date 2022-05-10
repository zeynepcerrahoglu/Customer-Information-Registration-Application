package proje2;


//cift bagli liste
public class DoublyLinkedList {
    
    //Değişkenler

    //Listenin başını tutar
    private Node head;

    //Listenin sonunu tutar
    private Node tail;


    //Consturctorlar

    //Parametresiz contructor
    public DoublyLinkedList()
    {
        head = null;
        tail = null;
    }

    //Parametreli contructor
    public DoublyLinkedList(Node newHead, Node newTail)
    {
        setHead(newHead);
        setTail(newTail);
    }


    //CustomerInfo tipinde parametre alan çift bağlı listeye ekleme yapan metod
    public void KayitEkleme(CustomerInfo eklenen)
    {

        //newNode isimli bir Node değişkeni oluşturuyoruz
        //Metod her çağrıldığında newNode çift bağlı listeye eklenecek yeni Node u tanımlayacak
        //Oluşturulurken her seferinde müşteri verilerini tutacak olan değişken kayıtEkleme metoduna
        //parametre olarak verilen CustomerInfo tipindeki değişkendir
        Node newNode = null;

        //Liste boşsa
        if (head == null)
        {
            //head ve tail değişkenleri atanacak
            //head ve tail liste içinde tek eleman bulunduğundan aynı Node u gösterecek
            tail = head = new Node(eklenen, null, null);
        }
        //karşılaştırma metodundan dönen değer bir ise
        //yeni head newNode olacaktır
        //alfabetik olarak newNode head den önde demektir
        //karşılatırma metoduna ilk parametre olarak head de tutulan adSoyad bilgisi gönderilir
        //ikici parametre olarak eklenen değişkenindeki adSoyad bilgisi gönderilir
        else if (karsilastirma(head.getData().getAdSoyad(), eklenen.getAdSoyad()) > 0)
        {
            //ilk olarak newNode oluşturulur
            //newNode un newNext i önceki head olarak atanacak
            //yeni head newNode olacağından newPrevious olarak null atanacak
            newNode = new Node(eklenen, head, null);

            //önceki head in previous u bundan sonra newNode olacaktır
            head.setPrevious(newNode);

            //yeni head newNode olarak atanır
            head = newNode;
        }
        //newNode ortaya ya da sona eklenecekse
        else
        {
            //listede ortaya  ekleme yapacağımız zaman nereye ekleme yapacağımızı gösteren
            //ve bütün listeyi dolaşan Node değişkeni
            Node position = head;

            //listeyi position null olana kadar dolaşır
            //eğer listenin ortasına ekleme yapılırsa
            //break ile while döngüsünden çıkılır
            while ((position != null))
            {

                //eğer karşılaştırma metodundan bir dönerse
                //newNode position nun önüne eklenecek demektir
                //karşılatırma metoduna ilk parametre olarak position da tutulan adSoyad bilgisi gönderilir
                //ikici parametre olarak eklenen değişkenindeki adSoyad bilgisi gönderilir
                if (karsilastirma(position.getData().getAdSoyad(), eklenen.getAdSoyad()) > 0) {

                    //ilk olarak newNode oluşturulur
                    //newNode un newNext i önüne ekleme yapılacak olan position olacak
                    //newPrevious u position un o andaki previous u olacak
                    newNode = new Node(eklenen, position, position.getPrevious());

                    //position un o anki previous unun next i newNode olarak değiştirilir
                    position.getPrevious().setNext(newNode);

                    //position un previousu bundan sonra newNode olacaktır
                    position.setPrevious(newNode);

                    //böylece listenin ortasına uygun yere ekleme yapılmış olur
                    //bir kere ekleme yapıldıktan sonra while döngüsünden çıkılır
                    break;
                }
                //eğer ekleme yapılmamışsa yani
                //karşılaştırma metodundan uygun değer dönmediyse
                else
                {
                    //bir sonraki Node ile karşılaştırma yapmak için
                    //yeni position position ın next i olarak değiştirilir
                    position = position.getNext();
                }
            }

            //eğer ortaya da ekleme yapılamadıysa geriye tek bir seçenek kalır
            //sona ekleme
            //newNode null olursa daha listeye ekleme yapılmadığını anlarız
            //yeni tail newNode olacaktır
            if (newNode == null)
            {
                //ilk önce newNode u oluştururuz
                //yeni tail olacağından newNext i null olarak atanır
                //newPrevious u o anki tail olarak atanır
                newNode = new Node(eklenen, null, tail);

                //o anki tail in next i newNode olur
                tail.setNext(newNode);

                //yeni tail newNode olarak belirlenir
                tail = newNode;
            }
        }
    } // kayıtEkleme metodunun sonu


    //kayitEkleme metodunda çağrılır
    //newNode eklerken nereye ekleneceğini belirlerken kullanırız
    //iki tane parametre alır
    //ilk parametre o anda listede bulunan Node larda tutulan adSoyad bilgisidir
    //ikincisi eklenen isimli değişkende tutulan adSoyad bilgisidir
    public int karsilastirma(String adSoyad1, String adSoyad2)
    {
        //ilk önce adları ve soyadları birbirlerinden ayırırız
        String[] kisi1 = adSoyad1.split(" ");
        String[] kisi2 = adSoyad2.split(" ");

        //eğer soyadlar aynıysa isimlere bakılacak
        //soyadlar aynıysa if koşulunun içine girer
        //soyadlara bakarken küçük harf büyük harf olması fark etmez
        if (kisi1[kisi1.length -1].equalsIgnoreCase(kisi2[kisi2.length - 1]))
        {
            //birden fazla ad olabilir onları tek bir string de birleştirip
            //karşılaştırma işlemini öyle yapmalıyız
            StringBuilder isim1 = new StringBuilder();
            StringBuilder isim2 = new StringBuilder();

            //isimAl metodunda kişinin adları birleştirilir
            isimAl(kisi1, isim1);
            isimAl(kisi2, isim2);

            //compareTo metodu iki string arasında karşılaştırma yapar
            //karşılaştırılan stringlerin küçük ya da büyük harf olması fark etmez
            //return bir döndürürse newNode o anda karşılatırılan Node un önüne
            //eklenecektir
            return isim1.toString().compareToIgnoreCase(isim2.toString());
        }
        //eğer soyadlar farklıysa sadece soyadlara göre bir karşılaştırma yapılır
        else
            //compareTo metodu iki string arasında karşılaştırma yapar
            //karşılaştırılan stringlerin küçük ya da büyük harf olması fark etmez
            //return bir döndürürse newNode o anda karşılatırılan Node un önüne
            //eklenecektir
            return kisi1[kisi1.length-1].compareToIgnoreCase(kisi2[kisi2.length-1]);
    }

    //karşılaştırma metodunun içinde çağrılır
    //bir kişinin birden fazla adı olabileceğinden bu adlar birleştirilir
    public void isimAl(String[] kisi, StringBuilder isim)
    {
        //soyada kadar birleştirir
        for (int sayac= 0 ; sayac<= kisi.length-2; sayac++)
        {
            isim.append(kisi[sayac]);
        }
    }

    //Adı ve soyadı verilen bir müşteriyi listede arayarak eğer varsa bu müşterinin bilgilerini yazdıran metot
    public void kayitBilgisiYazdirma(String adSoyad)
    {
        //müşterinin liste içinde bulunup bulunmadığı bilgisini tutar
        boolean durum = false;

        //listeyi gezmeye head den başlanır
        Node position = head;

        //listenin sonunda kadar yani position null olana kadar gezilir
        while (position != null)
        {
            //position ın adSoyad bilgisiyle aradığımız müşterinin adSoyad bilgisinin
            //aynı olması durumda if koşulu içine girer
            if (ayniKisiMi(adSoyad,position))
            {
                //müşterinin bilgileri yazdırılır
                System.out.println();
                System.out.println(position.getData());

                //müşteri bulundu
                durum = true;
            }
            //bulunmadıysa bir sonraki Node a geçilir
            position = position.getNext();
        }

        //öyle bir kişi bilgisi liste de yoksa
        if (!durum)
            System.out.println("Kişi kayıtlarda yok.");

    } // kayıtBilgisiYazdırma metodu sonu

    //Adı ve soyadı verilen müşterinin listeden silinmesini sağlayan silme metodu
    public void kayitSilme(String adSoyad)
    {
        //müşterinin liste içinde bulunup bulunmadığı bilgisini tutar
        boolean durum = false;

        //listede tek eleman kalmış ve bu tek eleman silinecekse girilecek olan koşul
        //head ve tail in aynı Node u göstermesi listede tek eleman kaldığını gösterir
        //müşterinin adSoyad bilgisiyle head de tutulan adSoyad bilgisinin aynı olup olmadığına bakılır
        if ((head == tail) && (ayniKisiMi(adSoyad,head)))
        {
            //listeden son eleman da silindiği için
            //head ve tail null olarak atanır
            head = null;
            tail = null;

            //müşteri bulundu ve silindi
            durum = true;
        }


        //listede birden fazla eleman varsa

        //müşterinin adSoyad bilgisiyle head de tutulan adSoyad bilgisinin aynı olup olmadığına bakılır
        //eğer aynıysa head in silineceği ve başka bir head belirleneceği anlamına gelir
        else if (ayniKisiMi(adSoyad,head))
        {
            //yeni head artık o anki head in next i olacaktır
            head = head.getNext();

            //yeni head in previous u null olarak atanır
            head.setPrevious(null);

            //müşteri bulundu ve silindi
            durum = true;
        }
        //müşterinin adSoyad bilgisiyle tail de tutulan adSoyad bilgisinin aynı olup olmadığına bakılır
        //eğer aynıysa tail in silineceği ve başka bir tail belirleneceği anlamına gelir
        else if (ayniKisiMi(adSoyad,tail))
        {
            //yeni tail artık o anki tail in previous u olacaktır
            tail = tail.getPrevious();

            //yeni tail in next i null olarak atanır
            tail.setNext(null);

            //müşteri bulundu ve silindi
            durum = true;
        }
        //ortadan silme
        else
        {
            //bütün listeyi dolaşacak ve silinecek elemanı bulacak olan position değişkeni
            Node position = head.getNext();

            //position null olana kadar yani liste sonuna gelene kadar while döngüsü döner
            while (position != null) {

                //müşterinin adSoyad bilgisiyle position da tutulan adSoyad bilgisinin aynı olup olmadığına bakılır
                if (ayniKisiMi(adSoyad,position)) {

                    //position un bağlantısını keseceğiz

                    //position un next inin previousu nu position un previous u olarak değiştiririz
                    position.getNext().setPrevious(position.getPrevious());

                    //position un previous unun next ini position un next i olarak değiştiririz
                    position.getPrevious().setNext(position.getNext());

                    //müşteri bulundu ve silindi
                    durum = true;
                }

                //silinecek kişi bulunamadıysa liste gezmeye devam edilir
                position = position.getNext();
            }
        }

        //kişi silindiyse
        if (durum)
            System.out.println("Kişi silindi.");
        //öyle bir kişi liste de yoksa
        else
            System.out.println("Kişi kayıtlarda yok.");

    } //kayıtSilme metodu sonu

    //kayitSilme ve kayitBilgisiYazdırma metodlarında çağrılır
    //ilk parametresi String tipinde adSoyad bilgisidir
    //ikinci parametresi Node tipindedir
    //Node tipindeki parametrenin içinden adSoyad bilgisi çekilir
    //iki parametre equals metoduyla karşılaştırılır
    //küçük ya da büyük harf fark etmeden karşılaştırma yapılır
    public boolean ayniKisiMi(String kisi1, Node kisi2)
    {
       return kisi1.replace(" ","").equalsIgnoreCase(kisi2.getData().getAdSoyad().replace(" ",""));
    }

    //Tüm kayıtları artan alfabetik sırada (A’dan Z’ye) ekrana yazdıran metot
    public void alfabetikArtanSiraylaYazdirma()
    {
        //listeyi gezecek olan position değişkeni
        //artan olarak yazdırılacağından listenin başından başlanmalı
        //o yüzden başta position değişkeni head olarak atandı
        Node position = head;

        //position null olana kadar liste gezilir
        while (position != null)
        {
            //liste elemanının bilgileri yazdırılır
            System.out.println(position.getData().toString());

            //bir sonraki elemana geçilir
            position = position.getNext();

            System.out.println();
        }
    } //alfabetikArtanSiraylaYazdırma metodunun sonu


    //Tüm kayıtları azalan alfabetik sırada (Z’den A’ya) ekrana yazdıran metot
    public void alfabetikAzalanSiraylaYazdirma()
    {
        //listeyi gezecek olan position değişkeni
        //azalan olarak yazdırılacağından listenin sonundan başlanmalı
        //o yüzden başta position değişkeni tail olarak atandı
        Node position = tail;

        //position null olana kadar liste gezilir
        while (position != null)
        {
            //liste elemanının bilgileri yazdırılır
            System.out.println(position.getData().toString());

            //bir sonraki elemana geçilir
            position = position.getPrevious();

            System.out.println();
        }
    } // alfabetikAzalanSiraylaYazdirma metodunun sonu


    //Listenin boş olup olmadığı bilgisini döndürür
    public boolean isEmpty()
    {
        return head == null;
    }


    //Get ve Set metodları

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    
    //toString metodu

    @Override
    public String toString()
    {
        return "head: " + head + "\ntail: "  + tail;
    }
}

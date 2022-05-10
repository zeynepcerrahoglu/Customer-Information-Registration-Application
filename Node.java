package proje2;

public class Node {
    
    //Değişkenler

    //CustomerInfo tipinde tanımlanmış müşterilerle ilgili verileri tutacak olan değişken
    private CustomerInfo data;

    //Çift bağlı liste oluşturmak için gereken bağ sahaları
    private Node next;  // Kendinden sonraki nesneyi tutar
    private Node previous; // Kendinden önceki nesneyi tutar


    //Constructorlar

    //Parametresiz constructor
    public Node()
    {
        data = new CustomerInfo();
        next = null;
        previous = null;
    }

    //Parametreli constructor
    public Node(CustomerInfo newData, Node newNext, Node newPrevious)
    {
        setData(newData);
        setNext(newNext);
        setPrevious(newPrevious);
    }


    // Get ve Set metodları

    public CustomerInfo getData() {
        return data;
    }

    public void setData(CustomerInfo data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }


    //toString metodu

    @Override
    public String toString()
    {
        return "data: "+ data + "\nnext data: " + next + "\nprevious data: " + previous;
    }
    
}

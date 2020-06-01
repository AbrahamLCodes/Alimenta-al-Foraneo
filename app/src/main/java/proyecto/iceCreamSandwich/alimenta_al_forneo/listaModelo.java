package proyecto.iceCreamSandwich.alimenta_al_forneo;

public class listaModelo {
    private String item;
    private int id;


    public listaModelo(String item, int id){
        this.item=item;
        this.id = id;
    }

    public String getItem(){ return item;}

    public void setItem(String item){this.item=item;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

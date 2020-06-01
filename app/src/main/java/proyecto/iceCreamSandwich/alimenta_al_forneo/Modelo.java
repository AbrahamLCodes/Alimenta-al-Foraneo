package proyecto.iceCreamSandwich.alimenta_al_forneo;

public class Modelo {
    int hambreProgress =10;
    int vidaProgress;
    int felcidadProgress;
    public Modelo(int felcidadProgress, int hambreProgress, int vidaProgress){
        this.felcidadProgress = felcidadProgress;
        this.hambreProgress = hambreProgress;
        this.vidaProgress = hambreProgress;

    }

    public int getFelcidadProgress() {
        return felcidadProgress;
    }

    public int getHambreProgress() {
        return hambreProgress;
    }
}



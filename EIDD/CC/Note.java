public class Note {
    Conso[] tabConsos;

    public Note() {
        this.tabConsos = new Conso[50];
    }

    public double prixTot() {
        double result = 0;
        for (int i = 0; i < this.tabConsos.length; i++){
            if (this.tabConsos[i] != null) {
                result += this.tabConsos[i].prix;
            }
        }
        return result;
    }

    public boolean ajouteConso(Conso c) {
        for (int i = 0; i < this.tabConsos.length; i++){
            if (this.tabConsos[i] != null) {
                this.tabConsos[i] = c;
                return true;
            }
        }
        return false;
    }

    public boolean annuleConso(String s) {
        for (int i = 0; i < this.tabConsos.length; i++){
            if (this.tabConsos[i].nom.equals(s) == true) {
                this.tabConsos[i] = null;
                return true;
            }
        }
        return false;
    }
}
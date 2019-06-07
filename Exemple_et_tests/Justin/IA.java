
public class IA {
    private IANiv1 ia;
    private int mode;

    public IA(int niveau, int mode, Jouer controleur){
        this.mode= mode;
        if(mode !=1 && mode !=2 && mode !=3){
            System.err.println("ERREUR 001 : Entrée invalide : mode invalide");
        }
        switch (niveau){
            case 1 :
                ia = new IANiv1(controleur,mode);
                break;
            case 2:
                ia = new IANiv2(controleur,mode);
                break;
            default:
                System.err.println("ERREUR 002 : Entrée invalide : niveau invalide");
                break;
        }
    }
    public void jouerIA(){
        if (mode == 3){
            //ia.jouerSpe();
            System.err.println("lol c'est pas prêt");
        }else{
            ia.jouer();
        }
    }

    public void retour(){
        ia.retour();
    }

    public void fin(){
        ia.fin();
    }
}

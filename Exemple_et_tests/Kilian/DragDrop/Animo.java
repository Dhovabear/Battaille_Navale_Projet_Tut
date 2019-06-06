
import java.util.*;

abstract class Animal{
	public abstract void crie();
}

class Chien extends Animal{
	public Chien(){

	}

	public void crie(){
		System.out.println("OUAF OUAF");
	}
}

class Serpent extends Animal{
	public Serpent(){

	}

	public void crie(){
		System.out.println("TSSS TSSS");
	}
}

class Canard extends Animal{
	public Canard(){

	}

	public void crie(){
		System.out.println("COIN COIN");
	}
}

class Chat extends Animal{
	public Chat(){

	}

	public void crie(){
		System.out.println("MIIIIIAAAAOOUUUU");
	}
}

class Lapin extends Animal{
	public Lapin(){

	}

	public void crie(){
	}
}

public class Animo{

	public static List<Animal> la = new ArrayList<Animal>();

	public static void main(String[] args) {
		la.add(new Chien());
		la.add(new Serpent());
		la.add(new Chat());
		la.add(new Canard());
		la.add(new Lapin());
		cacophonie();
	}

	public static void cacophonie(){
		for ( Animal a : la ) {
			a.crie();
		}
	}
}